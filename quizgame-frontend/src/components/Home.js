import React from 'react';
import './Home.css'
import {DefaultApi} from 'quizgame-client-api/src';
import {useNavigate} from "react-router-dom";

const client  = new DefaultApi();

const Home = (props) => {
	const state = props.state;
	const navigate = useNavigate();
	const handleGameStart = async (string) => {
		await client.startGame(state.sessionToken).then(res => props.onGameStart(res))
		navigate('/game')
	};
	
	let user, error;

	if (state.loggedIn && state.user) {
		user = state.user;
		console.log(user.user_name);
	} else {
		error = "Not logged in";
	}

	if (error) {
		return <div>{error}</div>;
	} else {
		return (
			<div>
				<div className={`start-screen`}>
					<h1 className="headline">Shlonzo.io</h1>
					<button className="button" onClick={() => handleGameStart(state.sessionToken)}>Lets GOOOOOO!!!</button>
					<p className={"username"}>You are logged in as: {user.user_name}</p>
				</div>
			</div>
		);
	}
};
export default Home;
