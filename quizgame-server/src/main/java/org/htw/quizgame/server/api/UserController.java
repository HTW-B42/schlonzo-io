package org.htw.quizgame.server.api;

import org.htw.quizgame.api.UserApi;
import org.htw.quizgame.api.model.RegisterUserDTO;
import org.htw.quizgame.api.model.UserDTO;
import org.htw.quizgame.server.service.IdentityProvider;
import org.htw.quizgame.server.data.GameResultRepository;
import org.htw.quizgame.server.data.UserRepository;
import org.htw.quizgame.server.model.User;
import org.htw.quizgame.server.model.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController implements UserApi {

  private final UserRepository userRepository;
  private final GameResultRepository gameSessionRepository;
  private final IdentityProvider identityProvider;

  @Autowired
  public UserController(
      UserRepository userRepository,
      GameResultRepository gameSessionRepository,
      IdentityProvider identityProvider) {
    this.userRepository = userRepository;
    this.gameSessionRepository = gameSessionRepository;
    this.identityProvider = identityProvider;
  }

  @Override
  public ResponseEntity<Void> performLogout(String sessionToken) {
    Optional<UserSession> session = identityProvider.findValidSessionBySessionToken(sessionToken);
    if (session.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    session.ifPresent(identityProvider::invalidate);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Boolean> testUsername(String name) {
    Boolean nameAvailable = !userRepository.existsUserByUserName(name);
    return ResponseEntity.ok(nameAvailable);
  }

  @Override
  public ResponseEntity<UserDTO> registerUser(RegisterUserDTO registerUserDTO) {
    User newUser = userRepository.insert(new User(registerUserDTO));
    System.out.println("neuer user angelegt: \n" + newUser.toDTO().toString());
    return ResponseEntity.ok(newUser.toDTO());
  }
}
