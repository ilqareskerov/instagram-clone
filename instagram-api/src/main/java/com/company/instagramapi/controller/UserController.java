package com.company.instagramapi.controller;

import com.company.instagramapi.modal.User;
import com.company.instagramapi.response.MessageResponse;
import com.company.instagramapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("id/{id}")
    public ResponseEntity<User> findUserById(@PathVariable long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }
    @GetMapping("username/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.findUserByUsername(username));
    }
    @PutMapping("follow/{followerUserId}")
    public ResponseEntity<MessageResponse> followUser(@PathVariable long followerUserId){
      //  MessageResponse response=userService.followUser(followerUserId,followerUserId);
        return null;
    }
    @PutMapping("unfollow/{unFollowerUserId}")
    public ResponseEntity<MessageResponse> unFollowUser(@PathVariable long unFollowerUserId){
        //  MessageResponse response=userService.unFollowUser(followerUserId,followerUserId);
        return null;
    }
    @PutMapping("req")
    public ResponseEntity<MessageResponse> findUserProfile(@RequestHeader("Authorization") String token){
        //  MessageResponse response=userService.unFollowUser(followerUserId,followerUserId);
        return null;
    }
    @GetMapping("m/{userIds}")
    public ResponseEntity<List<User>> findUserByUserIds(@PathVariable List<Long> userIds){
        List<User> users=userService.findUserByIds(userIds);
        return ResponseEntity.ok(users);
    }
    @GetMapping("search")
    public ResponseEntity<List<User>> searchUser(@RequestParam String query){
        List<User> users=userService.searchUser(query);
        return ResponseEntity.ok(users);
    }
    public ResponseEntity<User> updateUserProfile(@RequestHeader("Authorization") String token,@RequestBody User user){
        User updatedUser=userService.updateUserDetails(user,user);
        return ResponseEntity.ok(updatedUser);
    }
}
