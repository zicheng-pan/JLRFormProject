package com.example.jlrform.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Comparator;
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

    private PriceLevel priceLevel;

    public static class UserComparator implements Comparator<UserDto> {

        @Override
        public int compare(UserDto o1, UserDto o2) {
            if(o1.getScore().equals(o2.score)){
                return o1.getSubmitTime().compareTo(o2.getSubmitTime());
            }
            return o2.getScore() - o1.getScore();
        }
    }

    public enum PriceLevel {
        FIRST(1),
        SECOND(2);

        private Integer level;

        public Integer getLevel() {
            return level;
        }

        PriceLevel(Integer level) {
            this.level = level;
        }
    }
}
