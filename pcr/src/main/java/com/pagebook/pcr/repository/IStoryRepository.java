package com.pagebook.pcr.repository;

import com.pagebook.pcr.entity.Story;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IStoryRepository extends CrudRepository<Story, Integer> {
    List<Story> findByUserId(String storyId);
    Story findByStoryId(int storyId);
}
