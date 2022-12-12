package com.example.jlrform.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tshi1
 */
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="cdsid")
    private String cdsid;
    @Column(name="e_name")
    private String EName;
    @Column(name="c_name")
    private String CName;
    @Column(name="submit_time")
    private Date submitTime;
    @Column(name="score")
    private Integer score;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", cdsid='" + cdsid + '\'' +
                ", EName='" + EName + '\'' +
                ", CName='" + CName + '\'' +
                ", submitTime=" + submitTime.getTime() +
                ", score=" + score +
                '}';
    }
}
