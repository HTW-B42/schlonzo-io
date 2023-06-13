import React from "react";

import * as PropTypes from "prop-types";


class Game extends React.Component {
  render() {
    return <div className={"game"}>
      <h1>Game</h1>
      <div className={"game-content"}>
        <div className={"game-content-questions"}>
          <div className={"game-content-question"}>
            <div className={"game-content-question-text"}>Frage 1</div>
            <div className={"game-content-question-text"}>Frage 2</div>
            <div className={"game-content-question-text"}>Frage 3</div>
            <div className={"game-content-question-text"}>Frage 4</div>
          </div>
        </div>
      </div>
    </div>;
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

export default Game;