import React, {useMemo, useState} from 'react';
import {BasicAuthDTO, DefaultApi,} from 'quizgame-client-api/src';
import './App.css';
import {useNavigate} from "react-router-dom";
import {USER_NAME} from "./constants";

const difficultyLevels = ['Easy', 'Medium', 'Hard'];
const categories = ['Sports', 'History', 'Science'];

export default function Login({
                                state, onSuccess
                                // loggedIn = false,
                                // user = null
                              }) {

  const navigate = useNavigate()

  if (state.loggedIn) {
    navigate('/home')
  }

  const [username, setUsername] = useState(state.user ? state.user[USER_NAME] : '');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [isRegistering, setIsRegistering] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');
  const [isDarkMode, setIsDarkMode] = useState(state.isDarkMode);
  const [isLoading, setIsLoading] = useState(false);

  const client = useMemo(() => new DefaultApi(), []);

  // useEffect(() => {
  //   if (isLoggedIn && !gameSession) {
  //     setIsLoading(true);
  //     client.startGame(sessionToken)
  //         .then((session) => {
  //           setGameSession(session);
  //           setIsLoading(false);
  //         })
  //         .catch((error) => {
  //           console.error('Error:', error);
  //           setIsLoading(false);
  //         });
  //   }
  // }, [client, isLoggedIn, gameSession, sessionToken]);
  //
  // useEffect(() => {
  //   if (isLoggedIn && gameSession && !currentQuestion) {
  //     setIsLoading(true);
  //     client.getQuestion(sessionToken)
  //         .then((data) => {
  //           setCurrentQuestion(data);
  //           setIsLoading(false);
  //         })
  //         .catch((error) => {
  //           console.error('Error:', error);
  //           setIsLoading(false);
  //         });
  //   }
  // }, [client, isLoggedIn, gameSession, currentQuestion, sessionToken]);

  // const handleAnswer = (answer) => {
  //   setIsLoading(true);
  //   client.answerQuestion(sessionToken, answer === currentQuestion.correctAnswer)
  //     .then((data) => {
  //       setGameSession(data);
  //       setIsAnswered(true);
  //       setIsCorrect(answer === currentQuestion.correctAnswer);
  //       setIsLoading(false);
  //       if (answer === currentQuestion.correctAnswer) {
  //         setScore(score + 1);
  //       }
  //     })
  //     .catch((error) => {
  //       console.error('Error:', error);
  //       setIsLoading(false);
  //     });
  // };
  //
  // const handleNextQuestion = () => {
  //   setCurrentQuestion(null);
  //   setIsAnswered(false);
  //   setIsCorrect(false);
  // };

  const handleLogin = async () => {
    setIsLoading(true)
    const basicAuth = new BasicAuthDTO(btoa(`${username}:${password}`))
    let authSuccess
    await client.performLogin(basicAuth)
        .then((res) => authSuccess = res)
        .catch((error) => {
          console.error('Error: ', error)
        })
        .finally(() => setIsLoading(false));
    onSuccess(authSuccess)
    navigate('/home')
  };

  // const handleLogout = () => {
  //   setIsLoading(true);
  //   client.performLogout(sessionToken)
  //     .then(() => {
  //       setIsLoggedIn(false);
  //       setGameSession(null);
  //       setCurrentQuestion(null);
  //       setScore(0);
  //     })
  //     .catch((error) => {
  //       console.error('Error:', error);
  //     })
  //     .finally(() => {
  //       setIsLoading(false);
  //     });
  // };


  const handleRegister = async () => {
    if (password !== confirmPassword) {
      setErrorMessage('Passwords do not match');
    } else if (!password || !confirmPassword) {
      setErrorMessage('Please fill in both password fields');
    } else {
      setErrorMessage('');

      const newUser = {
        name: username,
        email: email,
        password: password,
      }

      setIsLoading(true);
      try {
        const response = await client.registerUser(newUser);
        console.log(response);

        setIsLoading(true)
      } catch (error) {
        console.error('Error:', error);
      }

      setIsLoading(false);
    }
  };

  const handleSwitchToLogin = () => {
    setUsername('');
    setPassword('');
    setConfirmPassword('');
    setErrorMessage('');
    setIsRegistering(false);
  };
  //
  // const handleDifficultyChange = (event) => {
  //   setDifficulty(event.target.value);
  // };
  //
  // const handleCategoryChange = (event) => {
  //   setSelectedCategory(event.target.value);
  // };
  //
  // const toggleHelp = () => {
  //   setShowHelp(!showHelp);
  //   if (!showHelp) {
  //     setHelpContent('This is the help menu. You can find information about the game rules and other tips here.');
  //   } else {
  //     setHelpContent('');
  //   }
  // };

  const toggleDarkMode = () => {
    setIsDarkMode(!isDarkMode);
  };

  return (
      <div className={`App ${isDarkMode ? 'dark-mode' : ''}`}>
        <div className="auth-container">
          <div className="auth-form">
            <h2>{isRegistering ? 'Register' : 'Login'}</h2>
            <input
                type="text"
                placeholder="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            />
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className={isRegistering && errorMessage && password !== confirmPassword ? 'error' : ''}
            />
            {isRegistering && (
                <>
                  <input
                      type="password"
                      placeholder="Confirm Password"
                      value={confirmPassword}
                      onChange={(e) => setConfirmPassword(e.target.value)}
                      className={errorMessage && password !== confirmPassword ? 'error' : ''}
                  />
                  {password !== confirmPassword && (
                      <p className="error-message">Passwords do not match</p>
                  )}
                  <input
                      type="text"
                      placeholder="Email"
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                  />
                </>
            )}
            {errorMessage && !isRegistering && (
                <p className="error-message">{errorMessage}</p>
            )}
            <button onClick={isRegistering ? handleRegister : handleLogin}>
              {isRegistering ? 'Register' : 'Login'}
            </button>
            {!isRegistering && (
                <p>
                  Don't have an account yet?{' '}
                  <a href="#" onClick={() => setIsRegistering(true)}>
                    Register!
                  </a>
                </p>
            )}
            {isRegistering && (
                <p>
                  Already have an account?{' '}
                  <a href="#" onClick={handleSwitchToLogin}>
                    Back to Login
                  </a>
                </p>
            )}
          </div>
        </div>
      </div>
  );
}