package org.htw.quizgame.server;

import java.util.Optional;
import org.htw.quizgame.server.data.UserSessionRepository;
import org.htw.quizgame.server.model.User;
import org.htw.quizgame.server.model.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdentityProvider {

  private final UserSessionRepository userSessionRepository;

  @Autowired
  public IdentityProvider(UserSessionRepository userSessionRepository) {
    this.userSessionRepository = userSessionRepository;
  }

  public Optional<User> findUserForSessionToken(String token) {
    Optional<UserSession> session = userSessionRepository.findUserSessionBySessionToken(token);
    if (session.isPresent()) {
      UserSession userSession = session.get();
      if (!userSession.isValid()) {
        userSessionRepository.delete(userSession);
        return Optional.empty();
      }
      userSession.touch();
      return Optional.ofNullable(userSession.getUser());
    }
    return Optional.empty();
  }

  public Optional<UserSession> findSessionForUser(User user) {
    Optional<UserSession> session = userSessionRepository.findUserSessionByUser(user);
    if (session.isPresent()) {
      UserSession userSession = session.get();
      if (userSession.isValid()) {
        return session;
      } else {
        userSessionRepository.delete(userSession);
      }
    }
    return Optional.empty();
  }

}
