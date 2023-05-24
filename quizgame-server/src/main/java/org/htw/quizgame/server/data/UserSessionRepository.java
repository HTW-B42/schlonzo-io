package org.htw.quizgame.server.data;

import java.util.Optional;

import org.htw.quizgame.server.model.User;
import org.htw.quizgame.server.model.UserSession;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserSessionRepository extends MongoRepository<UserSession, String> {

  Optional<UserSession> findUserSessionBySessionToken(String token);
  Optional<UserSession> findUserSessionByUser(User user);

}
