import React from 'react';
import './index.css';
import Login from './Login.js';
import {BrowserRouter as Router, Link, Route, Routes} from 'react-router-dom';
import reportWebVitals from './reportWebVitals';
import ReactDOM from 'react-dom/client';
import Home from "./Home";


const state = {
  loggedIn: false
}


// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
const App = () => {
  return (
        // TODO <Header></Header>
      <>
        <Router>
          <Link to={'/'}>login</Link>
          <Link to={'/home'}>home</Link>
          <Routes>
            <Route path={'/'} element={<Login state={state}/>}></Route>
            <Route path={'/home'} element={<Home state={state}/>}></Route>
          </Routes>
        </Router>
      </>
  );
};
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App/>);

reportWebVitals();
