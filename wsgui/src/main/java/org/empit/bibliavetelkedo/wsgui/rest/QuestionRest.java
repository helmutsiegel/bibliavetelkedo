package org.empit.bibliavetelkedo.wsgui.rest;

import org.empit.bibliavetelkedo.dal.entity.QuestionBE;
import org.empit.bibliavetelkedo.dal.entity.UserBE;
import org.empit.bibliavetelkedo.dal.repo.jpa.QuestionRepo;
import org.empit.bibliavetelkedo.dal.repo.jpa.UserRepo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.util.List;

@Path("/questions")
public class QuestionRest {


    @GET
    @Produces("application/json")
    public List<QuestionBE> getData() throws IOException {

        QuestionRepo questionRepo = QuestionRepo.getInstance();

        List<QuestionBE> all = questionRepo.getAll();

        return all;
    }
}
