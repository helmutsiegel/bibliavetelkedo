package org.empit.bibliavetelkedo.servicelayer.services;

import org.empit.bibliavetelkedo.dal.entity.GameBE;
import org.empit.bibliavetelkedo.dal.entity.QuestionBE;
import org.empit.bibliavetelkedo.dal.entity.UserBE;
import org.empit.bibliavetelkedo.dal.repo.jpa.QuestionRepo;
import org.empit.bibliavetelkedo.dal.repo.jpa.UserRepo;

import java.util.List;

public class QuestionService {

    private static QuestionService instance;

    public static QuestionService getInstance() {
        if (instance == null) {
            instance = new QuestionService();
        }
        return instance;
    }

    private QuestionService() {
        //singleton
    }

    public QuestionBE getNext(String username) {
        QuestionRepo questionRepo = QuestionRepo.getInstance();
        UserRepo instance = UserRepo.getInstance();
        UserBE byUsername = instance.getByUsername(username);
        List<GameBE> games = byUsername.getGames();
        GameBE gameBE = games.get(0);
        return questionRepo.getNext(gameBE.getQuestions());
    }

}
