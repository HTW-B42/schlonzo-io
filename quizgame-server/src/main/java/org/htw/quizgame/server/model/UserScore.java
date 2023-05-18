package org.htw.quizgame.server.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class UserScore {

    private String userId;

    @Setter
    private int score;
}
