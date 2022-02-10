package com.example.final_case_social_web.service.friend_relation;

import com.example.final_case_social_web.model.FriendRelation;
import com.example.final_case_social_web.model.User;
import com.example.final_case_social_web.service.IGeneralService;

import java.math.BigInteger;

public interface IFriendRelationService extends IGeneralService<FriendRelation> {
    Iterable<BigInteger> findAllIdUserNotFriend(Long id, Long id1);
}
