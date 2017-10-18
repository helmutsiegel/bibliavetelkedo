package org.empit.bibliavetelkedo.wsgui.rest;

import org.empit.bibliavetelkedo.dal.entity.GameBE;
import org.empit.bibliavetelkedo.servicelayer.services.GameService;
import org.empit.bibliavetelkedo.servicelayer.services.UserService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/game")
public class GameRest {

    @GET
    @Path("/hasgame")
    @Produces("application/json")
    public String hasGame(@QueryParam("username") String username) {

        GameService instance = GameService.getInstance();
        boolean exists = instance.checkHasGame(username);
        if (exists) {
            return "{\"answer\": \"true\"}";
        }
        return "{\"answer\": \"false\"}";
    }

    @GET
    @Path("/getgame")
    @Produces("application/json")
    public GameBE getGame(@QueryParam("username") String username) {

        GameService instance = GameService.getInstance();
        return instance.getGame(username);
    }
}
