package com.example.jlrform.controller;

import com.example.jlrform.dto.QuizDto;
import com.example.jlrform.entity.User;
import com.example.jlrform.service.QuizService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tshi1
 */
@RestController
public class AnswerSheetController {
    @Autowired
    private QuizService quizService;

    @GetMapping("/quiz/all")
    public List<QuizDto> getAllUsers() {

        return quizService.getAllQuestions();
    }
}
