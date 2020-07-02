package com.cricketgame.cricketgameapi.match;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UniqueMatchId implements Serializable {

    @Column
    private int seriesId;

    @Column
    private int matchId;

    public UniqueMatchId(int seriesId,int matchId)
    {
        this.seriesId=seriesId;
        this.matchId=matchId;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
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
}
