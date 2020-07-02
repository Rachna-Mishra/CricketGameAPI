package com.cricketgame.cricketgameapi.scoreboard;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Identifier implements Serializable {

    private int seriesId;
    private int matchId;
    private int inning;
    private int ballNumber;

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
