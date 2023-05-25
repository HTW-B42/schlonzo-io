package org.htw.quizgame.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.htw.quizgame.api.model.RegisterUserDTO;
import org.htw.quizgame.api.model.UserDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class User implements ConvertsTo<UserDTO>, SaveAs<User> {

  @Id
  private String userName;

  private Boolean userConfirmed = false;

  private String userEmail;

  private String hashedPassword;

  public User(RegisterUserDTO registerUserDTO) {
    super();
    userName = registerUserDTO.getUserName();
    userEmail = registerUserDTO.getUserEmail();
    hashedPassword = registerUserDTO.getHashedPassword();
    userConfirmed = false;
  }

  @Override
  public UserDTO toDTO() {
    return new UserDTO()
        .userName(userName)
        .userEmail(userEmail)
        .userConfirmed(userConfirmed);
  }

  @Override
  public User toEntity() {
    return this;
  }
}
