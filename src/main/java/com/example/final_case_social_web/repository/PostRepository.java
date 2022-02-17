package com.example.final_case_social_web.repository;

import com.example.final_case_social_web.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Iterable<Post> findByStatusContaining (String status);
    @Query(value = "select *from post where status =1 or status= 2",nativeQuery = true)
    Iterable<Post> findAllByStatusFriend();
    @Query(value = "select *from post where status =1",nativeQuery = true)
    Iterable<Post> findAllByStatusNotFriend();
}
