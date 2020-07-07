package com.cricketgame.cricketgameapi.scoreboard;

import javax.persistence.*;

@Entity
@Table(name = "scoreBoard")
public class ScoreBoard {

    @EmbeddedId
    private Identifier identifier;

    @Column
    private int run;

    @Column
    private int score;

   @Column
    private int playerOnStrike;

    @Column
    private int playerOnPitch;

    public ScoreBoard(){}

    public ScoreBoard(Identifier identifier, int run, int score, int playerOnStrike, int playerOnPitch) {
        this.identifier = identifier;
        this.run = run;
        this.score = score;
        this.playerOnStrike = playerOnStrike;
        this.playerOnPitch = playerOnPitch;
    }

    @Override
    public String toString() {
        return "ScoreBoard { " +
                "matchId : "+ identifier.getMatchId()+
                "inning : "+identifier.getInning()+
                "ball :" + identifier.getBallNumber() +
                ", run :" + run +
                ", score :" + score +
                ", playerOnStrike :" + playerOnStrike +
                ", playerOnPitch :" + playerOnPitch +
                '}';
    }
}
