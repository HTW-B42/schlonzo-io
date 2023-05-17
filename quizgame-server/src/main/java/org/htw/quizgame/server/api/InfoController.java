package org.htw.quizgame.server.api;

import static org.springframework.http.ResponseEntity.ok;

import org.htw.quizgame.api.InfoApi;
import org.htw.quizgame.api.model.ApiInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController implements InfoApi {

  private final ApiInfoDTO apiInfo;

  @Autowired
  public InfoController(ApiInfoDTO apiInfo) {
    this.apiInfo = apiInfo;
  }

  @Override
  public ResponseEntity<ApiInfoDTO> getApiInfo() {
    return ok(apiInfo);
  }
}
