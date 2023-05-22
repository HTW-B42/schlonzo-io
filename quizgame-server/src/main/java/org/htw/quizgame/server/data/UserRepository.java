package org.htw.quizgame.server.data;

import java.util.Optional;
import org.htw.quizgame.server.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

  Optional<User> findUserByUserNameAndHashedPassword(String username, String hashedpwd);
  boolean existsUserByUserName(String username);
}
