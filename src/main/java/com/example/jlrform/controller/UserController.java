package com.example.jlrform.controller;

import com.example.jlrform.dto.UserDto;
import com.example.jlrform.entity.User;
import com.example.jlrform.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tshi1
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/all", produces = "application/json; charset=utf-8")
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping(value = "/user/cdsid", produces = "application/json; charset=utf-8")
    public User getUserByCdsid(@RequestParam String cdsid) {
        return userService.getUserByCDSId(cdsid);
    }

    @PostMapping(value = "/user", produces = "application/json; charset=utf-8")
    public User saveUser(@RequestParam String eName
            , @RequestParam String cName
            , @RequestParam String cdsid
            , @RequestParam Boolean involved
            , @RequestParam int score) {

        return userService.saveUser(cdsid, eName, cName, involved, score);
    }

    @GetMapping(value = "/user/rank", produces = "application/json; charset=utf-8")
    public List<UserDto> getRank() {
        return userService.getAllRank();
    }

    @GetMapping(value = "/user/priceRank", produces = "application/json; charset=utf-8")
    public List<UserDto> getPriceRank() {
        return userService.getPriceRank();
    }

    @GetMapping(value = "/user/refresh", produces = "application/json; charset=utf-8")
    public boolean refresh() {
        return userService.refreshDataBase();
    }
}
