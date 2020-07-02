package com.cricketgame.cricketgameapi.api;

import com.cricketgame.cricketgameapi.player.Player;
import com.cricketgame.cricketgameapi.player.PlayerService;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/Player")
public class PlayerController {

    static Logger logger= LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerService playerService;

    @GetMapping(path="/All")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();

    }

    @RequestMapping(value = "/Add", method = RequestMethod.POST)
    public String addNewTeam(@RequestBody Player player) {
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

    @RequestMapping(value = "/Delete/{id}", method = RequestMethod.DELETE)
    public String deleteTeam(@PathVariable String id) {
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

    @RequestMapping(value = "/Update/{id}", method = RequestMethod.PUT)
    public String upateTeam(@PathVariable String id,@RequestBody Player player) {
        String response=null;
        try {
            player.setPlayer_id(id);
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
}
