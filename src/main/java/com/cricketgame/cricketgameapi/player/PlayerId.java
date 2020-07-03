package com.cricketgame.cricketgameapi.player;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PlayerId {

    @Column
    private int teamId;
    @Column
    private int jerseyNumber;

    public PlayerId(){};

    public PlayerId(int teamId, int jerseyNumber) {
        this.teamId = teamId;
        this.jerseyNumber = jerseyNumber;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }
}
