package org.htw.quizgame.server.api;

import org.htw.quizgame.api.UserApi;
import org.htw.quizgame.api.model.RegisterUserDTO;
import org.htw.quizgame.api.model.UserDTO;
import org.htw.quizgame.api.model.UserNameDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {
    @Override
    public ResponseEntity<Void> userLogoutGet() {
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Boolean> userNameTestPost(UserNameDTO userNameDTO) {

        return ResponseEntity.ok(true);
    }

    @Override
    public ResponseEntity<UserDTO> userRegisterPost(RegisterUserDTO registerUserDTO) {
        return null;
    }
}
