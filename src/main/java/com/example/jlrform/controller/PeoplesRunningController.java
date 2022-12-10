package com.example.jlrform.controller;

import com.example.jlrform.sender.WebSocketSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeoplesRunningController {

    @Autowired
    WebSocketSender webSocketSender;

    @GetMapping("/peoplesrunning")
    public String setCountDownPageNumber(@RequestParam String cdsid, @RequestParam String answer) {
        System.out.println(cdsid);
        System.out.println(answer);
        webSocketSender.addpeopleinrunning(cdsid);
        return "finished";
    }

}
