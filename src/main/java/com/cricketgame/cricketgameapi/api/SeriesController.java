package com.cricketgame.cricketgameapi.api;

import com.cricketgame.cricketgameapi.series.Series;
import com.cricketgame.cricketgameapi.series.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/Series")
public class SeriesController {

    @Autowired
    private SeriesService seriesService;

    @RequestMapping(value = "/All")
    public List<Series> getAllSeriesRecord()
    {
        return seriesService.getAllSeriesRecord();
    }

    @RequestMapping(value = "/Winner/{seriesId}",method = RequestMethod.GET)
    public String getWinnerOfSeries(@PathVariable int seriesId)
    {
        String winnerOfSeries=seriesService.getWinnerOfSeries(seriesId);
        return winnerOfSeries;
    }

}
