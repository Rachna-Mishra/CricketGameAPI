package com.cricketgame.cricketgameapi.player;

import com.cricketgame.cricketgameapi.generalutil.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    public void deletePlayer(int id) {
        Optional<Player> player=playerRepository.findById(id);
        if (player.isPresent())
            playerRepository.delete(player.get());
        }

    public void updatePlayer(Player player) {
        playerRepository.save(player);
    }

    public void updateTeamId(int playerId, int teamId) {
        Player p=playerRepository.getOne(playerId);
        List<Integer> allotedJerseyNumber=playerRepository.getAllJerseyNumberOfTeam(teamId);
        if(allotedJerseyNumber.contains(p.getJerseyNumber()))
        {
            Random r=new Random();
            int newJerseyNumber=r.nextInt(300);
            while(allotedJerseyNumber.contains(newJerseyNumber))
            {
                newJerseyNumber=r.nextInt(300);
            }
            p.setJerseyNumber(newJerseyNumber);
        }
        p.setTeamId(teamId);
        playerRepository.save(p);
    }

    public String getPlayerName(int playerId) {
        return (playerRepository.getOne(playerId)).getPlayerName();
    }
}
