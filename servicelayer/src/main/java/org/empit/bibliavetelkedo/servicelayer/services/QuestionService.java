package org.empit.bibliavetelkedo.servicelayer.services;

import org.empit.bibliavetelkedo.dal.entity.*;
import org.empit.bibliavetelkedo.dal.repo.jpa.*;
import org.empit.bibliavetelkedo.servicelayer.convert.QuestionConverter;
import org.empit.bibliavetelkedo.servicelayer.dto.QuestionDTO;
import org.empit.bibliavetelkedo.servicelayer.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionService {

    private static QuestionService instance;
    private Random randomGenerator;

    public static QuestionService getInstance() {
        if (instance == null) {
            instance = new QuestionService();
        }
        return instance;
    }

    private QuestionService() {
        //singleton
        randomGenerator = new Random();
    }

    public QuestionDTO getNext(String username) {
        QuestionRepo questionRepo = QuestionRepo.getInstance();
        UserRepo instance = UserRepo.getInstance();
        UserBE byUsername = instance.getByUsername(username);
        List<GameBE> games = byUsername.getGames();
        GameBE gameBE = games.get(0);
        QuestionDTO resp = QuestionConverter.fromBEToDTO(questionRepo.getNext(gameBE.getQuestions()));
        if (gameBE.getUsedHelps().parallelStream().anyMatch(e -> e.getHelp() == HelpEnum.HALVING)) {
            resp.setCanHalf(false);
        }
        if (gameBE.getUsedHelps().parallelStream().anyMatch(e -> e.getHelp() == HelpEnum.NEXT_QUESTION)) {
            resp.setCanNext(false);
        }
        if (gameBE.getUsedHelps().parallelStream().anyMatch(e -> e.getHelp() == HelpEnum.INCORRECT)) {
            resp.setCanFault(false);
        }
        return resp;
    }

    public boolean answer(String username, Long qid, String answer) {
        GameBE gameBE = GameRepo.getInstance().getByUsername(username);
        QuestionBE questionBE = QuestionRepo.getInstance().getById(qid);
        AnswerBE answerBE = new AnswerBE();
        answerBE.setAnswer(answer);
        answerBE.setQuestion(questionBE);
        answerBE.setGame(gameBE);
        AnswerRepo.getInstance().insert(answerBE);
        return true;
    }

    public List<String> halfing(String username, Long qid) {
        GameRepo gameRepo = GameRepo.getInstance();
        GameBE gameBE = gameRepo.getByUsername(username);
        QuestionBE questionBE = QuestionRepo.getInstance().getById(qid);
        if (!gameBE.getUsedHelps().parallelStream().anyMatch(e -> e.getHelp() == HelpEnum.HALVING)) {
            int index = randomGenerator.nextInt(3);
            List<String> result = new ArrayList<>();
            if (!questionBE.getAnswerA().equals(questionBE.getCorrectAnswer())) {
                result.add(questionBE.getAnswerA());
            }
            if (!questionBE.getAnswerB().equals(questionBE.getCorrectAnswer())) {
                result.add(questionBE.getAnswerB());
            }
            if (!questionBE.getAnswerC().equals(questionBE.getCorrectAnswer())) {
                result.add(questionBE.getAnswerC());
            }
            if (!questionBE.getAnswerD().equals(questionBE.getCorrectAnswer())) {
                result.add(questionBE.getAnswerD());
            }
            result.remove(index);
            gameBE.getUsedHelps().add(HelpRepo.getInstance().getByEnum(HelpEnum.HALVING));
            gameRepo.save(gameBE);
            return result;
        }
        ArrayList<String> res = new ArrayList<>();
        res.add("false");
        return res;
    }

    public boolean nexting(String username, Long qid) {
        GameRepo gameRepo = GameRepo.getInstance();
        GameBE gameBE = gameRepo.getByUsername(username);
        QuestionBE questionBE = QuestionRepo.getInstance().getById(qid);
        if (gameBE.getUsedHelps().parallelStream().anyMatch(e -> e.getHelp() == HelpEnum.NEXT_QUESTION)) {
            return false;
        }
        gameBE.getUsedHelps().add(HelpRepo.getInstance().getByEnum(HelpEnum.NEXT_QUESTION));
        gameRepo.save(gameBE);
        return true;
    }

}
