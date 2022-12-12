package com.example.jlrform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author tshi1
 */
@Data
@Entity
@Table(name = "quiz_option")
public class QuizOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="question_index")
    private Integer questionIndex;
    @Column(name="index")
    private Integer index;
    @Column(name="context")
    private String context;
    @Column(name="is_answer")
    private Boolean isAnswer;
}
