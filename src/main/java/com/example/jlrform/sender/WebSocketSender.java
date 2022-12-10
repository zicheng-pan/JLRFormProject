package com.example.jlrform.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketSender {
    @Autowired
    private SimpMessagingTemplate template;

    public void sendCountDownNumber(int number) {
        template.convertAndSend("/countdownnumber", number);
    }

    public void addpeopleinrunning(String cdsid) {
        template.convertAndSend("/peoplesrunning", cdsid);
    }

}
