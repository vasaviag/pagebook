package com.pagebook.pcr.services.impl;

import com.pagebook.pcr.entity.Story;
import com.pagebook.pcr.repository.IStoryRepository;
import com.pagebook.pcr.services.IStoryServices;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class StoryServicesImpl implements IStoryServices {
    @Autowired
    IStoryRepository iStoryRepository;

    public Story save(Story story)
    {
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

    public List<Story> findStorysByUserId(String id)
    {
        Iterable<Story> postsIterable = iStoryRepository.findByUserId(id);
        List<Story> posts = new ArrayList<>();
        postsIterable.forEach(posts::add);
        return posts;
    }

    public Story findByStoryId(int id)
    {
        return iStoryRepository.findByStoryId(id);
    }
}
