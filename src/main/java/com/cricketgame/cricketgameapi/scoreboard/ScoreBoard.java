package com.cricketgame.cricketgameapi.scoreboard;

import com.cricketgame.cricketgameapi.player.Player;
import com.cricketgame.cricketgameapi.player.PlayerId;

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
    private int score;

    @Column
    private PlayerId playerOnStrike;

    @Column
    private PlayerId playerOnPitch;

    public ScoreBoard(){}

    public ScoreBoard(Identifier identifier, int run, int score, PlayerId playerOnStrike, PlayerId playerOnPitch) {
        this.identifier = identifier;
        this.run = run;
        this.score = score;
        this.playerOnStrike = playerOnStrike;
        this.playerOnPitch = playerOnPitch;
    }

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public PlayerId getPlayerOnStrike() {
        return playerOnStrike;
    }

    public void setPlayerOnStrike(PlayerId playerOnStrike) {
        this.playerOnStrike = playerOnStrike;
    }

    public PlayerId getPlayerOnPitch() {
        return playerOnPitch;
    }

    public void setPlayerOnPitch(PlayerId playerOnPitch) {
        this.playerOnPitch = playerOnPitch;
    }
}
