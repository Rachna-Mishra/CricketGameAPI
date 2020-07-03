package com.cricketgame.cricketgameapi.api;

import com.cricketgame.cricketgameapi.match.MatchService;
import com.cricketgame.cricketgameapi.player.PlayerRepository;
import com.cricketgame.cricketgameapi.player.PlayerService;
import com.cricketgame.cricketgameapi.scoreboard.ScoreBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ScoreBoardService scoreBoardService;

    @RequestMapping("/startMatch/{}/{}")
    public void startMatch(@PathVariable int numberOfMatches, @PathVariable int numberOfOvers)
    {
       // matchService.getStartedMatch(numberOfMatches,numberOfOvers);
    }
}
