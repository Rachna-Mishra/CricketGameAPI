package com.cricketgame.cricketgameapi.api;

import com.cricketgame.cricketgameapi.match.Match;
import com.cricketgame.cricketgameapi.match.MatchService;
import com.cricketgame.cricketgameapi.match.UniqueMatchId;
import com.cricketgame.cricketgameapi.series.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("Series/Match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @RequestMapping("/All")
    public List<Match> getAllMatchRecord()
    {
        return matchService.getAllMatchRecord();
    }

    @RequestMapping("/Winner/{}/{}")
    public String getWinnerOfMatch(@PathVariable int seriesId,@PathVariable int matchId)
    {
        return matchService.getWinnerOfMatch(seriesId,matchId);
    }

    @RequestMapping("/Insert")
    public String insertMatchRecord(@RequestParam int seriesId,@RequestParam int matchId, @RequestParam int battingTeamId,@RequestParam int battingTeamScore,@RequestParam int battingTeamWickets,@RequestParam int bowlingTeamScore,@RequestParam int bowlingTeamWickets, @RequestParam int winningTeam)
    {
        UniqueMatchId uniqueMatchId=new UniqueMatchId(seriesId,matchId);
        matchService.insertMatchRecord(new Match(uniqueMatchId,battingTeamId,battingTeamScore,battingTeamWickets,bowlingTeamScore,bowlingTeamWickets,winningTeam));
        return "Saved";
    }

}
