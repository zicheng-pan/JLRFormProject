package com.example.jlrform.entity;

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

    @Column(name="csid")
    private String csid;
    @Column(name="e_name")
    private String EName;
    @Column(name="c_name")
    private String CName;
    @Column(name="involved")
    private Boolean involved;
    @Column(name="score")
    private Integer score;

}
