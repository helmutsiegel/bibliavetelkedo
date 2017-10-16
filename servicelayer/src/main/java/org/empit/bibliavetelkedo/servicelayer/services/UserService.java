package org.empit.bibliavetelkedo.servicelayer.services;

import org.empit.bibliavetelkedo.dal.entity.UserBE;
import org.empit.bibliavetelkedo.dal.repo.jpa.UserRepo;
import org.empit.bibliavetelkedo.servicelayer.convert.UserConverter;
import org.empit.bibliavetelkedo.servicelayer.dto.UserDTO;

/**
 * Created by helmut on 20.04.2017.
 */
public class UserService {
    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    private UserService() {
        //singleton
    }

    public boolean checkUsernameAndPassword(String username, String password) {
        UserRepo userRepo = UserRepo.getInstance();
        return userRepo.checkUsernameAndPassword(username, password);
    }

    public UserDTO getUserByUsername(String username) {
        UserRepo userRepo = UserRepo.getInstance();
        return UserConverter.fromBEToDTO(userRepo.getByUsername(username));
    }

    public boolean checkUsername(String username) {
        UserRepo userRepo = UserRepo.getInstance();
        return userRepo.checkUsername(username);
    }

    public boolean insert(UserDTO userDTO) {
        UserRepo userRepo = UserRepo.getInstance();
        UserBE userBE = UserConverter.fromDTOToBE(userDTO);
        userRepo.insert(userBE);
        return true;
    }

    public boolean changePassword(String username, String password) {
        UserRepo userRepo = UserRepo.getInstance();
        UserBE be = userRepo.getByUsername(username);
        if (be == null) {
            return false;
        }
        be.setPassword(password);
        userRepo.save(be);
        return true;
    }
}
