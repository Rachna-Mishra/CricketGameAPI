package com.cricketgame.cricketgameapi.player;

import com.cricketgame.cricketgameapi.team.Team;
import javafx.print.PageLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
private PlayerRepository playerRepository;

    public List<Player> getAllPlayers()
    {
        List<Player> playerList=new ArrayList<>();
        playerList.addAll(playerRepository.findAll());
        return playerList;
    }

    public List<Player> getPlayerByTeamId(int teamId)
    {
        List<Player> playerList=new ArrayList<>();
        playerList.addAll(playerRepository.getPlayerByTeamId(teamId));
        return playerList;
    }

    public void addPlayer(Player player) {
        playerRepository.save(player);
    }

    public void deletePlayer(PlayerId id) {
        Optional<Player> player=playerRepository.findById(id);
        if (player.isPresent())
            playerRepository.delete(player.get());
        }

    public void updatePlayer(Player player) {
        playerRepository.save(player);
    }

}
