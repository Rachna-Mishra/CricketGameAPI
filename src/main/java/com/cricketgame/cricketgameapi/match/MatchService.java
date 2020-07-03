package com.cricketgame.cricketgameapi.match;

import com.cricketgame.cricketgameapi.series.SeriesRepository;
import com.cricketgame.cricketgameapi.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private TeamService teamService;

    public List<Match> getAllMatchRecord() {
        List<Match> matchList=new ArrayList<>();
        for(Match match:matchRepository.findAll())
            matchList.add(match);
        return matchList;
    }

    public String getWinnerOfMatch(int seriesId,int matchId) {
        String winnerOfMatch=null;
        try
        {
            Optional<Match> winner=matchRepository.findById(new UniqueMatchId(seriesId,matchId));
            if(winner.isPresent())
            {
                int winnerOfMatchTeamId=winner.get().getWinningTeam();
                winnerOfMatch=teamService.getTeamName(winnerOfMatchTeamId);
            }
            else
                throw new Exception("This requested data does not exist in database");
        }
        catch (Exception e) {
            return "Failure : "+e;
        }
        return winnerOfMatch;
    }

    public void insertMatchRecord(Match match){
        matchRepository.save(match);
    }

}
