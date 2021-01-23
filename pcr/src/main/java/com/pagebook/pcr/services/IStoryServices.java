package com.pagebook.pcr.services;

import com.pagebook.pcr.dto.StoryDTO;
import com.pagebook.pcr.entity.Story;

import java.util.List;

public interface IStoryServices {
    Story save(Story story);
    void deleteById(int id);
    Story findById(int id);
    List<StoryDTO> findFriendStories(String id);
}
