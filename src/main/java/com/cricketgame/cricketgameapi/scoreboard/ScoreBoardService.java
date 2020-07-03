package com.cricketgame.cricketgameapi.scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreBoardService {

    @Autowired
    private ScoreBoardRepository scoreBoardRepository;

    public void updateScoreBoard(ScoreBoard scoreBoard)
    {
        scoreBoardRepository.save(scoreBoard);
    }
}
