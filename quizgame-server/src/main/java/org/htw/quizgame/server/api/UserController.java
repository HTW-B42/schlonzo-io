package org.htw.quizgame.server.api;

import org.htw.quizgame.api.UserApi;
import org.htw.quizgame.api.model.RegisterUserDTO;
import org.htw.quizgame.api.model.UserDTO;
import org.htw.quizgame.api.model.UserNameDTO;
import org.htw.quizgame.server.data.UserRepository;
import org.htw.quizgame.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {

  private final UserDTO exampleUser;
  private final UserRepository userRepository;

  @Autowired
  public UserController(UserDTO exampleUser, UserRepository userRepository) {
    this.exampleUser = exampleUser;
    this.userRepository = userRepository;
  }

  @Override
  public ResponseEntity<Void> userLogoutGet() {
    return ResponseEntity.ok(null);
  }

  @Override
  public ResponseEntity<Boolean> userNameTestPost(UserNameDTO userNameDTO) {
    return ResponseEntity.ok(true);
  }

  @Override
  public ResponseEntity<UserDTO> userRegisterPost(RegisterUserDTO registerUserDTO) {
    User newUser = userRepository.insert(new User(registerUserDTO));
    System.out.println("neuer user angelegt: \n" + newUser.toDTO().toString());
    return ResponseEntity.ok(newUser.toDTO());
  }
}
