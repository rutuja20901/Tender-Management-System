package com.jwtauth.tendersystem.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jwtauth.tendersystem.model.RoleModel;
import com.jwtauth.tendersystem.model.UserModel;
import com.jwtauth.tendersystem.repository.RoleRepo;
import com.jwtauth.tendersystem.repository.UserRepo;

@Component
public class Dataloader implements ApplicationRunner {

        @Autowired
        private RoleRepo roleRepo;

        @Autowired
        private UserRepo userRepo;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        public void run(ApplicationArguments args) throws Exception {
                RoleModel bidderRole = roleRepo.save(new RoleModel("BIDDER"));
                RoleModel approverRole = roleRepo.save(new RoleModel("APPROVER"));

                userRepo.save(
                                new UserModel(1, "bidder1", "companyOne", "bidderemail@gmail.com",
                                                passwordEncoder.encode("bidder123$"),
                                                bidderRole));
                userRepo.save(
                                new UserModel(2, "bidder2", "companyTwo", "bidderemail2@gmail.com",
                                                passwordEncoder.encode("bidder789$"),
                                                bidderRole));
                userRepo.save(new UserModel(3, "approver", "defaultCompany", "approveremail@gmail.com",
                                passwordEncoder.encode("approver123$"),
                                approverRole));
        }

}
