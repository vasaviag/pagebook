package com.pagebook.pcr.services.impl;

import com.pagebook.pcr.dto.StoryDTO;
import com.pagebook.pcr.dto.User;
import com.pagebook.pcr.entity.Story;
import com.pagebook.pcr.entity.Story;
import com.pagebook.pcr.repository.IStoryRepository;
import com.pagebook.pcr.services.IStoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class StoryServicesImpl implements IStoryServices {
    @Autowired
    IStoryRepository iStoryRepository;

    @Autowired
    RestTemplate restTemplate;

    public Story save(Story story)
    {
        story.setTimestamp(new Date());
        return iStoryRepository.save(story);
    }

    public void deleteById(int id)
    {
        iStoryRepository.deleteById(id);
    }

    public Story findById(int id)
    {
        return iStoryRepository.findById(id).get();
    }

    public List<Story> findStoriesByUserId(String id)
    {
        Iterable<Story> storyIterable = iStoryRepository.findByUserId(id);
        List<Story> stories = new ArrayList<>();
        storyIterable.forEach(stories::add);
        for (Story story : stories)
        {
            Date currDate = new Date();
            long diff = story.getTimestamp().getTime() - currDate.getTime();
            long diffInDays = TimeUnit.MILLISECONDS.toMinutes(diff);
            if(diffInDays > 10) {
                stories.remove(story);
                deleteById(story.getStoryId());
            }
        }
        return stories;
    }

    public Story findByStoryId(int id)
    {
        Story story = iStoryRepository.findByStoryId(id);
        Date timestamp = story.getTimestamp();
        return story;
    }

    public List<StoryDTO> findFriendStories(String id)
    {
        final String uri = "http://10.177.1.179:7081/pb/user/getFriends/" + id;
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(uri, User[].class);
        User users1 [] = responseEntity.getBody();
        System.out.println(users1);

        List<User> users = Arrays.asList(users1);

        List<StoryDTO> storyDTOS = new ArrayList<>();
        for (User user : users) {
            List<Story> stories = findStoriesByUserId(user.getUserId());

            for (Story story : stories) {
                storyDTOS.add(new StoryDTO(story.getStoryId(), user, story.getStoryUrl(), story.getTimestamp()));
            }
        }

        return storyDTOS;
    }
}
