package com.cricketgame.cricketgameapi.team;

import javax.persistence.*;

@Entity
@Table(name = "teamDetails")
public class Team
{
 @Id
 @Column
 private int teamId;

 @Column
 private String teamName;

 public Team(){}

 public Team(int teamId, String teamName) {
  this.teamId = teamId;
  this.teamName = teamName;
 }

 public int getTeamId() {
  return teamId;
 }

 public void setTeamId(int teamId) {
  this.teamId = teamId;
 }

 public String getTeamName() {
  return teamName;
 }

 public void setTeamName(String teamName) {
  this.teamName = teamName;
 }
}
