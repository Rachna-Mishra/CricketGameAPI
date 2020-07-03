package com.cricketgame.cricketgameapi.playerRecord;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UniqueId {
    @Column
    private int seriesId;

    @Column
    private int matchId;

    @Column
    private String playerId;

    public UniqueId(){}

    public UniqueId(int seriesId, int matchId, String playerId) {
        this.seriesId = seriesId;
        this.matchId = matchId;
        this.playerId = playerId;
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

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
