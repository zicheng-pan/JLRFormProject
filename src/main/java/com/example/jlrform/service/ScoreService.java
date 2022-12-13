package com.example.jlrform.service;

import com.example.jlrform.entity.QuizOption;
import com.example.jlrform.entity.User;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tshi1
 */
@Service
public class ScoreService {

    @Autowired
    private UserService userService;
    @Autowired
    private QuizService quizService;

    public String calculateScore(String cdsid, String answer) {
        User user = userService.getUserByCDSId(cdsid);
        if(user == null){
            throw new RuntimeException("There is no user named "+ cdsid);
        }
        List<QuizOption> options = quizService.getRightOptions();
        Map<Integer, QuizOption> quizAnswer = options.stream().collect(Collectors.toMap(QuizOption::getQuestionIndex, option -> option));
        String[] answers = answer.split(",");
        Integer score = 0;
        for (int i = 0; i < answers.length; i++) {
            Integer ansIndex = Integer.parseInt(answers[i]);
            QuizOption rightOption = quizAnswer.get(i+1);
            if(rightOption.getIndex().equals(ansIndex)){
                score ++ ;
            }
        }

        user.setScore(score);
        user.setSubmitTime(new Date());

        userService.saveUser(user);
        return user.getEName();
    }

}
