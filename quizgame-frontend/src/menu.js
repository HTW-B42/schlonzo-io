import React, {useMemo, useState} from 'react';

class Menu extends React.Component {

  // add a constructor
  constructor(props) {
    super(props);
    this.state = {
      loggedIn: false,
      sessionToken: '',
      isDarkMode: false,
      isLoading: false,
      user: null,
      gameSession: null
    };

    this.handleLogin = this.handleLogin.bind(this);
    this.handleLogout = this.handleLogout.bind(this);
    this.handleDarkMode = this.handleDarkMode.bind(this);
    // 
  }

}