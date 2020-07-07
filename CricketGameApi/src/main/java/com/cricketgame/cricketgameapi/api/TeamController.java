package com.cricketgame.cricketgameapi.api;

import com.cricketgame.cricketgameapi.team.Team;
import com.cricketgame.cricketgameapi.team.TeamService;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/teams")
public class TeamController
{
   static Logger logger= LoggerFactory.getLogger(TeamController.class);
    @Autowired
    private TeamService teamService;

    @GetMapping(path="/all")
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNewTeam(@RequestBody Team team) {
        String response=null;
        try {
            teamService.addTeam(team.getTeamId(), team.getTeamName());
            response = "New Team Added Successfully";
        }
        catch (Exception e)
        {
           logger.error("Exception while deleting the entry from database.");
           response="Failure : "+e.getMessage();
        }
        return response;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteTeam(@PathVariable int id) {
        return teamService.deleteTeam(id);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public String upateTeam(@PathVariable int id,@RequestBody Team team) {
        String response=null;
        try {
            team.setTeamId(id);
            teamService.updateTeam(team);
            response="Team updated successfully.";
        }
        catch(Exception e)
        {
            logger.error("Exception while updating Team: "+e.getMessage());
            response="Failure"+e.getMessage();
        }
        return response;
    }

}
