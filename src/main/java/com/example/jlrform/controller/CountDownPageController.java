package com.example.jlrform.controller;

import com.example.jlrform.sender.WebSocketSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountDownPageController {

    @Autowired
    WebSocketSender webSocketSender;

    @GetMapping("/countdownnumber")
    public void setCountDownPageNumber(@RequestParam int number) {
//        System.out.println(number);
        webSocketSender.sendCountDownNumber(number);
    }

}
