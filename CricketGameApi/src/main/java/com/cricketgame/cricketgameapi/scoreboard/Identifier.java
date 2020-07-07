package com.cricketgame.cricketgameapi.scoreboard;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Identifier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column
    private String seriesId;

    @Column
    private int matchId;

    @Column
    private int inning;

    @Column
    private int ballNumber;

    public Identifier(){}

    public Identifier(String seriesId, int matchId, int inning, int ballNumber) {
        this.seriesId = seriesId;
        this.matchId = matchId;
        this.inning = inning;
        this.ballNumber = ballNumber;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Identifier)) return false;
        Identifier that = (Identifier) o;
        return Objects.equals(getSeriesId(), that.getSeriesId()) &&
                Objects.equals(getMatchId(), that.getMatchId()) &&
                Objects.equals(getInning(), that.getInning()) &&
                Objects.equals(getBallNumber(),that.getBallNumber());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getSeriesId(),getMatchId(),getInning(),getMatchId());
    }
}
