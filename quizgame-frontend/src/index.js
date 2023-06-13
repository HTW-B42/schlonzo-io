import React, {useState} from 'react';
import {AuthSuccessDTO, UserDTO} from 'quizgame-client-api/src';
import './index.css';
import Login from './login';
import Home from "./home";
import {BrowserRouter as Router, Link, Route, Routes} from 'react-router-dom';
import ReactDOM from 'react-dom/client';
import {SESSION_TOKEN, USER} from "./constants";
import * as PropTypes from "prop-types";


class Game extends React.Component {
  render() {
    return null;
  }
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

  const onGameStartFromHome = function (gameId = null) {
    if (gameId) {
      // TODO join existing game gibts noch nicht, im moment nur neues Spiel starten:
    } else {
      // TODO create new game and join...
    }
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
            <Route path={'/game'} element={<Game state={state} onGameEnd={onGameEnd}/>}></Route>
          </Routes>
        </Router>
      </React.StrictMode>
  );
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App/>);

// reportWebVitals();
