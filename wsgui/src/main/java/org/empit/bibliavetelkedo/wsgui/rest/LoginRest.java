package org.empit.bibliavetelkedo.wsgui.rest;



import org.empit.bibliavetelkedo.servicelayer.services.UserService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Created by helmut on 26.04.2017.
 */
@Path("/login")
public class LoginRest {

    @GET
    @Produces("application/json")
    public String login(@QueryParam("username") String username,
                        @QueryParam("password") String password) {

        UserService instance = UserService.getInstance();
        boolean exists = instance.checkUsernameAndPassword(username, password);
        if (exists) {
            return "{\"login\": \"true\", \"username\":\"" + username + "\"}";
        }
        return "{\"login\": \"false\"}";
    }
}
