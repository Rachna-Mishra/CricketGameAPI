package com.cricketgame.cricketgameapi.scoreboard;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ScoreBoard")
public class ScoreBoard {

    @EmbeddedId
    private Identifier identifier;

    @Column
    private int run;

    @Column
    private int playerOnStrike;

    @Column
    private int playerOnPitch;

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public int getPlayerOnStrike() {
        return playerOnStrike;
    }

    public void setPlayerOnStrike(int playerOnStrike) {
        this.playerOnStrike = playerOnStrike;
    }

    public int getPlayerOnPitch() {
        return playerOnPitch;
    }

    public void setPlayerOnPitch(int playerOnPitch) {
        this.playerOnPitch = playerOnPitch;
    }
}
