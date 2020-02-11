package com.example.whisky_base.service;

import com.example.whisky_base.controller.UserApi.UserCommand;
import com.example.whisky_base.model.entity.User;
import com.example.whisky_base.model.entity.UserRole;
import com.example.whisky_base.repo.UserRepo;
import com.example.whisky_base.repo.UserRoleRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAndRoleService {
    UserRepo userRepo;
    UserRoleRepo userRoleRepo;
    PasswordEncoder passwordEncoder;

    public UserAndRoleService(UserRepo userRepo, UserRoleRepo userRoleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(UserCommand userCommand) {
        User user = new User();
        user.setEnable(true);
        user.setUser_mail(userCommand.getUser_email());
        user.setUser_pass(passwordEncoder.encode(userCommand.getUser_password()));

        userRepo.save(user);


        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setUser_role("ROLE_USER");

        userRoleRepo.save(userRole);


    }

    public String whoIsLogged(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
            }
}
