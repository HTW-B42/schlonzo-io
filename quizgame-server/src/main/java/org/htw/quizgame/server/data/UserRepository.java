package org.htw.quizgame.server.data;

import java.util.Optional;
import org.htw.quizgame.server.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

  Optional<User> findUserByUserName(String s);
  Boolean existsUserByUserName(String username);

  Boolean existsUserByUserEmail(String mail);
}
