package com.pagebook.user.repository;

import com.pagebook.user.entity.Friend;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFriendRepository extends CrudRepository<Friend,Integer> {

    Iterable<Friend> findByUserId(String userId);


    @Query(value = "SELECT friend_user_id AS may_know_friend_id FROM friends WHERE" +
            " user_id IN (SELECT friend_user_id FROM friends WHERE user_id=:userId) AND" +
            " friend_user_id NOT IN  (SELECT friend_user_id FROM friends WHERE user_id=:userId) AND NOT friend_user_id=:userId" +
            " GROUP BY may_know_friend_id ORDER BY COUNT(*) DESC  ", nativeQuery = true)
    List<String> youMayKnow(@Param("userId")String userId);
}
