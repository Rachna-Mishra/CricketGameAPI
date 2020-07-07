package com.cricketgame.cricketgameapi.api;

import com.cricketgame.cricketgameapi.series.Series;
import com.cricketgame.cricketgameapi.series.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/series")
public class SeriesController {

    @Autowired
    private SeriesService seriesService;

    @RequestMapping(value = "/all")
    public List<Series> getAllSeriesRecord()
    {
        return seriesService.getAllSeriesRecord();
    }

    @RequestMapping(value = "/winner/{date}/{id}",method = RequestMethod.GET)
    public String getWinnerOfSeries(@PathVariable String date, @PathVariable Integer id)
    {
        String seriesId=date+"-"+id.toString();
        String winnerOfSeries=seriesService.getWinnerOfSeries(seriesId);
        return winnerOfSeries;
    }

    @RequestMapping(value ="/insert",method = RequestMethod.POST)
    public void insertSeriesRecord(@RequestBody Series series)
    {
        seriesService.insertSeriesRecord(series);
    }

}
