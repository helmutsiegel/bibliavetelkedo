package org.empit.bibliavetelkedo.servicelayer.services;

import org.empit.bibliavetelkedo.dal.entity.GameBE;
import org.empit.bibliavetelkedo.dal.repo.jpa.GameRepo;
import org.empit.bibliavetelkedo.dal.repo.jpa.UserRepo;

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
        if(!gameRepo.checkHasGame(username)){
            GameBE game = new GameBE();
            game.setUserBE(UserRepo.getInstance().getByUsername(username));
            game.setName("Vetelkedő");
            gameRepo.insert(game);
        }
        GameBE byUsername = gameRepo.getByUsername(username);
        return byUsername;
    }
}
