package com.cricketgame.cricketgameapi.playerRecord;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PlayerRecord")
public class PlayerRecord {

    @EmbeddedId
    private UniqueId uniqueId;

    @Column
    private int totalRun;

    @Column
    private int totalBalls;

    @Column
    private int totalWickets;

    public PlayerRecord(){}

    public PlayerRecord(UniqueId uniqueId, int totalRun, int totalBalls, int totalWickets) {
        this.uniqueId = uniqueId;
        this.totalRun = totalRun;
        this.totalBalls = totalBalls;
        this.totalWickets = totalWickets;
    }

    public UniqueId getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(UniqueId uniqueId) {
        this.uniqueId = uniqueId;
    }

    public int getTotalRun() {
        return totalRun;
    }

    public void setTotalRun(int totalRun) {
        this.totalRun = totalRun;
    }

    public int getTotalBalls() {
        return totalBalls;
    }

    public void setTotalBalls(int totalBalls) {
        this.totalBalls = totalBalls;
    }

    public int getTotalWickets() {
        return totalWickets;
    }

    public void setTotalWickets(int totalWickets) {
        this.totalWickets = totalWickets;
    }
}
