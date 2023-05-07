package com.company.instagramapi.repository;

import com.company.instagramapi.modal.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("select p from Post p where p.user.id=:userId")
    List<Post> findByUserId(long userId);
    @Query("select p from Post p where p.user.id in :userIds order by p.created_at desc")
    List<Post> findAllPostByUserIds(List<Long> userIds);
}
