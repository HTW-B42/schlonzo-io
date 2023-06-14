package org.htw.quizgame.server.api;

import org.htw.quizgame.api.AuthApi;
import org.htw.quizgame.api.model.AuthSuccessDTO;
import org.htw.quizgame.api.model.BasicAuthDTO;
import org.htw.quizgame.server.service.IdentityProvider;
import org.htw.quizgame.server.data.UserRepository;
import org.htw.quizgame.server.data.UserSessionRepository;
import org.htw.quizgame.server.model.User;
import org.htw.quizgame.server.model.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;
import static org.springframework.http.ResponseEntity.*;

@RestController
public class AuthController implements AuthApi {

  private final UserSessionRepository userSessionRepository;
  private final UserRepository userRepository;
  private final IdentityProvider identityProvider;

  @Autowired
  public AuthController(UserSessionRepository userSessionRepository,
                        UserRepository userRepository, IdentityProvider identityProvider) {
    this.userSessionRepository = userSessionRepository;
    this.userRepository = userRepository;
    this.identityProvider = identityProvider;
  }

  @Override
  public ResponseEntity<AuthSuccessDTO> performLogin(BasicAuthDTO basicAuth) {
    if (isNull(basicAuth.getAuthString())) {
      return badRequest().header("msg", "no auth_string in request body").build();
    }
    String s = new String(Base64.getDecoder().decode(basicAuth.getAuthString()));
    String[] auth = s.split(":");
    if (auth.length != 2) {
      return notFound().build();
    }
    String token = UUID.randomUUID().toString();
    Optional<User> optionalUser = userRepository.findUserByUserName(auth[0]);
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      if (!user.getHashedPassword().equals(auth[1])) {
        return notFound().header("msg", "no authorization").build();
      }
      Optional<UserSession> session = identityProvider.findSessionByUser(user);
      if (session.isPresent()) {
        ResponseEntity<AuthSuccessDTO> ok = ok(new AuthSuccessDTO().user(user.toDTO())
            .sessionToken(session.get().getSessionToken()));
        System.out.println(ok);
        return ok;
      } else {
        userSessionRepository.insert(new UserSession(user, token));
        AuthSuccessDTO success = new AuthSuccessDTO()
            .sessionToken(token)
            .user(user.toDTO());
        ResponseEntity<AuthSuccessDTO> ok = ok(success);
        System.out.println(ok);
        return ok;
      }
    }
    return notFound().header("msg", "no authorization").build();
  }

}
