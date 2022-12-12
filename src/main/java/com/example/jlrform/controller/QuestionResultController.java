package com.example.jlrform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionResultController {

    @GetMapping("/firstResult")
    public String getFirstResult() {
        return "[{\"name\":\"test\",\"department\":\"tttttttt\"},{\"name\":\"test\",\"department\":\"11111\"},{\"name\":\"test\",\"department\":\"222222\"}]";
    }

    @GetMapping("/secondResult")
    public String getSecondResult() {
        return "[{\"name\":\"test\",\"department\":\"tttttttt\"},{\"name\":\"test\",\"department\":\"11111\"},{\"name\":\"test\",\"department\":\"222222\"}]";
    }

    @GetMapping("/allCDSID")
    public String getAllCDSID() {
        return "[{\"name\":\"test\",\"department\":\"tttttttt\"},{\"name\":\"test\",\"department\":\"11111\"},{\"name\":\"test\",\"department\":\"222222\"}]";
    }

}
