package com.cricketgame.cricketgameapi.series;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seriesRecord")
public class Series
{
    @Id
    @Column(length = 32)
    private String seriesId;

    @Column(name = "numberOfMatches")
    private int numberOfMatches;

    @Column(name = "team1")
    private int team1;

    @Column(name="team2")
    private int team2;

    @Column(name = "winnerSeriesTeamId")
    private int winnerSeriesTeamId;

    public Series(){}

    public Series( String seriesId,int numberOfMatches, int team1, int team2, int winnerSeriesTeamId) {
        this.seriesId=seriesId;
        this.numberOfMatches = numberOfMatches;
        this.team1 = team1;
        this.team2 = team2;
        this.winnerSeriesTeamId = winnerSeriesTeamId;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public void setNumberOfMatches(int numberOfMatches) {
        this.numberOfMatches = numberOfMatches;
    }

    public int getTeam1() {
        return team1;
    }

    public void setTeam1(int team1) {
        this.team1 = team1;
    }

    public int getTeam2() {
        return team2;
    }

    public void setTeam2(int team2) {
        this.team2 = team2;
    }

    public int getWinnerSeriesTeamId() {
        return winnerSeriesTeamId;
    }

    public void setWinnerSeriesTeamId(int winnerSeriesTeamId) {
        this.winnerSeriesTeamId = winnerSeriesTeamId;
    }
}
