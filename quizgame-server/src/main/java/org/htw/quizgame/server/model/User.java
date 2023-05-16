package org.htw.quizgame.server.model;

import lombok.Data;
import org.htw.quizgame.api.model.RegisterUserDTO;
import org.htw.quizgame.api.model.UserDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {

  @Id
  private String userId;

  private Boolean userConfirmed;

  private String userName;

  private String userEmail;

  private String hashedPassword;


  public User(RegisterUserDTO registerUserDTO) {
    super();
    userName = registerUserDTO.getUserName();
    userEmail = registerUserDTO.getUserEmail();
    hashedPassword = registerUserDTO.getHashedPassword();
    userConfirmed = false;
  }

  public UserDTO toDTO() {
    return new UserDTO()
        .userId(userId)
        .userName(userName)
        .userEmail(userEmail)
        .userConfirmed(userConfirmed);
  }
}
