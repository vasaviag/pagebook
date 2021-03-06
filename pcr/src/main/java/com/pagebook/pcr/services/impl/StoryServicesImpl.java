package com.pagebook.pcr.services.impl;

import com.pagebook.pcr.dto.StoryDTO;
import com.pagebook.pcr.dto.StoryRequestDTO;
import com.pagebook.pcr.dto.UserDTO;
import com.pagebook.pcr.entity.Story;
import com.pagebook.pcr.repository.IStoryRepository;
import com.pagebook.pcr.services.IStoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class StoryServicesImpl implements IStoryServices {
    @Autowired
    IStoryRepository iStoryRepository;

    @Autowired
    RestTemplateImpl restTemplateImpl;

    public List<Story> save(StoryRequestDTO storyRequestDTO)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 10);
        List<Story> storyList = new ArrayList<>();
        for (String storyUrl : storyRequestDTO.getStoryUrl()) {
            Story story = new Story();
            story.setStoryUrl(storyUrl);
            story.setUserId(storyRequestDTO.getUserId());
            story.setTimestamp(calendar.getTime());
            storyList.add(iStoryRepository.save(story));
        }
        return storyList;
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
        //todo : remove the array list and use iterable
        List<Story> stories = new ArrayList<>();
        storyIterable.forEach(stories::add);
        for (Story story : stories)
        {
            Date currDate = new Date();
            if(currDate.compareTo(story.getTimestamp()) > 0) {
                deleteById(story.getStoryId());
                stories.remove(story);

            }
        }
        return stories;
    }

    public Story findByStoryId(int id)
    {
        Story story = iStoryRepository.findByStoryId(id);
        Date timestamp = story.getTimestamp();
        story.setTimestamp(timestamp);
        return story;
    }

    public List<StoryDTO> findFriendStories(String id)
    {

        List<UserDTO> userDTOS = new ArrayList<>();
        userDTOS.add(restTemplateImpl.getUserDetails(id));
        userDTOS.addAll(restTemplateImpl.getFriendsDetails(id));

        List<StoryDTO> storyDTOS = new ArrayList<>();


        for (UserDTO userDTO : userDTOS) {
            List<Story> stories = findStoriesByUserId(userDTO.getUserId());


            for (Story story : stories) {
                StoryDTO storyDTO = new StoryDTO();
                storyDTO.setStoryId(story.getStoryId());
                storyDTO.setUserDTO(userDTO);
                storyDTO.setStoryUrl(story.getStoryUrl());
                storyDTO.setTimestamp(story.getTimestamp());
                storyDTOS.add(storyDTO);
            }
        }
        //todo : use sort methods on list or colletion framework to sort based on the time stamps after collecting all stories of friends
        System.out.println(storyDTOS.size());

        return storyDTOS;
    }
}
