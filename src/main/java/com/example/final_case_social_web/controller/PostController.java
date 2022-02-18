package com.example.final_case_social_web.controller;

import com.example.final_case_social_web.model.Post;
import com.example.final_case_social_web.model.User;
import com.example.final_case_social_web.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
@RestController
@CrossOrigin("*")
@RequestMapping("/api/posts")
public class PostController {

        @Autowired
        IPostService postService;
        @GetMapping("")
        public ResponseEntity<Iterable<Post>> findAll() {

            Iterable<Post> postIterable = postService.findAll();
            return new ResponseEntity<>(postIterable, HttpStatus.OK);
        }

        @PostMapping("")
        public ResponseEntity<Post> create(@RequestBody Post post) {
            post.setCreateAt(LocalDate.now());
            postService.save(post);
            return new ResponseEntity<>(post, HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody Post post) {
            post.setCreateAt(LocalDate.now());
            post.setId(id);
            postService.save(post);
            return new ResponseEntity<>(post, HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Post> delete(@PathVariable Long id) {
            postService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Post> findOne(@PathVariable Long id) {
            Optional<Post> postOptional = postService.findById(id);
            return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
        }
    @GetMapping("friends")
    public ResponseEntity<Iterable<Post>> findAllFriend() {

        Iterable<Post> postIterable = postService.findAllByStatusFriend();
        return new ResponseEntity<>(postIterable, HttpStatus.OK);
    }
    @GetMapping("notFriends")
    public ResponseEntity<Iterable<Post>> findAllNotFriend() {

        Iterable<Post> postIterable = postService.findAllByStatusNotFriend();
        return new ResponseEntity<>(postIterable, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Iterable<Post>> findAllByUserId(@RequestParam Long q){
        Iterable<Post> postIterable=   postService.findAllByUserId(q);
        return  new ResponseEntity<>(postIterable,HttpStatus.OK);
    }

    @GetMapping("/postOfFriends/{id}")
    public ResponseEntity<Optional<Post>> findPostByIdFriend(@PathVariable Long id) {
        Optional<Post> post = postService.findTop1NewByUserId(id);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

}
