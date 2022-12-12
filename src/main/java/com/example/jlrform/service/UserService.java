package com.example.jlrform.service;

import com.example.jlrform.dao.UserDao;
import com.example.jlrform.dto.UserDto;
import com.example.jlrform.entity.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author tshi1
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Value("${price.all}")
    private int allPriceNum;
    @Value("${price.first}")
    private int firstPriceNum;

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        userDao.findAll().forEach(u -> users.add(u));
        return users;
    }

    public User getUserByCDSId(String cdsid){
        return userDao.findByCdsid(cdsid);
    }

    public User saveUser(String cdsid,String eName,String cName,Boolean involved,int score){
        User user = new User();
        user.setCdsid(cdsid);
        user.setEName(eName);
        user.setCName(cName);
        user.setSubmitTime(new Date());
        user.setScore(score);
        return saveUser(user);
    }

    public User saveUser(User user){
        return userDao.save(user);
    }

    public List<UserDto> getAllRank(){
        List<User> users = getAllUsers();
        List<User> submiteduser = users.stream().filter(u ->u.getSubmitTime() != null).collect(Collectors.toList());

        List<UserDto> userDtos = new ArrayList<>(submiteduser.size());
        for(int rank = 0 ; rank < submiteduser.size(); rank++){
            UserDto userDto = new UserDto();
            User user = submiteduser.get(rank);
            userDto.setRank(rank+1);
            userDto.setCdsid(user.getCdsid());
            userDto.setEName(user.getEName());
            userDto.setCName(user.getCName());
            userDto.setScore(user.getScore());
            userDto.setSubmitTime(user.getSubmitTime());
            userDtos.add(userDto);
        }

        Collections.sort(userDtos, new UserDto.UserComparator());
        return userDtos;
    }

    public List<UserDto> getPriceRank(){
        List<UserDto> userDtos = this.getAllRank();

        Map<Integer,UserDto> rankUserMap = userDtos.stream().collect(Collectors.toMap(UserDto ::getRank,user ->user));

        List<UserDto> results = new ArrayList<>();

        if(firstPriceNum > allPriceNum){
            throw new RuntimeException("Wrong properties: first price num is larger than all price num");
        }

        for(int i = 0;i<allPriceNum; i++){
            UserDto user = rankUserMap.get(i+1);
            if(user == null){
                break;
            }
            if(i < firstPriceNum){
                user.setPriceLevel(UserDto.PriceLevel.FIRST);
            }else{
                user.setPriceLevel(UserDto.PriceLevel.SECOND);
            }

            results.add(user);
        }

        return results;
    }
}
