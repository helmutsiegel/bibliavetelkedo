package org.empit.bibliavetelkedo.wsgui.rest;


import org.empit.bibliavetelkedo.dal.entity.UserBE;
import org.empit.bibliavetelkedo.dal.repo.jpa.UserRepo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.util.List;

/**
 * Created by helmut on 09.05.2017.
 */
@Path("/users")
public class UserRest {

    @GET
    @Produces("application/json")
    public List<UserBE> getData() throws IOException {

        UserRepo userRepo = UserRepo.getInstance();

        List<UserBE> all = userRepo.getAll();
        return all;
    }
}
