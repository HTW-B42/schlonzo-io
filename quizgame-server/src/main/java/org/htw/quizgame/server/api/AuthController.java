package org.htw.quizgame.server.api;

import static org.springframework.http.ResponseEntity.ok;

import java.util.Base64;
import java.util.UUID;
import org.htw.quizgame.api.AuthApi;
import org.htw.quizgame.api.model.AuthSuccessDTO;
import org.htw.quizgame.api.model.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

  @Override
  public ResponseEntity<AuthSuccessDTO> authPost(
      org.htw.quizgame.api.model.BasicAuthDTO basicAuth) {
    System.out.println(new String(Base64.getDecoder().decode(basicAuth.getAuthString())));
    UserDTO user = new UserDTO()
        .userId(UUID.randomUUID())
        .userName("jakob_nicht")
        .userEmail("string@google.de")
        .userConfirmed(false);
    AuthSuccessDTO success = new AuthSuccessDTO()
        .sessionToken(UUID.randomUUID().toString())
        .user(user);
    return ok(success);
  }
}
