package com.pagebook.pcr.controller;

import com.pagebook.pcr.dto.StoryDTO;
import com.pagebook.pcr.dto.StoryRequestDTO;
import com.pagebook.pcr.entity.Story;
import com.pagebook.pcr.services.IStoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "pb/story")
public class StoryController {
    @Autowired
    IStoryServices iStoryServices;
    
    @PostMapping(value = "/addStory")
    List<Story> addStory(@RequestBody StoryRequestDTO storyRequestDTO)
    {
        return iStoryServices.save(storyRequestDTO);
    }

    @PostMapping(value = "/updateStory")
    List<Story> updateStory(@RequestBody StoryRequestDTO storyRequestDTO)
    {
        return iStoryServices.save(storyRequestDTO);
    }

    @DeleteMapping(value = "/deleteStory/{storyId}")
    void deleteStory(@PathVariable("storyId") int storyId)
    {
        iStoryServices.deleteById(storyId);
    }

    @GetMapping(value = "/getStory/{storyId}")
    Story getStory(@PathVariable("storyId") int storyId)
    {
        return iStoryServices.findById(storyId);
    }

    @GetMapping(value = "/getFriendsStories/{userId}")
    List<StoryDTO> getFriendsStories(@PathVariable("userId") String userId)
    {
        return iStoryServices.findFriendStories(userId);
    }
}
