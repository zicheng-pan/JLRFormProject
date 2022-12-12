package com.example.jlrform.dto;

import java.util.Date;
import lombok.Data;

/**
 * @author tshi1
 */
@Data
public class UserDto {

    private Integer rank;
    private String cdsid;
    private String EName;
    private String CName;
    private Integer score;
    private Date submitTime;
}
