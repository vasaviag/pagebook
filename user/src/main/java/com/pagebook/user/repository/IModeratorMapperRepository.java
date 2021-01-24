package com.pagebook.user.repository;

import com.pagebook.user.entity.ModeratorMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IModeratorMapperRepository extends CrudRepository<ModeratorMapper,Integer> {
    Iterable<ModeratorMapper> findByModeratorId(String userId);

    Iterable<ModeratorMapper> findByBusinessId(String userId);

    @Query(value = "select true from moderatormapper where moderator_id=:moderatorId AND business_id=:businessId", nativeQuery = true)
    boolean isModerator(@Param("moderatorId") String moderatorId,@Param("businessId") String businessId);
}
