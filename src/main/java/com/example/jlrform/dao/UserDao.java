package com.example.jlrform.dao;

import com.example.jlrform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tshi1
 */
public interface UserDao extends JpaRepository<User,Long> {

}
