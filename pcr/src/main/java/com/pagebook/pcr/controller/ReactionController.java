package com.pagebook.pcr.controller;

import com.pagebook.pcr.dto.ReactionDetailsDTO;
import com.pagebook.pcr.dto.ReactionRequestDTO;
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
    List<ReactionsDTO> addReaction(@RequestBody ReactionRequestDTO reactionRequestDTO)
    {
        return iReactionServices.save(reactionRequestDTO);
    }

    @PostMapping(value = "/updateReaction")
    List<ReactionsDTO> updateReaction(@RequestBody ReactionRequestDTO reactionRequestDTO)
    {
        return iReactionServices.save(reactionRequestDTO);
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
    List<ReactionDetailsDTO> getPostReactionDetails(@PathVariable("postId") int postId, @PathVariable("reactionType") int reactionType)
    {
        return iReactionServices.findByPostIdAndReactionType(postId, reactionType);
    }
}
