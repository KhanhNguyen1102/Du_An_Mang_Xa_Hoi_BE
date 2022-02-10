package com.example.final_case_social_web.repository;

import com.example.final_case_social_web.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Iterable<Post> findByStatusContaining (String status);
}
