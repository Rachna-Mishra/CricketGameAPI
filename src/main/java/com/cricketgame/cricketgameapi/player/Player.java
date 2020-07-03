package com.cricketgame.cricketgameapi.player;

import javax.persistence.*;

@Entity
@Table(name = "PlayerDetails")
public class Player {

    @EmbeddedId
    private PlayerId playerId;

    @Column
    private String player_name;

    @Column
    private String player_type;

    public Player(PlayerId playerId, String player_name, String player_type) {
        this.playerId = playerId;
        this.player_name = player_name;
        this.player_type = player_type;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getPlayer_type() { return player_type; }

    public void setPlayer_type(String player_type) {
        this.player_type = player_type;
    }

}
