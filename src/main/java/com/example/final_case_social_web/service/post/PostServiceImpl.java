package com.example.final_case_social_web.service.post;

import com.example.final_case_social_web.model.Post;
import com.example.final_case_social_web.model.User;
import com.example.final_case_social_web.repository.PostRepository;
import com.example.final_case_social_web.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Iterable<Post> findByStatusContaining(String status) {
        return postRepository.findByStatusContaining(status);
    }

    @Override
    public Iterable<Post> findAllByStatusFriend() {
        return postRepository.findAllByStatusFriend();
    }

    @Override
    public Iterable<Post> findAllByStatusNotFriend() {
        return postRepository.findAllByStatusNotFriend();
    }

    @Override
    public Iterable<Post> findAllByUserId(Long id) {
        return postRepository.findAllByUserId(id);
    }

}
