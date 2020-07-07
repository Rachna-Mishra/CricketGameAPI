package com.cricketgame.cricketgameapi.team;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService
{
    static Logger logger= LoggerFactory.getLogger(TeamService.class);
    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getAllTeams()
    {
        List<Team> teamList=new ArrayList<>();
        for(Team team:teamRepository.findAll())
            teamList.add(team);
        return teamList;
    }

    public void addTeam( int id, String teamName)
    {
        Team team = new Team();
        team.setTeamId(id);
        team.setTeamName(teamName);
        teamRepository.save(team);
    }

    public String deleteTeam(int id) {
        String response=null;
        try {
            Optional<Team> team=teamRepository.findById(id);
            if (team.isPresent()) {
                teamRepository.delete(team.get());
            }
            else
                throw new Exception("Id does not exist");
        }
        catch(Exception e)
        {
            logger.error("Exception while deleting the entry from database.");
            response="Failure : "+e.getMessage();
        }
        return response;

    }

    public void updateTeam(Team team)
    {
        teamRepository.save(team);
    }

    public String getTeamName(int winnerOfSeriesTeamId) {
        return teamRepository.getOne(winnerOfSeriesTeamId).getTeamName();
    }
}

