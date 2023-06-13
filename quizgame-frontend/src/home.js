import { useNavigate } from "react-router-dom";
import {useEffect} from 'react';
import { SESSION_TOKEN, USER_EMAIL, USER_NAME } from "./constants";

export default function Home({ state, onGameStart }) {
  const navigate = useNavigate();
  useEffect(() => {
    if (state.gameSession) {
      navigate('/game');
    }
  }, [state.gameSession, navigate]);
 

  if (state.loggedIn && state.user) {
    const user = state.user;
    return (
      <div>
        <div>logged in as {user[USER_NAME]}</div>
        <div>sessiontoken {state.sessionToken}</div>
        <div>email {user[USER_EMAIL]}</div>
        <button onClick={onGameStart}>Start Game</button> 
      </div>
    );
  } else {
    return <h1>You have to log in</h1>;
  }
}

