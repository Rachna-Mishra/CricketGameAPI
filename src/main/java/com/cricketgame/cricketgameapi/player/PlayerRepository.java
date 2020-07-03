package com.cricketgame.cricketgameapi.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PlayerRepository extends JpaRepository<Player, PlayerId> {

    @Query(value = "select * from player_details p where p.teamId = ?1")
    Collection<Player> getPlayerByTeamId(int teamId);
}
