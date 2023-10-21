package com.pagebook.user.repository;

import com.pagebook.user.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserRepository extends CrudRepository<User, String> {

    Iterable<User> findByUserIdIn(List<String> userIdList);
}
