import React, {useState} from 'react';
import {AuthSuccessDTO, UserDTO} from 'quizgame-client-api/src';
import './index.css';
import Login from './login';
import Home from "./components/Home";
import {BrowserRouter as Router, Link, Route, Routes} from 'react-router-dom';
import ReactDOM from 'react-dom/client';
import {SESSION_TOKEN, USER} from "./constants";
import Quiz from "./quiz";

function App() {
  const [loggedIn, setLoggedIn] = useState(false)
  const [sessionToken, setSessionToken] = useState('')
  const [isDarkMode, setIsDarkMode] = useState(false)
  const [isLoading, setIsLoading] = useState(false)
  const [user, setUser] = useState(null)
  const [gameSession, setGameSession] = useState(null)

  const state = {
    loggedIn, sessionToken, isDarkMode, isLoading, user, gameSession
  }

  const onLoginSuccess = function (response = {}) {
    if (!response.hasOwnProperty(SESSION_TOKEN) || !response.hasOwnProperty(USER)) {
      console.log('no auth success')
      return
    }
    const authSuccess = AuthSuccessDTO.constructFromObject(response)
    setSessionToken(authSuccess[SESSION_TOKEN])
    const user = UserDTO.constructFromObject(authSuccess[USER]);
    setUser(user)
    setLoggedIn(true)
  };

  const onGameStartFromHome = function (response = {}) {
    if (!response.hasOwnProperty("gameID") || !response.hasOwnProperty("scoreboard")) {
      console.log('no valid session');
      return
    }
    setGameSession(response);
  };

  const onGameEnd = function (userScore = null) {

  };

  return (
      // TODO <Header></Header>
      <React.StrictMode>
        <Router>
          <Routes>
            <Route path={'/'} element={<Login state={state} onSuccess={onLoginSuccess}/>}></Route>
            <Route path={'/home'} element={<Home state={state} onGameStart={onGameStartFromHome}/>}></Route>
            <Route path={'/game'} element={<Quiz state={state} onGameEnd={onGameEnd}/>}></Route>
          </Routes>
        </Router>
      </React.StrictMode>
  );
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App/>);

// reportWebVitals();
