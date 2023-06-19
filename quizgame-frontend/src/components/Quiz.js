import React, { useEffect, useState, useMemo, useCallback } from 'react';
import { DefaultApi } from 'quizgame-client-api/src';
import * as PropTypes from "prop-types";
import './Quiz.css';
import ScoreBoard from './ScoreBoard';
import {
    SESSION_TOKEN,
    USER_EMAIL,
    USER_NAME,
    IS_LOADING,
    IS_LOGGED_IN,
    USER,
} from '../constants';

export default function Quiz({ state, onGameEnd }) {
    const [question, setQuestion] = useState(null);
    const [isLoading, setIsLoading] = useState(false);
    const [gameOver, setGameOver] = useState(false);
    const [answeredCorrectly, setAnsweredCorrectly] = useState(null);
    const [score, setScore] = useState(0);
    const [helpContent, setHelpContent] = useState('');
    const [showHelp, setShowHelp] = useState(false);

    Quiz.propTypes = {
        state: PropTypes.shape({
            isLoading: PropTypes.bool,
            loggedIn: PropTypes.bool,
            sessionToken: PropTypes.string,
            isDarkMode: PropTypes.bool,
            user: PropTypes.any,
            gameSession: PropTypes.any
        })
    };

    const api = useMemo(() => new DefaultApi(), []);


    const fetchQuestion = useCallback(() => {
        setIsLoading(true);
        api
            .getQuestion(state.sessionToken)
            .then((data) => {
                setQuestion(data);
                setIsLoading(false);
            })
            .catch((error) => {
                console.error('Error:', error);
                setIsLoading(false);
            });
    }, [api, state.sessionToken]);

    useEffect(() => {
        fetchQuestion();
    }, [fetchQuestion]);

    const handleAnswer = (answer) => {
        setIsLoading(true);
        api
            .answerQuestion(state.sessionToken, answer === question.correctAnswer)
            .then(() => {
                setAnsweredCorrectly(answer === question.correctAnswer);
                setScore(score + (answer === question.correctAnswer ? 1 : 0));
                setIsLoading(false);
            })
            .catch((error) => {
                console.error('Error:', error);
                setIsLoading(false);
            });
    };


    const handleNextQuestion = () => {
        setAnsweredCorrectly(null);
        fetchQuestion();
    };

    const handleGameOver = () => {
        setGameOver(true);
    }

    const handlePlayAgain = () => {
        setGameOver(false);
        setScore(0);
        fetchQuestion();
    }

    const toggleHelp = () => {
        setShowHelp(!showHelp);
        if (!showHelp) {
          setHelpContent('This is the help menu. You can find information about the game rules and other tips here.');
        } else {
          setHelpContent('');
        }
    };
     
    if (gameOver) {
        return <ScoreBoard score={score} onGameEnd={onGameEnd} onPlayAgain={handlePlayAgain} />;
    }

    return (
        <div className="container">
            <div className="quiz">
            <div className="help-container">
                <span className="help-icon" onClick={toggleHelp}>
                  ?
                </span>
                {showHelp && (
                  <div className="help-tooltip">
                    <h4>Game Rules</h4>
                    <p>Here are the game rules:</p>
                    <ul>
                      <li>Answer the questions correctly to earn points.</li>
                      <li>You can view your score and progress on the screen.</li>
                    </ul>
                    <p>Enjoy the game and have fun!</p>
                  </div>
                )}
              </div>
                {isLoading && <p>Loading...</p>}
                {!isLoading && question && (
                    <div>
                        <h2 className="question">{question.question}</h2>
                        {question.answerChoices.map((answerChoice, index) => (
                            <button
                                key={index}
                                onClick={() => handleAnswer(answerChoice)}
                                className={`answer-button ${answeredCorrectly === answerChoice ? (answeredCorrectly ? 'correct' : 'incorrect') : ''}`}
                            >
                                {answerChoice}
                            </button>
                        ))}
                        {answeredCorrectly && <p className="feedback correct">Correct!</p>}
                        {answeredCorrectly === false && (
                            <p className="feedback incorrect">Incorrect!</p>
                        )}
                        <p className="score">Score: {score}</p>
                        <button onClick={handleNextQuestion}>Next Question</button>
                    </div>
                )}
            </div>
        </div>
    );
}


