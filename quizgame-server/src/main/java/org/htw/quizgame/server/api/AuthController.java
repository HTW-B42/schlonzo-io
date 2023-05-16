package org.htw.quizgame.server.api;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.util.Base64;
import java.util.Optional;
import java.util.UUID;
import org.htw.quizgame.api.AuthApi;
import org.htw.quizgame.api.model.AuthSuccessDTO;
import org.htw.quizgame.api.model.BasicAuthDTO;
import org.htw.quizgame.api.model.RegisterUserDTO;
import org.htw.quizgame.api.model.UserDTO;
import org.htw.quizgame.server.data.UserRepository;
import org.htw.quizgame.server.data.UserSessionRepository;
import org.htw.quizgame.server.model.User;
import org.htw.quizgame.server.model.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

  private final UserSessionRepository userSessionRepository;
  private final UserRepository userRepository;

  @Autowired
  public AuthController(UserSessionRepository userSessionRepository,
      UserRepository userRepository) {
    this.userSessionRepository = userSessionRepository;
    this.userRepository = userRepository;
  }

  @Override
  public ResponseEntity<AuthSuccessDTO> authPost(BasicAuthDTO basicAuth) {
    String s = new String(Base64.getDecoder().decode(basicAuth.getAuthString()));
    String[] auth = s.split(":");
    System.out.println(s);
    if (auth.length != 2) {
      return ResponseEntity.notFound().build();
    }
    String token = UUID.randomUUID().toString();
//    Optional<User> user = userRepository.findUserByUserNameAndHashedPassword(auth[0], auth[1]);

//    if (user.isPresent()) {
      userSessionRepository.insert(new UserSession(new User(new RegisterUserDTO()), token));
      AuthSuccessDTO success = new AuthSuccessDTO()
          .sessionToken(token)
          .user(new UserDTO().userId("egal"));
      return ok(success);
//    }
//    return notFound().build();
  }
}
