package org.htw.quizgame.server.model;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class UserSession {

  @Id
  private String userSessionID;
  private final User user;
  private final String sessionToken;
  private LocalDateTime expires;

  public UserSession(User user, String sessionToken) {
    super();
    this.user = user;
    this.sessionToken = sessionToken;
    touch();
  }

  public void touch() {
    expires = LocalDateTime.now().plusMinutes(10);
  }

  public void logout() {
    expires = LocalDateTime.now();
  }

  public boolean isValid() {
    return expires.isAfter(LocalDateTime.now());
  }

}
