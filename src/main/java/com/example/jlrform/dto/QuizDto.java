package com.example.jlrform.dto;

import java.util.List;
import lombok.Data;

/**
 * @author tshi1
 */
@Data
public class QuizDto {
    private Integer index;
    private String context;

    private List<QuizOptionDto> options;
}
