package com.cricketgame.cricketgameapi.player;

import com.cricketgame.cricketgameapi.team.Team;
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

    public void addPlayer(Player player) {
        player.setPlayer_id(player.getTeam_id()+""+player.getJersey_no());
        playerRepository.save(player);
    }

    public void deletePlayer(String id) {
        Optional<Player> player=playerRepository.findById(id);
        if (player.isPresent())
            playerRepository.delete(player.get());
        }

    public void updatePlayer(Player player) {
        playerRepository.save(player);
    }
}
