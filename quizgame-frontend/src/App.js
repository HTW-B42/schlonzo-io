import React, { useState } from 'react';
import Client from './Client';
import './App.css';
console.log(Client);

function App() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [isRegistering, setIsRegistering] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const client = new Client('http://localhost:4711/v1');

  const handleLogin = async () => {
    try {
      const response = await client.authenticate(username, password);
      console.log(response);
    } catch (error) {
      console.error('Error:', error);
    }
  };

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
      };
      try {
        const response = await client.registerUser(newUser);
        console.log(response);
      } catch (error) {
        console.error('Error:', error);
      }
    }
  };

  const handleSwitchToLogin = () => {
    setUsername('');
    setPassword('');
    setConfirmPassword('');
    setErrorMessage('');
    setIsRegistering(false);
  };



  const switchButtonText = isRegistering ? 'Back to Login' : 'Register';

  return (
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
  );  
}

export default App;

