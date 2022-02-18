package com.example.final_case_social_web.service.post;

import com.example.final_case_social_web.model.Post;
import com.example.final_case_social_web.model.User;
import com.example.final_case_social_web.service.IGeneralService;

import java.util.Optional;

public interface IPostService extends IGeneralService<Post> {
    Iterable<Post> findByStatusContaining (String status);
    Iterable<Post> findAllByStatusFriend();
    Iterable<Post> findAllByStatusNotFriend();
    Iterable<Post> findAllByUserId(Long id);
    Optional<Post> findTop1NewByUserId(Long id);
}
