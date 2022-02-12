package com.example.final_case_social_web.service.friend_relation;

import com.example.final_case_social_web.model.FriendRelation;
import com.example.final_case_social_web.model.User;
import com.example.final_case_social_web.service.IGeneralService;

import java.math.BigInteger;
import java.util.Optional;

public interface IFriendRelationService extends IGeneralService<FriendRelation> {
    Iterable<BigInteger> findAllIdUserNotFriend(Long id, Long id1);
    Iterable<BigInteger> findUserByIdFriend(Long id);
    Optional<FriendRelation> findByIdUserAndIdFriend(Long idUser, Long idFriend);
    Integer countListFriend(Long idUser);
}
