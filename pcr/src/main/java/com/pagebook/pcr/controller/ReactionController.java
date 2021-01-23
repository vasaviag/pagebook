package com.pagebook.pcr.controller;

import com.pagebook.pcr.dto.ReactionDetails;
import com.pagebook.pcr.dto.ReactionsDTO;
import com.pagebook.pcr.entity.ReactionOnPosts;
import com.pagebook.pcr.services.IReactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "pb/reaction")
public class ReactionController {
    @Autowired
    IReactionServices iReactionServices;

    @PostMapping(value = "/addReaction")
    ReactionOnPosts addReaction(@RequestBody ReactionOnPosts reactionOnPosts)
    {
        return iReactionServices.save(reactionOnPosts);
    }

    @PostMapping(value = "/updateReaction")
    ReactionOnPosts updateReaction(@RequestBody ReactionOnPosts reactionOnPosts)
    {
        return iReactionServices.save(reactionOnPosts);
    }

    @DeleteMapping(value = "/deleteReaction/{reactionId}")
    void deleteReaction(@PathVariable("reactionId") int reactionId)
    {
        iReactionServices.deleteById(reactionId);
    }

    @GetMapping(value = "/getReaction/{reactionId}")
    ReactionOnPosts getReaction(@PathVariable("reactionId") int reactionId)
    {
        return iReactionServices.findById(reactionId);
    }

    @GetMapping(value = "/getReactions/{postId}")
    List<ReactionsDTO> getPostReactions(@PathVariable("postId") int postId)
    {
        return iReactionServices.findByPostId(postId);
    }

    @GetMapping(value = "/getReactionDetails/{postId}/{reactionType}")
    List<ReactionDetails> getPostReactionDetails(@PathVariable("postId") int postId, @PathVariable("reactionType") int reactionType)
    {
        return iReactionServices.findByPostIdAndReactionType(postId, reactionType);
    }
}
