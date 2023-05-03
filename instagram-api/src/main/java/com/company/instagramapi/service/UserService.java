package com.company.instagramapi.service;

import com.company.instagramapi.dto.UserDto;
import com.company.instagramapi.exception.UserException;
import com.company.instagramapi.modal.User;
import com.company.instagramapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public User registerUser(User user){
        if(isUserEmailExists(user.getEmail()))
            throw new UserException("Email already exists");
        if(isUsernameExists(user.getUsername()))
            throw new UserException("Username already exists");
        return userRepository.save(user);
    }
    public User findUserById(long id){
        return userRepository.findById(id).orElseThrow(()->new UserException("User not found"));
    }
    public User findUserProfile(String token){
        return null;
    }
    public User findUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(()->new UserException("User not found"));
    }
    public String followUser(long reqUserId,long followerUserId){
        User reqUser=findUserById(reqUserId);
        User followerUser=findUserById(followerUserId);
        UserDto follower=new UserDto();
        follower.setEmail(reqUser.getEmail());
        follower.setId(reqUser.getId());
        follower.setImage(reqUser.getImage());
        follower.setName(reqUser.getName());
        follower.setUsername(reqUser.getUsername());
        UserDto following=new UserDto();
        following.setEmail(followerUser.getEmail());
        following.setId(followerUser.getId());
        following.setImage(followerUser.getImage());
        following.setName(followerUser.getName());
        following.setUsername(followerUser.getUsername());
        reqUser.getFollowing().add(follower);
        followerUser.getFollower().add(following);
        userRepository.save(reqUser);
        userRepository.save(followerUser);

        return "You are following "+followerUser.getUsername()+" now";
    }
    public String unFollowUser(long reqUserId,long followerUserId){
        User reqUser=findUserById(reqUserId);
        User followerUser=findUserById(followerUserId);
        UserDto follower=new UserDto();
        follower.setEmail(reqUser.getEmail());
        follower.setId(reqUser.getId());
        follower.setImage(reqUser.getImage());
        follower.setName(reqUser.getName());
        follower.setUsername(reqUser.getUsername());
        UserDto following=new UserDto();
        following.setEmail(followerUser.getEmail());
        following.setId(followerUser.getId());
        following.setImage(followerUser.getImage());
        following.setName(followerUser.getName());
        following.setUsername(followerUser.getUsername());
        reqUser.getFollowing().remove(follower);
        followerUser.getFollower().remove(following);
        userRepository.save(reqUser);
        userRepository.save(followerUser);
        return "You are not following "+followerUser.getUsername()+" now";
    }
    public List<User> findUserByIds(List<Long> userIds){
        List<User> users=userRepository.findAllUsersByIds(userIds);
        if(users.isEmpty())
            throw new UserException("Users not found");
        return users;
    }
   public List<User> searchUser(String query){
       List<User> users=userRepository.findByQuery(query);
         if(users.isEmpty())
              throw new UserException("User not found");
        return users;
   }
   public User updateUserDetails(User updatedUser,User existingUser){
        if(updatedUser.getName()!=null)
            existingUser.setName(updatedUser.getName());
        if(updatedUser.getEmail()!=null)
            existingUser.setEmail(updatedUser.getEmail());
        if(updatedUser.getUsername()!=null)
            existingUser.setUsername(updatedUser.getUsername());
        if(updatedUser.getImage()!=null)
            existingUser.setImage(updatedUser.getImage());
        if (updatedUser.getBio()!=null)
            existingUser.setBio(updatedUser.getBio());
        if(updatedUser.getWebsite()!=null)
            existingUser.setWebsite(updatedUser.getWebsite());
        if (updatedUser.getGender()!=null)
            existingUser.setGender(updatedUser.getGender());
        if(updatedUser.getMobile()!=null)
            existingUser.setMobile(updatedUser.getMobile());
        if(updatedUser.getId() == existingUser.getId())
            return userRepository.save(existingUser);

        throw new UserException("You are not authorized to update this user");

   }
   private boolean isUserEmailExists(String email){

        return userRepository.existsByEmail(email);
   }
   private boolean isUsernameExists(String username){
        return userRepository.existsByUsername(username);
   }
}
