package org.htw.quizgame.server;

import org.htw.quizgame.api.model.ApiInfoDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ServerConfig {

  @Bean
  public ApiInfoDTO apiInfo() {
    return new ApiInfoDTO().infoMsg("hallo welt");
  }
}
