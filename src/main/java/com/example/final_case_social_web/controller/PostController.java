package com.example.final_case_social_web.controller;

import com.example.final_case_social_web.model.Post;
import com.example.final_case_social_web.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            postService.save(post);
            return new ResponseEntity<>(post, HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody Post post) {
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
//        @GetMapping("/search/{key}")
//        public ResponseEntity<Iterable<Post>> findByStatusContaining(String key){
//            Iterable<Post> postIterable=   postService.findByStatusContaining(key);
//            return  new ResponseEntity<>(postIterable,HttpStatus.OK);
//        }

}
