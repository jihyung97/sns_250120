package com.sns.post.repository;

import com.sns.post.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity,Integer> {
    public List<PostEntity> findByOrderById;
}
