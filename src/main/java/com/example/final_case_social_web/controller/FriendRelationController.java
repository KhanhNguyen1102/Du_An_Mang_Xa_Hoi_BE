package com.example.final_case_social_web.controller;

import com.example.final_case_social_web.model.FriendRelation;
import com.example.final_case_social_web.service.friend_relation.FriendRelationServiceImpl;
import com.example.final_case_social_web.service.friend_relation.IFriendRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/relationships")
public class FriendRelationController {

    @Autowired
    private IFriendRelationService friendRelationService;

    @GetMapping("")
    public ResponseEntity<Iterable<FriendRelation>> listAllFriendRelation() {
        Iterable<FriendRelation> friendRelations = friendRelationService.findAll();
        if (friendRelations == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(friendRelations, HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FriendRelation> getFriendRelation(@PathVariable Long id) {
        Optional<FriendRelation> friendRelation = friendRelationService.findById(id);
        if (friendRelation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(friendRelation.get(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Void> createFriendRelation(@RequestBody FriendRelation friendRelation) {
        friendRelationService.save(friendRelation);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FriendRelation> updateFriendRelation(@PathVariable("id") Long id, @RequestBody FriendRelation friendRelation) {
        Optional<FriendRelation> friendRelation1 = friendRelationService.findById(id);

        if (friendRelation1 == null) {
            return new ResponseEntity<FriendRelation>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<FriendRelation> deleteFriendRelation(@PathVariable("id") Long id) {
        Optional<FriendRelation> friendRelation = friendRelationService.findById(id);
        if (friendRelation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        friendRelationService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}