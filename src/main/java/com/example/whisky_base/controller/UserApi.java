package com.example.whisky_base.controller;

import com.example.whisky_base.service.UserAndRoleService;
import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserApi {

    UserAndRoleService userAndRoleService;

    public UserApi(UserAndRoleService userAndRoleService) {
        this.userAndRoleService = userAndRoleService;
    }

    @PostMapping
    public void addUser(@RequestBody UserCommand userCommand) {
        userAndRoleService.addUser(userCommand);
    }

    @GetMapping
    public String whoIsLgged() {
        return userAndRoleService.whoIsLogged();
    }

    @Value
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class UserCommand {
        String user_email;
        String user_password;
    }
}
