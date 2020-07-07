package com.cricketgame.cricketgameapi.generalutil;

import com.cricketgame.cricketgameapi.team.Team;
import com.cricketgame.cricketgameapi.team.TeamService;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GeneralUtil {

    public static  int seriesCount=1;
    private Random random = new Random();

    public Random getRandomFunction() {
        return random;
    }

    //=================After Tossing fetching the Winner=============
    public int getWinner() {
        Random r = getRandomFunction();
        if (r.nextInt(2) == 0)
            return 0;
        else
            return 1;
    }

    //===============selecting 2 random Teams from number of Teams=============
    public  List<Team> getSelectedTeams(TeamService teamService)
    {
        List<Team> selectedTeams=new ArrayList<>();
        List<Team> listOfTeams=teamService.getAllTeams();
        Random r = getRandomFunction();
        int index1 = r.nextInt(listOfTeams.size());
        int index2;
        do {
            index2 = r.nextInt(listOfTeams.size());
        } while (index1 == index2);
        selectedTeams.add(listOfTeams.get(index1));
        selectedTeams.add(listOfTeams.get(index2));
        return selectedTeams;
    }

}
