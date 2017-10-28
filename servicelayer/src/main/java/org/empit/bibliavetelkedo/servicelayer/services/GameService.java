package org.empit.bibliavetelkedo.servicelayer.services;

import org.empit.bibliavetelkedo.dal.entity.GameBE;
import org.empit.bibliavetelkedo.dal.entity.UserBE;
import org.empit.bibliavetelkedo.dal.repo.jpa.GameRepo;
import org.empit.bibliavetelkedo.dal.repo.jpa.UserRepo;
import org.empit.bibliavetelkedo.servicelayer.dto.ResultDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GameService {
    private static GameService gameService;

    public static GameService getInstance() {
        if (gameService == null) {
            gameService = new GameService();
        }
        return gameService;
    }

    private GameService() {
        //singleton
    }

    public boolean checkHasGame(String username) {
        GameRepo gameRepo = GameRepo.getInstance();
        return gameRepo.checkHasGame(username);
    }

    public GameBE getGame(String username) {
        GameRepo gameRepo = GameRepo.getInstance();
        if (!gameRepo.checkHasGame(username)) {
            GameBE game = new GameBE();
            game.setUserBE(UserRepo.getInstance().getByUsername(username));
            game.setName("Vetelkedő");
            gameRepo.insert(game);
        }
        GameBE byUsername = gameRepo.getByUsername(username);
        return byUsername;
    }

    public List<ResultDTO> getAllResult() {
        List<GameBE> all = GameRepo.getInstance().getAll();
        List<ResultDTO> result = all.stream().map(g -> {
            ResultDTO resultDTO = new ResultDTO();
            UserBE userBE = g.getUserBE();
            resultDTO.setFirstName(userBE.getFirstName());
            resultDTO.setLastName(userBE.getLastName());
            resultDTO.setUsername(userBE.getUsername());
            resultDTO.setLevel((long) g.getCorrectAnswers().size());
            resultDTO.setUsedHelps(g.getUsedHelps().stream()
                    .map(e -> e.getHelp()).collect(Collectors.toList()));
            return resultDTO;
        }).collect(Collectors.toList());
        Collections.sort(result, Comparator.comparing(ResultDTO::getLevel).reversed());
        return result;
    }
}
