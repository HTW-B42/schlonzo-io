import React, { useState, useEffect, useMemo } from 'react';
import { AuthSuccessDTO, UserDTO, DefaultApi } from 'quizgame-client-api/src';
import './index.css';
import Login from './login';
import Home from "./home";
import { BrowserRouter as Router, Link, Route, Routes, useNavigate } from 'react-router-dom';
import ReactDOM from 'react-dom/client';
import { SESSION_TOKEN, USER } from "./constants";
import Game from './game';

function App() {
  const [loggedIn, setLoggedIn] = useState(false);
  const [sessionToken, setSessionToken] = useState('');
  const [isDarkMode, setIsDarkMode] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [currentQuestion, setCurrentQuestion] = useState(null);
  const [user, setUser] = useState(null);
  const [gameSession, setGameSession] = useState(null);
  const client = useMemo(() => new DefaultApi(), []);
  const state = {
    loggedIn, sessionToken, isDarkMode, isLoading, user, gameSession
  };

  const onLoginSuccess = function (response = {}) {
    if (!response.hasOwnProperty(SESSION_TOKEN) || !response.hasOwnProperty(USER)) {
      console.log('no auth success');
      return;
    }
    const authSuccess = AuthSuccessDTO.constructFromObject(response);
    setSessionToken(authSuccess[SESSION_TOKEN]);
    const user = UserDTO.constructFromObject(authSuccess[USER]);
    setUser(user);
    setLoggedIn(true);
  };
  const onGameStartFromHome = function (gameID = state.gameSession ) {
    if (!state.gameSession) {
      setGameSession(gameID);
    }
  };
  const onGameEnd = function (userScore = null) {
    setGameSession(null);
  };

  useEffect(() => {
    if (loggedIn && !gameSession) {
      setIsLoading(true);
      client.startGame(sessionToken)
        .then((session) => {
          setGameSession(session.gameSession);
          setIsLoading(false);
        })
        .catch((error) => {
          console.error('Error:', error);
          setIsLoading(false);
        });
    }
  }, [client, loggedIn, gameSession, sessionToken]);

  useEffect(() => {
    if (loggedIn && gameSession && !currentQuestion) {
      setIsLoading(true);
      client.getQuestion(sessionToken)
        .then((data) => {
          setCurrentQuestion(data);
          setIsLoading(false);
        })
        .catch((error) => {
          console.error('Error:', error);
          setIsLoading(false);
        });
    }
  }, [client, loggedIn, gameSession, currentQuestion, sessionToken]);


   useEffect(() => {
    onGameStartFromHome();
  }, [onGameStartFromHome]);

  return (
    <React.StrictMode>
      <Router>
        <Link to={'/'}>login</Link>
        <Link to={'/home'}>home</Link>
        <Routes>
          <Route path={'/'} element={<Login state={state} onSuccess={onLoginSuccess} />} />
          <Route path={'/home'} element={<Home state={state} onGameStart={onGameStartFromHome} />} />
          <Route path={'/game'} element={<Game state={state} onGameEnd={onGameEnd} />} />
        </Routes>
      </Router>
    </React.StrictMode>
  );
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App />);
