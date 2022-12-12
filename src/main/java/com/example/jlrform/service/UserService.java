package com.example.jlrform.service;

import com.example.jlrform.dao.UserDao;
import com.example.jlrform.entity.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tshi1
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        userDao.findAll().forEach(u -> users.add(u));
        return users;
    }
}
