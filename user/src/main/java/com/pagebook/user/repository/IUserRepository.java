package com.pagebook.user.repository;

import com.pagebook.user.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, String> {
    User findByUserId(String userId);
}
