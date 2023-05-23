class Client {
  constructor(baseURL) {
    this.baseURL = baseURL;
  }

  async authenticate(user, password) {
    const response = await fetch(`${this.baseURL}/auth`, {
      headers: {
        'Authorization': 'Basic ' + btoa(user + ':' + password),
      },
    });
    if (response.ok) {
      const data = await response.json();
      return data;
    } else {
      throw new Error("Authentication failed");
    }
  }



  async registerUser(newUser) {
    const response = await fetch(`${this.baseURL}/users/register`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(newUser)
    });

    if (response.ok) {
      return "User registered successfully";
    } else {
      throw new Error("User registration failed");
    }
  }

  async checkUsername(username) {
    const response = await fetch(`${this.baseURL}/users/register/${username}`);
    if (response.ok) {
      return "Username available";
    } else {
      throw new Error("Username not available");
    }
  }

  async logout() {
    const response = await fetch(`${this.baseURL}/users/logout`);
    if (response.ok) {
      return "Logged out successfully";
    } else {
      throw new Error("Logout failed");
    }
  }

  async startGame() {
    const response = await fetch(`${this.baseURL}/game/start`);
    if (response.ok) {
      const data = await response.json();
      return data;
    } else {
      throw new Error("Game start failed");
    }
  }

  async getQuestion() {
    const response = await fetch(`${this.baseURL}/game/question`);
    if (response.ok) {
      const data = await response.json();
      return data;
    } else {
      throw new Error("Get question failed");
    }
  }

  async sendAnswer(answer) {
    const response = await fetch(`${this.baseURL}/game/question`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(answer)
    });

    if (response.ok) {
      const data = await response.json();
      return data;
    } else {
      throw new Error("Answer submission failed");
    }
  }
}

//const client = new Client('http://localhost:4711/v1');

// Use the API client
// client.authenticate('username', 'password');
console.log(Client);
export default Client;
