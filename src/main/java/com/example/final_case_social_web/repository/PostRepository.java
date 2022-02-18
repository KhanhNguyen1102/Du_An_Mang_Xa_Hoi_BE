package com.example.final_case_social_web.repository;

import com.example.final_case_social_web.model.Post;
import com.example.final_case_social_web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Iterable<Post> findByStatusContaining (String status);
    Iterable<Post> findAllByUserId(Long id);
    @Query(value = "select *from post where status =1 or status= 2",nativeQuery = true)
    Iterable<Post> findAllByStatusFriend();
    @Query(value = "select *from post where status =1",nativeQuery = true)
    Iterable<Post> findAllByStatusNotFriend();

    @Modifying
    @Query(value = "select * from Post where userId = :idUser order by id desc limit 1 ", nativeQuery = true)
    Optional<Post> findTop1NewByUserId(@Param("id") Long id);
}
