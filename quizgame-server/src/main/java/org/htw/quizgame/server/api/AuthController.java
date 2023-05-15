package org.htw.quizgame.server.api;

import static org.springframework.http.ResponseEntity.ok;

import java.util.Base64;
import java.util.UUID;
import org.htw.quizgame.api.AuthApi;
import org.htw.quizgame.api.model.AuthSuccessDTO;
import org.htw.quizgame.api.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

  private final UserDTO exampleUser;

  @Autowired
  public AuthController(UserDTO exampleUser){

    this.exampleUser = exampleUser;
  }

  @Override
  public ResponseEntity<AuthSuccessDTO> authPost(
      org.htw.quizgame.api.model.BasicAuthDTO basicAuth) {
    System.out.println(new String(Base64.getDecoder().decode(basicAuth.getAuthString())));

    AuthSuccessDTO success = new AuthSuccessDTO()
        .sessionToken(UUID.randomUUID().toString())
        .user(exampleUser);
    return ok(success);
  }
}
