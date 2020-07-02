package com.cricketgame.cricketgameapi.player;

import javax.persistence.*;

@Entity
@Table(name = "player_details")
public class Player
{
@Id
@Column
@GeneratedValue(strategy = GenerationType.AUTO)
private String player_id;

@Column
private int team_id;

@Column
private String player_name;

@Column
private String player_type;

@Column
private int jersey_no;

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getPlayer_type() {
        return player_type;
    }

    public void setPlayer_type(String player_type) {
        this.player_type = player_type;
    }

    public int getJersey_no() {
        return jersey_no;
    }

    public void setJersey_no(int jersey_no) {
        this.jersey_no = jersey_no;
    }
}
