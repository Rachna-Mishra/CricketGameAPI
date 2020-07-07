package com.cricketgame.cricketgameapi.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Query("select p from Player p where p.teamId = ?1")
    Collection<Player> getPlayerByTeamId(int teamId);

    @Query("select p.jerseyNumber from Player p where teamId = ?1")
    List<Integer> getAllJerseyNumberOfTeam(int teamId);
}
