package com.pagebook.user.repository;

import com.pagebook.user.entity.ModeratorMapper;
import org.springframework.data.repository.CrudRepository;

public interface IModeratorMapperRepository extends CrudRepository<ModeratorMapper,Integer> {
    Iterable<ModeratorMapper> findByModeratorId(String userId);

    Iterable<ModeratorMapper> findByBusinessId(String userId);
}
