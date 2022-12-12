package com.example.jlrform.controller;

import com.example.jlrform.sender.WebSocketSender;
import com.example.jlrform.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeoplesRunningController {

    @Autowired
    WebSocketSender webSocketSender;

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/peoplesrunning")
    public String setCountDownPageNumber(@RequestParam String cdsid, @RequestParam String answer) {
        System.out.println(cdsid);
        System.out.println(answer);
        scoreService.calculateScore(cdsid,answer);
        webSocketSender.addpeopleinrunning(cdsid);
        return "finished";
    }

}
