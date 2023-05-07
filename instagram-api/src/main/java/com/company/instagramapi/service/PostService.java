package com.company.instagramapi.service;

import com.company.instagramapi.dto.UserDto;
import com.company.instagramapi.exception.GenericException;
import com.company.instagramapi.modal.Post;
import com.company.instagramapi.modal.User;
import com.company.instagramapi.repository.PostRepository;
import com.company.instagramapi.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
   private final UserRepository userRepository;
    public PostService(PostRepository postRepository, UserService userService, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }
    public Post createPost(Post post,Long userId){
        User user=userService.findUserById(userId);
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setImage(user.getImage());
        userDto.setName(user.getName());
        post.setUser(userDto);
        Post createdPost =postRepository.save(post);
        return createdPost;
    }
    public String deletePost(long postId, long userId){
        Post post=findPostById(postId);
        User user=userService.findUserById(userId);
      if(post.getUser().getId()==user.getId()){
          postRepository.delete(post);
          return "Post deleted successfully";
      }
         throw new GenericException(HttpStatus.BAD_REQUEST,"You can't delete this post");
    }
    public List<Post> findPostsByUserId(long userId){
        List<Post> posts=postRepository.findByUserId(userId);
        if(posts.size()==0){
            throw new GenericException(HttpStatus.NOT_FOUND,"No posts found");
        }
        return posts;
    }
    public Post findPostById(long postId){
        Optional<Post> opt=postRepository.findById(postId);
        if(opt.isPresent())
            return opt.get();
        else
            throw new GenericException(HttpStatus.NOT_FOUND,"Post not found");
    }

    public List<Post> findAllPostByUserIds(List<Long> userIds){
        List<Post> posts=postRepository.findAllPostByUserIds(userIds);
        if (posts.size()==0)
            throw new GenericException(HttpStatus.NOT_FOUND,"No posts found");
        return posts;
    }

    public String savedPost(long postId,long userId){
        Post post=findPostById(postId);
        User user=userService.findUserById(userId);
        if(!user.getSavedPost().contains(post)){
            user.getSavedPost().add(post);
            userRepository.save(user);
        }
        return "Post saved successfully";
    }
    public String unSavedPost(long postId,long userId){
        Post post=findPostById(postId);
        User user=userService.findUserById(userId);
        if(user.getSavedPost().contains(post)){
        user.getSavedPost().remove(post);
        userRepository.save(user);
        }
        return "Post unsaved successfully";
    }
    public Post likePost(long postId,long userId){
        Post post=findPostById(postId);
        User user=userService.findUserById(userId);
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setImage(user.getImage());
        userDto.setName(user.getName());
        post.getLikedByUsers().add(userDto);
        return postRepository.save(post);
    }
    public Post unLikePost(long postId,long userId){
        Post post=findPostById(postId);
        User user=userService.findUserById(userId);
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setImage(user.getImage());
        userDto.setName(user.getName());
        post.getLikedByUsers().remove(userDto);
        return postRepository.save(post);
    }


}
