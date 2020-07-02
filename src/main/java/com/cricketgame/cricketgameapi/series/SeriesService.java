package com.cricketgame.cricketgameapi.series;

import com.cricketgame.cricketgameapi.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeriesService {

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private TeamService teamService;

    public String getWinnerOfSeries(int seriesId) {
        String winnerOfSeries=null;
        try
        {
            Optional<Series> winner=seriesRepository.findById(seriesId);
            if(winner.isPresent())
            {
                System.out.println( winner.get().getWinnerSeriesTeamId());
                int winnerOfSeriesTeamId=winner.get().getWinnerSeriesTeamId();
                winnerOfSeries=teamService.getTeamName(winnerOfSeriesTeamId);
            }
            else
                throw new Exception("This Series does not exist in database");
        }
        catch (Exception e) {
            return "Failure : "+e;
        }
        return winnerOfSeries;
    }

    public List<Series> getAllSeriesRecord() {
        List<Series> seriesList=new ArrayList<>();
        for(Series series:seriesRepository.findAll())
            seriesList.add(series);
        return seriesList;
    }
}
