package org.htw.quizgame.server.api;

import org.htw.quizgame.api.UserApi;
import org.htw.quizgame.api.model.RegisterUserDTO;
import org.htw.quizgame.api.model.UserDTO;
import org.htw.quizgame.api.model.UserNameDTO;
import org.htw.quizgame.server.IdentityProvider;
import org.htw.quizgame.server.data.GameSessionRepository;
import org.htw.quizgame.server.data.UserRepository;
import org.htw.quizgame.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController implements UserApi {

  private final UserRepository userRepository;
  private final GameSessionRepository gameSessionRepository;
  private final IdentityProvider identityProvider;

  @Autowired
  public UserController(
      UserRepository userRepository,
      GameSessionRepository gameSessionRepository,
      IdentityProvider identityProvider) {
    this.userRepository = userRepository;
    this.gameSessionRepository = gameSessionRepository;
    this.identityProvider = identityProvider;
  }

  @Override
  public ResponseEntity<Void> performLogout(String sessionToken) {
    Optional<User> user = identityProvider.identifyBySessionToken(sessionToken);
    if (user.isPresent()) {
      // TODO logout and end gamesession

      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

  @Override
  public ResponseEntity<Boolean> testUsername(UserNameDTO userNameDTO) {
    String username = userNameDTO.getUserName();
    boolean exists = userRepository.existsUserByUserName(username);
    System.out.println(exists);
    return ResponseEntity.ok(!exists);
  }

  @Override
  public ResponseEntity<UserDTO> registerUser(RegisterUserDTO registerUserDTO) {
    User newUser = userRepository.insert(new User(registerUserDTO));
    System.out.println("neuer user angelegt: \n" + newUser.toDTO().toString());
    return ResponseEntity.ok(newUser.toDTO());
  }
}
