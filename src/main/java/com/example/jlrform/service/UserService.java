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

    public User saveUser(String csid,String eName,String cName,Boolean involved,int score){
        User user = new User();
        user.setCsid(csid);
        user.setEName(eName);
        user.setCName(cName);
        user.setInvolved(involved);
        user.setScore(score);
        return userDao.save(user);
    }
}
