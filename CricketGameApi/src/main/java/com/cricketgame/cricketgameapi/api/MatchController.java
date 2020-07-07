package com.cricketgame.cricketgameapi.api;

import com.cricketgame.cricketgameapi.match.Match;
import com.cricketgame.cricketgameapi.match.MatchService;
import com.cricketgame.cricketgameapi.scoreboard.ScoreBoard;
import com.cricketgame.cricketgameapi.scoreboard.ScoreBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;
    @Autowired
    private ScoreBoardService scoreBoardService;

    @RequestMapping("Match/all")
    public List<Match> getAllMatchRecord()
    {
        return matchService.getAllMatchRecord();
    }

    @RequestMapping("series/match/winner/{seriesId}/{matchId}")
    public String getWinnerOfMatch(@PathVariable String seriesId,@PathVariable int matchId)
    {
        return matchService.getWinnerOfMatch(seriesId,matchId);
    }

    @RequestMapping(path="/organizeMatch/{numberOfMatches}/{numberOfOvers}",method = RequestMethod.GET)
    public String startMatch(@PathVariable int numberOfMatches,@PathVariable int numberOfOvers) throws ParseException {
        matchService.organizeMatch(numberOfMatches,numberOfOvers);
        return "Match Over and Information is stored in Database";
    }

    @RequestMapping(path="/getScoreBoard/{seriesId}/{matchId}/{inning}/{ballNumber}",method = RequestMethod.GET)
        public String getScoreBoardForInningData(@PathVariable String seriesId,@PathVariable int matchId,@PathVariable int inning,@PathVariable int ballNumber)
        {
           return scoreBoardService.getScoreBoardForInningData(seriesId,matchId,inning,ballNumber);
        }

}
