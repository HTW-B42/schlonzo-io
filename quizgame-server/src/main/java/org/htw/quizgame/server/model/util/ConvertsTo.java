package org.htw.quizgame.server.model.util;

import java.io.Serializable;

public interface ConvertsTo<DTO> extends Serializable {
  DTO toDTO();
}
