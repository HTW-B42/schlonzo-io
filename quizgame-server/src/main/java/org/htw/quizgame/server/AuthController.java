package org.htw.quizgame.server;

import java.util.UUID;
import org.htw.quizgame.api.model.AuthSuccess;
import org.htw.quizgame.api.model.BasicAuth;
import org.htw.quizgame.api.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController implements org.htw.quizgame.api.AuthApi {

  @Override
  public ResponseEntity<AuthSuccess> authPost(BasicAuth basicAuth) {
    User user = new User().userId(UUID.randomUUID()).userEmail("string@google.de")
        .userName("jakob_nicht").userConfirmed(false);
    AuthSuccess obj = new AuthSuccess().sessionToken("54d6f7g8h9jkß098hg7f6").user(user);
    return ResponseEntity.ok(obj);
  }
}
