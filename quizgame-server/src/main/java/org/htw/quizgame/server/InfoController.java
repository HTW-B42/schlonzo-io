package org.htw.quizgame.server;

import static org.springframework.http.ResponseEntity.ok;

import org.htw.quizgame.api.InfoApi;
import org.htw.quizgame.api.model.ApiInfoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController implements InfoApi {

  @Override
  public ResponseEntity<ApiInfoDTO> infoGet() {
    return ok(new ApiInfoDTO().infoMsg("hallo welt"));
  }
}
