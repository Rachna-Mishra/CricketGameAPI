package com.cricketgame.cricketgameapi.team;

import javax.persistence.*;

@Entity
@Table(name = "team_details")
public class Team
{
 @Id
 @Column
 @GeneratedValue(strategy=GenerationType.AUTO)
 private int team_id;

 @Column
 private String team_name;

 public int getTeam_id() {

  return team_id;
 }

 public String getTeam_name() {
  return team_name;
 }

 public void setTeam_name(String team_name) {
  this.team_name = team_name;
 }

 public void setTeam_id(int team_id) {
  this.team_id = team_id;
 }


}
