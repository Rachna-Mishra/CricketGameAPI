package com.cricketgame.cricketgameapi.series;

import javax.persistence.*;

@Entity
@Table(name = "SeriesRecord")
public class Series
{
@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int seriesId;

    @Column(name = "NumberOfMatches")
    private int numberOfMatches;

    @Column(name = "Team1")
    private int team1;

    @Column(name="Team2")
    private int team2;

    @Column(name = "WinnerSeriesTeamId")
    private int winnerSeriesTeamId;

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
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
