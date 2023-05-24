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

  public Optional<UserSession> findValidSessionBySessionToken(String token) {
    Optional<UserSession> session = userSessionRepository.findUserSessionBySessionToken(token);
    if (session.isEmpty()) {
      return Optional.empty();
    }
    UserSession userSession = session.get();
    if (!userSession.isValid()) {
      userSessionRepository.delete(userSession);
      return Optional.empty();
    }
    userSession.touch();
    return Optional.of(userSession);
  }

  public Optional<UserSession> findSessionByUser(User user) {
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

  public void invalidate(UserSession userSession) {
    userSessionRepository.delete(userSession);
  }
}
