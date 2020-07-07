package com.cricketgame.cricketgameapi.playerRecord;

import com.cricketgame.cricketgameapi.player.Player;
import com.cricketgame.cricketgameapi.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerRecordService {

    @Autowired
    private PlayerRecordRepository playerRecordRepository;

    @Autowired
    private PlayerService playerService;

    public void addPlayerRecord(String seriesId,int matchId,int teamIdOfBattingTeam) {
        List<Player> playerList=playerService.getPlayerByTeamId(teamIdOfBattingTeam);
        for(Player p:playerList)
        {
            PlayerRecord playerRecord=new PlayerRecord(new UniqueId(seriesId,matchId,p.getPlayerId()));
            playerRecordRepository.save(playerRecord);
        }
    }

    public void updatePlayerRecord(PlayerRecord playerRecord ){
        playerRecordRepository.save(playerRecord);
    }

    @Transactional
    public void updateWicketsData(String seriesId, int matchId, List<Player> bowlers, int bowlerNumber, int[] totalWickets) {
       int i=0;
        for(Player p:bowlers)
       {
           if(i<bowlerNumber) {
               UniqueId uniqueId = new UniqueId(seriesId, matchId, p.getPlayerId());
               playerRecordRepository.updateWickets(totalWickets[i++], uniqueId);
           }
           else
               break;
       }
    }
}
