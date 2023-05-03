package com.company.instagramapi.modal;

import com.company.instagramapi.dto.UserDto;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String name;
    private String email;
    private String mobile;
    private String website;
    private String bio;
    private String gender;
    private String image;
    private String password;
    @Embedded
    @ElementCollection
     Set<UserDto> follower=new HashSet<UserDto>();

    @ElementCollection
    @Embedded
    private Set<UserDto> following=new HashSet<UserDto>();
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Story> stories=new ArrayList<Story>();
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
     private List<Post> savedPost=new ArrayList<Post>();

    public User() {
    }

    public User(long id,
                String username,
                String name,
                String email,
                String mobile,
                String website,
                String bio,
                String gender,
                String image,
                String password,
                Set<UserDto> follower,
                Set<UserDto> following,
                List<Story> stories,
                List<Post> savedPost) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.website = website;
        this.bio = bio;
        this.gender = gender;
        this.image = image;
        this.password = password;
        this.follower = follower;
        this.following = following;
        this.stories = stories;
        this.savedPost = savedPost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserDto> getFollower() {
        return follower;
    }

    public void setFollower(Set<UserDto> follower) {
        this.follower = follower;
    }

    public Set<UserDto> getFollowing() {
        return following;
    }

    public void setFollowing(Set<UserDto> following) {
        this.following = following;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public List<Post> getSavedPost() {
        return savedPost;
    }

    public void setSavedPost(List<Post> savedPost) {
        this.savedPost = savedPost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getId() == user.getId() && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getName(), user.getName()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getMobile(), user.getMobile()) && Objects.equals(getWebsite(), user.getWebsite()) && Objects.equals(getBio(), user.getBio()) && Objects.equals(getGender(), user.getGender()) && Objects.equals(getImage(), user.getImage()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getFollower(), user.getFollower()) && Objects.equals(getFollowing(), user.getFollowing()) && Objects.equals(getStories(), user.getStories()) && Objects.equals(getSavedPost(), user.getSavedPost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getName(), getEmail(), getMobile(), getWebsite(), getBio(), getGender(), getImage(), getPassword(), getFollower(), getFollowing(), getStories(), getSavedPost());
    }
}