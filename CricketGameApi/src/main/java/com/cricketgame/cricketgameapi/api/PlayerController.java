package com.cricketgame.cricketgameapi.api;

import com.cricketgame.cricketgameapi.player.Player;
import com.cricketgame.cricketgameapi.player.PlayerService;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/player")
public class PlayerController {

    static Logger logger= LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerService playerService;

    @GetMapping(path="/all")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNewPlayer(@RequestBody Player player) {
        String response=null;
        try {
            playerService.addPlayer(player);
            response = "New Player Added Successfully";
        }
        catch (Exception e)
        {
            logger.error("Exception while adding the entry from database.");
            response="Failure : "+e.getMessage();
        }
        return response;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deletePlayer(@RequestParam int id) {
        String response=null;
        try {
            playerService.deletePlayer(id);
            response="Deleted Successfully";
        }
        catch(Exception e)
        {
            logger.error("Exception while deleting the entry from database.");
            response="Failure : "+e.getMessage();
        }
        return response;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public String upatePlayer(@PathVariable int id, @RequestBody Player player) {
        String response=null;
        try {
            player.setPlayerId(id);
            playerService.updatePlayer(player);
            response="Player Deatils updated successfully.";
        }
        catch(Exception e)
        {
            logger.error("Exception while updating Team: "+e.getMessage());
            response="Failure"+e.getMessage();
        }
        return response;
    }

    @RequestMapping(value="/tradePlayer/{playerId}/{teamId}",method = RequestMethod.POST)
    public String tradePlayer(@PathVariable int playerId,@PathVariable int teamId)
    {
        String response=null;
            if(playerService.getPlayerByTeamId(teamId).size()==15)
            {
                response="Maximum limit exceeded we can't add this player in "+teamId+"team";
            }
            else
            {
                playerService.updateTeamId(playerId,teamId);
                response="Player has been traded successfully !!";
            }
        return response;
    }
}
