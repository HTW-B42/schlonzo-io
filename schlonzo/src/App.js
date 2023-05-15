import React, { useState } from 'react';
import Client from './Client';
import './App.css'; 
console.log(Client); 

function App() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');

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
  };

  return (
    <div className="auth-container">
      <form className="auth-form">
        <h2>User Authentication</h2>
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
        />
        <input
          type="text"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <button onClick={handleLogin}>Login</button>
        <button onClick={handleRegister} style={{ marginTop: '10px' }}>
          Register
        </button>
        <p>Don't have an account? Register now!</p>
      </form>
    </div>
  );  
}

export default App;
