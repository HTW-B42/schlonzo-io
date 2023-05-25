package org.htw.quizgame.server.model;

import java.io.Serializable;

public interface SaveAs<ENTITY> extends Serializable {
  ENTITY toEntity();
}
