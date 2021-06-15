package com.haiya.controller;

import com.haiya.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/info")
    public User info(@RequestParam("id") String id) {
        User user = new User();
        user.setId("123123123213");
        user.setUsername("qiao");
        user.setAge(29);
        return user;
    }
}
