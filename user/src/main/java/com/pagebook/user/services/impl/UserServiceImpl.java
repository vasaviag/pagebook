package com.pagebook.user.services.impl;

import com.pagebook.user.entity.User;
import com.pagebook.user.repository.IUserRepository;
import com.pagebook.user.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository iUserRepository;
    @Override
    public User save(User user) {
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