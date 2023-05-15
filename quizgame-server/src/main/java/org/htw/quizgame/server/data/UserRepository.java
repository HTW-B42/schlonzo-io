package org.htw.quizgame.server.data;

import org.htw.quizgame.server.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
