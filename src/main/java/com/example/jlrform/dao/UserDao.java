package com.example.jlrform.dao;

import com.example.jlrform.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author tshi1
 */
public interface UserDao extends CrudRepository<User,Long> {

}
