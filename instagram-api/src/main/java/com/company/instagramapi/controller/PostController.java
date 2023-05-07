package com.company.instagramapi.controller;

import com.company.instagramapi.modal.Post;
import com.company.instagramapi.modal.User;
import com.company.instagramapi.response.MessageResponse;
import com.company.instagramapi.service.PostService;
import com.company.instagramapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post/")
public class PostController {
    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }
    @PostMapping("create")
    public ResponseEntity<Post> createPost(@RequestBody Post post,@RequestHeader("Authorization") String  token){
        User user=userService.findUserProfile(token);
        Post createdPost=postService.createPost(post,user.getId());
        return ResponseEntity.ok(createdPost);
    }
    @GetMapping("all/{id}")
    public ResponseEntity<List<Post>> findPostsByUserId(@PathVariable("id") long userId){
        List<Post> posts=postService.findPostsByUserId(userId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("following/{ids}")
    public ResponseEntity<List<Post>> findAllPostByUserIds(@PathVariable("ids") List<Long> userId){
        List<Post> posts=postService.findAllPostByUserIds(userId);
        return ResponseEntity.ok(posts);
    }
    @GetMapping("{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable("postId") long postId){
        Post post=postService.findPostById(postId);
        return ResponseEntity.ok(post);
    }
    @PutMapping("like/{postId}")
    public ResponseEntity<Post> likePost(@PathVariable("postId") long postId,@RequestHeader("Authorization") String  token){
        User user=userService.findUserProfile(token);
        Post post=postService.likePost(postId,user.getId());
        return ResponseEntity.ok(post);
    }
    @PutMapping("unlike/{postId}")
    public ResponseEntity<Post> unLikePost(@PathVariable("postId") long postId,@RequestHeader("Authorization") String  token){
        User user=userService.findUserProfile(token);
        Post post=postService.unLikePost(postId,user.getId());
        return ResponseEntity.ok(post);
    }
    @DeleteMapping("delete/{postId}")
    public ResponseEntity<MessageResponse> deletePost(@PathVariable("postId") long postId,@RequestHeader("Authorization") String  token){
        User user=userService.findUserProfile(token);
       String message= postService.deletePost(postId,user.getId());
        return ResponseEntity.ok(new MessageResponse(message));
    }
    @PutMapping("saved/{postId}")
    public ResponseEntity<MessageResponse> savedPost(@PathVariable("postId") long postId,@RequestHeader("Authorization") String  token){
        User user=userService.findUserProfile(token);
        String message=postService.savedPost(postId,user.getId());
        return ResponseEntity.ok(new MessageResponse(message));
    }

    @PutMapping("unsaved/{postId}")
    public ResponseEntity<MessageResponse> unSavedPost(@PathVariable("postId") long postId,@RequestHeader("Authorization") String  token){
        User user=userService.findUserProfile(token);
        String message=postService.unSavedPost(postId,user.getId());
        return ResponseEntity.ok(new MessageResponse(message));
    }

}
