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
@RequestMapping("/api/friends")
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
        Iterable<BigInteger> idUserNotFriend = friendRelationService.findAllIdUserNotFriend(idU, idU);
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

    //  API gửi lời mời kết bạn
    @PostMapping("/{idUser}/{idFriend}")
    public ResponseEntity<FriendRelation> addFriend(@PathVariable("idUser") Long idUser, @PathVariable("idFriend") Long idFriend) {
        FriendRelation friendRequestSend = new FriendRelation(idUser, idFriend, "1");
        FriendRelation friendRequestReceive = new FriendRelation(idFriend, idUser, "3");
        friendRelationService.save(friendRequestSend);
        friendRelationService.save(friendRequestReceive);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // API tìm kiếm User gửi Request kết bạn đến mình
    @GetMapping("/friendRequest/{idUser}")
    public ResponseEntity<List<User>> findRequest(@PathVariable("idUser") Long idUser) {
        List<User> users = new ArrayList<>();
        Iterable<BigInteger> idFriend = friendRelationService.findRequest(idUser);
        for (BigInteger id : idFriend) {
            Optional<User> user = userService.findById(id.longValue());
            users.add(user.get());
        }
        if (users == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // API đồng ý kết bạn
    @GetMapping("/acceptance/{idU}/{idRequest}")
    public ResponseEntity<Optional<FriendRelation>> acceptFriend(@PathVariable("idU") Long idUser, @PathVariable("idRequest") Long idRequest) {
        Optional<FriendRelation> friendRelation = friendRelationService.findByIdUserAndIdFriend(idRequest, idUser);
        if (friendRelation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        friendRelation.get().setStatus("2");
        friendRelationService.save(friendRelation.get());
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

    //    API hủy kết bạn, Hủy yêu cầu kết bạn
    @DeleteMapping(value = "/{idUser}/{idFriend}")
    public ResponseEntity<FriendRelation> deleteFriendRelation(@PathVariable("idUser") Long idUser, @PathVariable("idFriend") Long idFriend) {
        Optional<FriendRelation> friendRelation = friendRelationService.findByIdUserAndIdFriend(idUser, idFriend);
        if (friendRelation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        friendRelationService.remove(friendRelation.get().getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    Chức năng đang lỗi
//    API đếm số lượng bạn bè
//    @GetMapping("/allFriends/{idU}")
//    public ResponseEntity<Integer> countListFriend(@PathVariable("idU") Long idUser) {
//        Integer numberOfFriends = friendRelationService.countListFriend(idUser);
//        if (numberOfFriends == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(numberOfFriends, HttpStatus.OK);
//    }
}