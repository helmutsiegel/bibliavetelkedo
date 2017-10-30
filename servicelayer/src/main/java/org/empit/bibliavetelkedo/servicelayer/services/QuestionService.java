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
        final int[] incorrects = {0};
        gameBE.getAnswers().forEach(a -> {
            if (!a.getAnswer().equals(a.getQuestion().getCorrectAnswer())
                    && !a.getAnswer().equals("NEXT_QUESTION") && !a.getAnswer().equals("NO_ANSWER")) {
                incorrects[0]++;
            }
        });

        AnswerBE whereNotAnswer = AnswerRepo.getInstance().getWhereNotAnswer(gameBE.getId());

        QuestionBE next = null;

        if (null != whereNotAnswer) {
            next = whereNotAnswer.getQuestion();
        } else {
            next = questionRepo.getNext(gameBE.getQuestions());
            AnswerBE answerBE = new AnswerBE();
            answerBE.setAnswer("NO_ANSWER");
            answerBE.setQuestion(next);
            answerBE.setGame(gameBE);
            AnswerRepo.getInstance().insert(answerBE);
            whereNotAnswer = answerBE;
        }

        QuestionDTO resp = QuestionConverter.fromBEToDTO(next);
        resp.setAnsId(whereNotAnswer.getId());
        resp.setLevel((long) gameBE.getCorrectAnswers().size() + 1);

        if (incorrects[0] > 1 || gameBE.getCorrectAnswers().size() == 45) {
            resp.setCanContinue(false);
            resp.setLevel((long) gameBE.getCorrectAnswers().size());
        }


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

    public boolean answer(String username, Long ansid, String answer) {
        boolean resp = true;
        GameRepo gameRepo = GameRepo.getInstance();
        GameBE gameBE = gameRepo.getByUsername(username);
        AnswerBE answerBE = AnswerRepo.getInstance().getById(ansid);
        answerBE.setAnswer(answer);
        QuestionBE questionBE = answerBE.getQuestion();

        if (!answer.equals(questionBE.getCorrectAnswer())) {
            if (gameBE.getUsedHelps().parallelStream().anyMatch(e -> e.getHelp() == HelpEnum.INCORRECT)) {
                resp = false;
            } else {
                gameBE.getUsedHelps().add(HelpRepo.getInstance().getByEnum(HelpEnum.INCORRECT));
                gameRepo.save(gameBE);
            }
        }
        AnswerRepo.getInstance().save(answerBE);
        return resp;
    }

    public List<String> halfing(String username, Long ansId) {
        GameRepo gameRepo = GameRepo.getInstance();
        AnswerBE answerBE = AnswerRepo.getInstance().getById(ansId);
        QuestionBE questionBE = answerBE.getQuestion();
        GameBE gameBE = answerBE.getGame();
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

    public boolean nexting(String username, Long ansId) {
        GameRepo gameRepo = GameRepo.getInstance();
        GameBE gameBE = gameRepo.getByUsername(username);
        AnswerRepo answerRepo = AnswerRepo.getInstance();
        AnswerBE answerBE = answerRepo.getById(ansId);

        if (gameBE.getUsedHelps().parallelStream().anyMatch(e -> e.getHelp() == HelpEnum.NEXT_QUESTION)) {
            return false;
        }
        gameBE.getUsedHelps().add(HelpRepo.getInstance().getByEnum(HelpEnum.NEXT_QUESTION));
        answerBE.setAnswer("NEXT_QUESTION");
        answerRepo.save(answerBE);
        gameRepo.save(gameBE);
        return true;
    }

}
