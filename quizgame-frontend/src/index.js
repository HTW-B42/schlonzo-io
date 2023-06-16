import React, {useState} from 'react';
import {AuthSuccessDTO, UserDTO} from 'quizgame-client-api/src';
import './index.css';
import Login from './login';
import Home from "./home";
import {BrowserRouter as Router, Link, Route, Routes} from 'react-router-dom';
import ReactDOM from 'react-dom/client';
import {SESSION_TOKEN, USER} from "./constants";


function App() {

  // const [state, setState] = useState({
  //   loggedIn: false,
  //   sessionToken: null,
  //   isDarkMode: false,
  //   isLoading: false,
  //   user: null,
  //   gameSession: null,
  // })

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

  return (
      // TODO <Header></Header>
      <React.StrictMode>
        <Router>
          <Link to={'/'}>login</Link>
          <Link to={'/home'}>home</Link>
          <Routes>
            <Route path={'/'} element={<Login state={state} onSuccess={onLoginSuccess}/>}></Route>
            <Route path={'/home'} element={<Home state={state} onGameStart={onGameStartFromHome}/>}></Route>
          </Routes>
        </Router>
      </React.StrictMode>
  );
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App/>);

// reportWebVitals();
