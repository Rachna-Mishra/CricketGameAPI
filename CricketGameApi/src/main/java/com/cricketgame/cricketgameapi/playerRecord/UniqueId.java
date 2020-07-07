package com.cricketgame.cricketgameapi.playerRecord;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UniqueId implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    private String seriesId;

    @Column
    private int matchId;

    @Column
    private int playerId;

    public UniqueId(){}

    public UniqueId(String seriesId, int matchId, int playerId) {
        this.seriesId = seriesId;
        this.matchId = matchId;
        this.playerId = playerId;
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

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public String toString()
    {
        return getSeriesId()+" "+getMatchId()+" "+getPlayerId();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getSeriesId(),getMatchId(),getPlayerId());
    }
}
