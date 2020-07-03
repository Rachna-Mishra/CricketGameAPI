package com.cricketgame.cricketgameapi.playerRecord;

import com.cricketgame.cricketgameapi.player.PlayerId;
import com.cricketgame.cricketgameapi.player.PlayerRepository;
import com.cricketgame.cricketgameapi.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerRecordService {
    
    @Autowired
    private PlayerRecordRepository playerRecordRepository;
    @Autowired
    private PlayerService playerService;

    public void addPlayerRecord(int teamIdOfBattingTeam) {
        playerService.getPlayerByTeamId(teamIdOfBattingTeam);
    }

    public void updatePlayerRecord(PlayerId currentPlayerOnPitch, int totalScoreOfPlayerOnPitch, int i, int totalBallsByPlayerOnPitch) {
    }
}
