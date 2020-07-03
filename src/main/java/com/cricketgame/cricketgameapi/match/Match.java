package com.cricketgame.cricketgameapi.match;

import javax.persistence.*;

@Entity
@Table(name = "MatchRecord")
public class Match {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UniqueMatchId uniqueMatchId;

    @Column
    private int battingTeamId;

    @Column
    private int battingTeamScore;

    @Column
    private int battingTeamWickets;

    @Column
    private int bowlingTeamScore;

    @Column
    private int bowlingTeamWickets;

    @Column
    private int winningTeam;

    public Match(){}

    public Match( UniqueMatchId uniqueMatchId,int battingTeamId, int battingTeamScore, int battingTeamWickets,int bowlingTeamScore, int bowlingTeamWickets, int winningTeam) { this.uniqueMatchId=uniqueMatchId;
     this.battingTeamId=battingTeamId;
     this.battingTeamScore=battingTeamScore;
     this.battingTeamWickets=battingTeamWickets;
     this.bowlingTeamScore=bowlingTeamScore;
     this.battingTeamWickets=bowlingTeamWickets;
     this.winningTeam=winningTeam;

    }

    public int getBattingTeamId() {
        return battingTeamId;
    }

    public void setBattingTeamId(int battingTeamId) {
        this.battingTeamId = battingTeamId;
    }

    public int getBattingTeamScore() {
        return battingTeamScore;
    }

    public void setBattingTeamScore(int battingTeamScore) {
        this.battingTeamScore = battingTeamScore;
    }

    public int getBattingTeamWickets() {
        return battingTeamWickets;
    }

    public void setBattingTeamWickets(int battingTeamWickets) {
        this.battingTeamWickets = battingTeamWickets;
    }

    public int getBowlingTeamScore() {
        return bowlingTeamScore;
    }

    public void setBowlingTeamScore(int bowlingTeamScore) {
        this.bowlingTeamScore = bowlingTeamScore;
    }

    public int getBowlingTeamWickets() {
        return bowlingTeamWickets;
    }

    public void setBowlingTeamWickets(int bowlingTeamWickets) {
        this.bowlingTeamWickets = bowlingTeamWickets;
    }

    public int getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam(int winningTeam) {
        this.winningTeam = winningTeam;
    }

    public UniqueMatchId getUniqueMatchId() {
        return uniqueMatchId;
    }

    public void setUniqueMatchId(UniqueMatchId uniqueMatchId) {
        this.uniqueMatchId = uniqueMatchId;
    }
}
