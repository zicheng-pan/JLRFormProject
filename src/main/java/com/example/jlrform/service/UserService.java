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
        return userDao.findByCdsid(cdsid.toLowerCase());
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
        List<User> submitedusers = users.stream().filter(u ->u.getSubmitTime() != null).collect(Collectors.toList());

        List<UserDto> submitedUserDtos = new ArrayList<>(submitedusers.size());
        for(int i = 0 ; i < submitedusers.size(); i++){
            UserDto userDto = new UserDto();
            User submiteduser = submitedusers.get(i);
            userDto.setCdsid(submiteduser.getCdsid());
            userDto.setEName(submiteduser.getEName());
            userDto.setCName(submiteduser.getCName());
            userDto.setScore(submiteduser.getScore());
            userDto.setSubmitTime(submiteduser.getSubmitTime());
            submitedUserDtos.add(userDto);
        }

        Collections.sort(submitedUserDtos, new UserDto.UserComparator());

        List<UserDto> userRankList = new ArrayList<>();
        for(UserDto userDto: submitedUserDtos){
            Integer score = userDto.getScore();
            Integer rankLowerThanCurrentUser = submitedUserDtos.stream().filter(su -> su.getScore() > score || (su.getScore().equals(score) && su.getSubmitTime().getTime() < userDto.getSubmitTime().getTime())).collect(Collectors.toList()).size();
            userDto.setRank(rankLowerThanCurrentUser + 1);
            userRankList.add(userDto);
        }
        return userRankList;
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
