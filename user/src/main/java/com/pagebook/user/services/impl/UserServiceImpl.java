package com.pagebook.user.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pagebook.user.entity.User;
import com.pagebook.user.repository.IUserRepository;
import com.pagebook.user.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    KafkaTemplate kafkaTemplate;
    @Override
    public User save(User user)  {
        ObjectMapper objectMapper = new ObjectMapper();

        String userString="";
        try {
            userString = objectMapper.writeValueAsString(user);
        }catch (Exception e)
        {
            System.out.println(e.toString());
        }

        kafkaTemplate.send("newuser",userString);
        return iUserRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        Iterable<User> userIterable = iUserRepository.findAll();
        List<User> userList = new ArrayList<>();
        userIterable.forEach(userList::add);
        return userList;
    }

    @Override
    public User getUserInfo(String userId) {
        return iUserRepository.findById(userId).get();
    }

    @Override
    public List<User> userDetailsList(List<String> userIdList) {

        Iterable<User> userIterable = iUserRepository.findByUserIdIn(userIdList);
        List<User> userDetailsList = new ArrayList<>();
        userIterable.forEach(userDetailsList::add);
        return userDetailsList;
    }
}
