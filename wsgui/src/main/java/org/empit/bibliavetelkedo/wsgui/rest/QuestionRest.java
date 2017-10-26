package org.empit.bibliavetelkedo.wsgui.rest;

import org.empit.bibliavetelkedo.dal.entity.QuestionBE;
import org.empit.bibliavetelkedo.dal.entity.UserBE;
import org.empit.bibliavetelkedo.dal.repo.jpa.QuestionRepo;
import org.empit.bibliavetelkedo.dal.repo.jpa.UserRepo;
import org.empit.bibliavetelkedo.servicelayer.dto.QuestionDTO;
import org.empit.bibliavetelkedo.servicelayer.services.QuestionService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

    @GET
    @Path("/getnext")
    @Produces("application/json")
    public QuestionDTO getNext(@QueryParam("username") String username) {

        QuestionService instance = QuestionService.getInstance();
        return instance.getNext(username);
    }

    @GET
    @Path("/answer")
    @Produces("application/json")
    public boolean answer(@QueryParam("username") String username,
                          @QueryParam("qid") Long qid,
                          @QueryParam("answer") String answer) {

        QuestionService instance = QuestionService.getInstance();
        instance.answer(username, qid, answer);
        return true;
    }
}
