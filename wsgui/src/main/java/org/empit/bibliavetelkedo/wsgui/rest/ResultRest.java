package org.empit.bibliavetelkedo.wsgui.rest;

import org.empit.bibliavetelkedo.dal.entity.UserBE;
import org.empit.bibliavetelkedo.dal.repo.jpa.UserRepo;
import org.empit.bibliavetelkedo.servicelayer.dto.ResultDTO;
import org.empit.bibliavetelkedo.servicelayer.services.GameService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.util.List;

@Path("/result")
public class ResultRest {

    @GET
    @Produces("application/json")
    public List<ResultDTO> getData() throws IOException {
        return GameService.getInstance().getAllResult();
    }

}
