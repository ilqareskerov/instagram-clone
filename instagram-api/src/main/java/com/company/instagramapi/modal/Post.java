package com.company.instagramapi.modal;

import com.company.instagramapi.dto.UserDto;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String caption;
    @Column(insertable=false, updatable=false)
    private String image;
    private String location;
    private LocalDateTime created_at;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id",column = @Column(name = "user_id")),
            @AttributeOverride(name="email",column = @Column(name = "user_email")),
    })
    private UserDto user;
    @OneToMany
    private List<Comment> comments=new ArrayList<>();
    @Embedded
    @ElementCollection
    @JoinTable(
            name = "post_liked_by_users",
            joinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserDto> likedByUsers=new HashSet<>();

    public Post(long id,
                String caption,
                String image,
                String location,
                LocalDateTime created_at,
                UserDto user, List<Comment> comments,
                Set<UserDto> likedByUsers) {
        this.id = id;
        this.caption = caption;
        this.image = image;
        this.location = location;
        this.created_at = created_at;
        this.user = user;
        this.comments = comments;
        this.likedByUsers = likedByUsers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<UserDto> getLikedByUsers() {
        return likedByUsers;
    }

    public void setLikedByUsers(Set<UserDto> likedByUsers) {
        this.likedByUsers = likedByUsers;
    }
}
