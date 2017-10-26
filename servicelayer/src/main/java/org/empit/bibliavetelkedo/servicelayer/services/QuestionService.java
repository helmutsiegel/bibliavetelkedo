package org.empit.bibliavetelkedo.servicelayer.services;

import org.empit.bibliavetelkedo.dal.entity.AnswerBE;
import org.empit.bibliavetelkedo.dal.entity.GameBE;
import org.empit.bibliavetelkedo.dal.entity.QuestionBE;
import org.empit.bibliavetelkedo.dal.entity.UserBE;
import org.empit.bibliavetelkedo.dal.repo.jpa.AnswerRepo;
import org.empit.bibliavetelkedo.dal.repo.jpa.GameRepo;
import org.empit.bibliavetelkedo.dal.repo.jpa.QuestionRepo;
import org.empit.bibliavetelkedo.dal.repo.jpa.UserRepo;
import org.empit.bibliavetelkedo.servicelayer.convert.QuestionConverter;
import org.empit.bibliavetelkedo.servicelayer.dto.QuestionDTO;
import org.empit.bibliavetelkedo.servicelayer.dto.UserDTO;

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

    public QuestionDTO getNext(String username) {
        QuestionRepo questionRepo = QuestionRepo.getInstance();
        UserRepo instance = UserRepo.getInstance();
        UserBE byUsername = instance.getByUsername(username);
        List<GameBE> games = byUsername.getGames();
        GameBE gameBE = games.get(0);
        return QuestionConverter.fromBEToDTO(questionRepo.getNext(gameBE.getQuestions()));
    }

    public boolean answer(String username, Long qid, String answer){
        GameBE gameBE = GameRepo.getInstance().getByUsername(username);
        QuestionBE questionBE = QuestionRepo.getInstance().getById(qid);
        AnswerBE answerBE = new AnswerBE();
        answerBE.setAnswer(answer);
        answerBE.setQuestion(questionBE);
        answerBE.setGame(gameBE);
        AnswerRepo.getInstance().insert(answerBE);
        return true;
    }

}
