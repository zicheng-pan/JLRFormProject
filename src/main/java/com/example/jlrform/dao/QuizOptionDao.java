package com.example.jlrform.dao;

import com.example.jlrform.entity.Quiz;
import com.example.jlrform.entity.QuizOption;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tshi1
 */
public interface QuizOptionDao extends JpaRepository<QuizOption,Long> {

}
