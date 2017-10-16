package org.empit.bibiliavetelkedo.dal;

import org.empit.bibliavetelkedo.dal.entity.UserBE;
import org.empit.bibliavetelkedo.dal.repo.jpa.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserRepoIT {

    @Test
    public void testGetAllUser(){
        UserRepo instance = UserRepo.getInstance();
        List<UserBE> all = instance.getAll();
        Assertions.assertNotEquals(all.size(),0);
    }
}
