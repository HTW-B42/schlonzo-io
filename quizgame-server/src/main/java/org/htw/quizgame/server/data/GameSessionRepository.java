package org.htw.quizgame.server.data;

import java.util.Optional;
import org.htw.quizgame.server.model.GameSession;
import org.htw.quizgame.server.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameSessionRepository extends MongoRepository<GameSession, String> {

  Optional<GameSession> findGameSessionByLobbyMembersContains(User user);

}
