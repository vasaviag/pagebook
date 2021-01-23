package com.pagebook.user.services;

import com.pagebook.user.entity.User;

import java.util.List;

public interface IUserService {
    User save(User user);

    List<User> findAll();
}
