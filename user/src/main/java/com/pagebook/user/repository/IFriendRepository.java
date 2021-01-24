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
            " GROUP BY may_know_friend_id ORDER BY COUNT(*) DESC limit 10 ", nativeQuery = true)

    //todo : check phani's query!
    //select friend_user_id
        //(select friend_user_id, count(1) cnt from friends (select friend_user_id from friends where user_id in (SELECT friend_user_id FROM friends WHERE user_id=:userId)) order by cnt desc) where friend_user_id <> :userId limit 10

    List<String> youMayKnow(@Param("userId")String userId);


    @Query(value = "select true from friends where user_id=:userId AND friend_user_Id=:friendUserId", nativeQuery = true)
    boolean isFriend(@Param("userId")String userId, @Param("friendUserId")String friendUserId);
}
