package com.cricketgame.cricketgameapi.playerRecord;

import com.cricketgame.cricketgameapi.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRecordRepository extends JpaRepository<PlayerRecord, UniqueId>
{
    @Modifying
    @Query("Update PlayerRecord p set p.totalWickets=:totalWickets where p.uniqueId=:playerId")
    void updateWickets(@Param("totalWickets") int totalWicket, @Param("playerId") UniqueId playerId);

}
