package org.htw.quizgame.server.model;

import java.io.Serializable;

public interface ConvertsTo<DTO> extends Serializable {
  DTO toDTO();
}
