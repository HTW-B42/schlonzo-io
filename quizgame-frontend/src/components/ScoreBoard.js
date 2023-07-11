import React from 'react';
import {useNavigate} from "react-router-dom";
import './ScoreBoard.css';

const ScoreBoard = ({ score, onGameEnd, onPlayAgain, handleGameOver }) => {
  const navigate = useNavigate();

  const handleQuit = () => {
    handleGameOver();
    onGameEnd();
    navigate('/home');
  }

  const handlePlayAgain = () => {
    handleGameOver();
    onPlayAgain();
  }

  return (
    <div>
      <h2>Your Score: {score}</h2>
      <button onClick={handlePlayAgain}>Play Again</button>
      <button onClick={handleQuit}>Quit</button>
    </div>
  );
}

export default ScoreBoard;
