package com.example.final_case_social_web.controller;

import com.example.final_case_social_web.model.FriendRelation;
import com.example.final_case_social_web.model.User;
import com.example.final_case_social_web.service.friend_relation.FriendRelationServiceImpl;
import com.example.final_case_social_web.service.friend_relation.IFriendRelationService;
import com.example.final_case_social_web.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/relationships")
public class FriendRelationController {

    @Autowired
    private IFriendRelationService friendRelationService;

    @Autowired
    private IUserService userService;

    @GetMapping("")
    public ResponseEntity<Iterable<FriendRelation>> listAllFriendRelation() {
        Iterable<FriendRelation> friendRelations = friendRelationService.findAll();
        if (friendRelations == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(friendRelations, HttpStatus.OK);

    }

    @GetMapping(value = "/notFriend/{idU}")
    public ResponseEntity<List<User>> getListNotFriend(@PathVariable Long idU) {
        List<User> users = new ArrayList<>();
        Iterable<BigInteger> idUserNotFriend = friendRelationService.findAllIdUserNotFriend(idU,idU);
        for (BigInteger id : idUserNotFriend) {
            Optional<User> user = userService.findById(id.longValue());
            users.add(user.get());
        }
        if (users == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FriendRelation> getFriendRelation(@PathVariable Long id) {
        Optional<FriendRelation> friendRelation = friendRelationService.findById(id);
        if (friendRelation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(friendRelation.get(), HttpStatus.OK);
    }

    @GetMapping("/addFriend/{idUser}/{idFriend}")
    public ResponseEntity<FriendRelation> addFriend(@PathVariable("idUser") Long idUser, @PathVariable("idFriend") Long idFriend) {
        FriendRelation friendRelation = new FriendRelation(idUser, idFriend, "1");
        friendRelationService.save(friendRelation);
        return new ResponseEntity<>(friendRelation, HttpStatus.OK);
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