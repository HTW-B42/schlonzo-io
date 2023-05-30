import {SESSION_TOKEN, USER_EMAIL, USER_NAME} from "./constants";

export default function Home({
                               state, setState
                               // loggedIn = false,
                               // user = null
                             }) {
  if (state.loggedIn && state.user) {
    const user = state.user
    return (
        <div>
          <div>logged in as {user[USER_NAME]}</div>
          <div>sessiontoken {state.sessionToken}</div>
          <div>email {user[USER_EMAIL]}</div>
        </div>
    );
  } else {
    return (
        <h1>you have to log in</h1>
    )
  }
}
