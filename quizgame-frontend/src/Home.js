function Home(state) {

  return (
      <>
        <div>logged in as {state.username}</div>
        <div>sessiontoken {state.session_token}</div>
        <div>email {state.email}</div>
      </>
  );
}

export default Home;