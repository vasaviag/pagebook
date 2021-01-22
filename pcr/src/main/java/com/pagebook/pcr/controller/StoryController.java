package com.pagebook.pcr.controller;

import com.pagebook.pcr.entity.Story;
import com.pagebook.pcr.services.IStoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/story")
public class StoryController {
    @Autowired
    IStoryServices iStoryServices;
    
    @PostMapping(value = "/addStory")
    Story addStory(@RequestBody Story story)
    {
        return iStoryServices.save(story);
    }

    @PostMapping(value = "/updateStory")
    Story updateStory(@RequestBody Story story)
    {
        return iStoryServices.save(story);
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
}
