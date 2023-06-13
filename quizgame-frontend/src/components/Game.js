import React, { useState } from "react";
import './Game.css';
import {useNavigate} from "react-router-dom";
import * as PropTypes from "prop-types";


const Game = (props) => {
    const state = props.state;
    const navigate = useNavigate();
    const [currentRound, setCurrentRound] = useState(1);
    const [score, setScore] = useState(0);

    const questions = [{
            question: 'Question 1',
                answers: [
            { text: 'Answer 1.1', isCorrect: true },
            { text: 'Answer 1.2', isCorrect: false },
            { text: 'Answer 1.3', isCorrect: false },
            { text: 'Answer 1.4', isCorrect: false }
        ]
        },
        {
            question: 'Question 2',
                answers: [
            { text: 'Answer 2.1', isCorrect: false },
            { text: 'Answer 2.2', isCorrect: false },
            { text: 'Answer 2.3', isCorrect: false },
            { text: 'Answer 2.4', isCorrect: true }
        ]
        },
        {
            question: 'Question 3',
            answers: [
                { text: 'Answer 3.1', isCorrect: true },
                { text: 'Answer 3.2', isCorrect: false },
                { text: 'Answer 3.3', isCorrect: false },
                { text: 'Answer 3.4', isCorrect: false }
            ]
        },
        {
            question: 'Question 4',
            answers: [
                { text: 'Answer 4.1', isCorrect: false },
                { text: 'Answer 4.2', isCorrect: true },
                { text: 'Answer 4.3', isCorrect: false },
                { text: 'Answer 4.4', isCorrect: false }
            ]
        }]

    const handleAnswer = (isCorrect) => {
        if (isCorrect) {
            setScore(score + 1);
        }

        if (currentRound === 4) {
            setCurrentRound(1);
            setScore(0);
        } else {
            setCurrentRound(currentRound + 1);
        }
    };


    return (
        <div className="container">
            {currentRound <= 4 ? (
                <div>
                    <h2 className="round">Round {currentRound}</h2>
                    <p className="question">{questions[currentRound - 1].question}</p>
                    <ul>
                        {questions[currentRound - 1].answers.map((answer, index) => (
                            <li key={index}>
                                <button onClick={() => handleAnswer(answer.isCorrect)}>
                                    {answer.text}
                                </button>
                            </li>
                        ))}
                    </ul>
                    <p>Score: {score}</p>
                </div>
            ) : (
                <div>
                    <h2>Quiz Completed!</h2>
                    <p>Final Score: {score}</p>
                    <button onClick={props.onGameEnd} />
                </div>
            )}
        </div>
    );
}

Game.propTypes = {
  state: PropTypes.shape({
    isLoading: PropTypes.bool,
    loggedIn: PropTypes.bool,
    sessionToken: PropTypes.string,
    isDarkMode: PropTypes.bool,
    user: PropTypes.any,
    gameSession: PropTypes.any
  })
};

export default Game;