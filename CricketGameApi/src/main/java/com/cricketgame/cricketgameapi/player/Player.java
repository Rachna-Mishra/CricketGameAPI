package com.cricketgame.cricketgameapi.player;

import javax.persistence.*;

@Entity
@Table(name = "playerDetails")
public class Player {

    @Id
    private int playerId;

    @Column
    private int teamId;

    @Column
    private int jerseyNumber;

    @Column
    private String playerName;

    @Column
    private String playerType;

    public Player(){}

    public Player(int playerId, int teamId, int jerseyNumber, String playerName, String playerType) {
        this.playerId = playerId;
        this.teamId = teamId;
        this.jerseyNumber = jerseyNumber;
        this.playerName = playerName;
        this.playerType = playerType;
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

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerType() {
        return playerType;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId=playerId;
    }
}
