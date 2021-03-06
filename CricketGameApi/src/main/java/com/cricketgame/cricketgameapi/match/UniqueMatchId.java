package com.cricketgame.cricketgameapi.match;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Table
public class UniqueMatchId implements Serializable {

    @Column
    private String seriesId;

    @Column
    private int matchId;

    public UniqueMatchId(){}

    public UniqueMatchId(String seriesId,int matchId)
    {
        this.seriesId=seriesId;
        this.matchId=matchId;
    }

    public String  getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof UniqueMatchId)) return false;
        UniqueMatchId that = (UniqueMatchId) o;
        return Objects.equals(getSeriesId(), that.getSeriesId() )&&
                Objects.equals(getMatchId(),that.getMatchId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSeriesId(),getMatchId());
    }

    @Override
    public String toString()
    {
        return seriesId+" "+matchId;
    }
}
