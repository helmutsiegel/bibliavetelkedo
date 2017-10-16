package org.empit.bibliavetelkedo.servicelayer.convert;

import org.empit.bibliavetelkedo.dal.entity.UserBE;
import org.empit.bibliavetelkedo.dal.repo.jpa.UserRepo;
import org.empit.bibliavetelkedo.servicelayer.dto.UserDTO;

/**
 * Created by helmut on 18.04.2017.
 */
public class UserConverter {
    public static UserDTO fromBEToDTO(UserBE userBE) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userBE.getUsername());
        userDTO.setFirstName(userBE.getFirstName());
        userDTO.setLastName(userBE.getLastName());

        return userDTO;
    }

    public static UserBE fromDTOToBE(UserDTO userDTO) {
        UserRepo instance = (UserRepo) UserRepo.getInstance();
        UserBE userBE = instance.getByUsername(userDTO.getUsername());
        if (userBE == null) {
            userBE = new UserBE();
        }
        userBE.setUsername(userDTO.getUsername());

        userBE.setFirstName(userDTO.getFirstName());
        userBE.setLastName(userDTO.getLastName());
        userBE.setPassword(userDTO.getPassword());

        return userBE;
    }
}
