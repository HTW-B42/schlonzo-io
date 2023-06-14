import React, { useEffect, useState, useMemo, useCallback } from 'react';
import { DefaultApi } from 'quizgame-client-api/src';
import * as PropTypes from "prop-types";
import './quiz.css';
import {
    SESSION_TOKEN,
    USER_EMAIL,
    USER_NAME,
    IS_LOADING,
    IS_LOGGED_IN,
    USER,
} from './constants';

export default function Quiz({ state }) {
    const [question, setQuestion] = useState(null);
    const [isLoading, setIsLoading] = useState(false);
    const [answeredCorrectly, setAnsweredCorrectly] = useState(null);
    const [score, setScore] = useState(0);

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


    return (
        <div className="container">
            <div className="quiz">
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


