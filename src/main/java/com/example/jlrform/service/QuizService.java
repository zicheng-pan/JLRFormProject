package com.example.jlrform.service;

import com.example.jlrform.dao.QuizDao;
import com.example.jlrform.dao.QuizOptionDao;
import com.example.jlrform.dao.UserDao;
import com.example.jlrform.dto.QuizDto;
import com.example.jlrform.dto.QuizOptionDto;
import com.example.jlrform.entity.Quiz;
import com.example.jlrform.entity.QuizOption;
import com.example.jlrform.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tshi1
 */
@Service
public class QuizService {
    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuizOptionDao quizOptionDao;

    public List<QuizDto> getAllQuestions() {
        List<Quiz> quizs = getAllQuiz();
        List<QuizOption> quizOptions = getAllQuizOptions();

        List<QuizDto> quizResults = new ArrayList<>();
        quizs.forEach(q -> {
            QuizDto dto = new QuizDto();
            dto.setIndex(q.getIndex());
            dto.setContext(q.getContext());
            List<QuizOptionDto> options = new ArrayList<>();
            quizOptions.forEach(o -> {
                if (o.getQuestionIndex().equals(q.getIndex())) {
                    QuizOptionDto optionDto = new QuizOptionDto();
                    optionDto.setIndex(o.getIndex());
                    optionDto.setContext(o.getContext());
                    options.add(optionDto);
                }
            });
            dto.setOptions(options);
            quizResults.add(dto);
        });

        return quizResults;
    }

    private List<Quiz> getAllQuiz() {
        List<Quiz> quizs = new ArrayList<>();
        quizDao.findAll().forEach(q -> quizs.add(q));
        return quizs;
    }

    public List<QuizOption> getRightOptions() {
        List<QuizOption> quizOptions = getAllQuizOptions();
        return quizOptions.stream().filter(o -> o.getIsAnswer()).collect(Collectors.toList());
    }

    private List<QuizOption> getAllQuizOptions() {
        List<QuizOption> quizOptions = new ArrayList<>();
        quizOptionDao.findAll().forEach(q -> quizOptions.add(q));
        return quizOptions;
    }
}
