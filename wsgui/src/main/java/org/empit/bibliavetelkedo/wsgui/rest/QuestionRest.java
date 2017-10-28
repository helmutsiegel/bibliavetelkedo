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
                          @QueryParam("ansId") Long ansid,
                          @QueryParam("answer") String answer) {

        QuestionService instance = QuestionService.getInstance();
        return instance.answer(username, ansid, answer);

    }

    @GET
    @Path("/halfing")
    @Produces("application/json")
    public List<String> halfing(@QueryParam("username") String username,
                                @QueryParam("ansId") Long ansId) {
        QuestionService instance = QuestionService.getInstance();
        return instance.halfing(username, ansId);
    }

    @GET
    @Path("/nexting")
    @Produces("application/json")
    public boolean nexting(@QueryParam("username") String username,
                           @QueryParam("ansId") Long ansId) {
        QuestionService instance = QuestionService.getInstance();
        return instance.nexting(username, ansId);
    }

}
