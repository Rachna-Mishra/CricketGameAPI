package com.cricketgame.cricketgameapi.scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScoreBoardService {

    @Autowired
    private ScoreBoardRepository scoreBoardRepository;

    public void updateScoreBoard(ScoreBoard scoreBoard)
    {
        scoreBoardRepository.save(scoreBoard);
    }

    public String getScoreBoardForInningData(String seriesId,int matchId,int inning,int ball)
    {
        Identifier identifier=new Identifier(seriesId,matchId,inning,ball);
        Optional<ScoreBoard> data=scoreBoardRepository.findById(identifier);
        if(data.isPresent())
        {
            return data.get().toString();
        }
        return "This provided data is not valid...Either SeriesId, matchId is Wrong";
    }
}
