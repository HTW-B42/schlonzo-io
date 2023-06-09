package org.htw.quizgame.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.htw.quizgame.api.model.RegisterUserDTO;
import org.htw.quizgame.api.model.UserDTO;
import org.htw.quizgame.server.model.util.ConvertsTo;
import org.htw.quizgame.server.model.util.SaveAs;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class User implements ConvertsTo<UserDTO>, SaveAs<User> {

  @Id
  private String userName;

  private Boolean userConfirmed;

  private String userEmail;

  private String hashedPassword;

  public User(RegisterUserDTO registerUserDTO) {
    super();
    userName = registerUserDTO.getUserName();
    userEmail = registerUserDTO.getUserEmail();
    hashedPassword = registerUserDTO.getHashedPassword();
    System.out.println("New User added: " + userName + " " + userEmail + " " + hashedPassword);
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
