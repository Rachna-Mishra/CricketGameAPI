package com.cricketgame.cricketgameapi.scoreboard;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Identifier implements Serializable {

    @Column
    private int seriesId;

    @Column
    private int matchId;

    @Column
    private int inning;

    @Column
    private int ballNumber;

    public Identifier(){}

    public Identifier(int seriesId, int matchId, int inning, int ballNumber) {
        this.seriesId = seriesId;
        this.matchId = matchId;
        this.inning = inning;
        this.ballNumber = ballNumber;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getInning() {
        return inning;
    }

    public void setInning(int inning) {
        this.inning = inning;
    }

    public int getBallNumber() {
        return ballNumber;
    }

    public void setBallNumber(int ballNumber) {
        this.ballNumber = ballNumber;
    }
}
