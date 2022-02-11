package com.example.final_case_social_web.repository;

import com.example.final_case_social_web.model.FriendRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;

public interface FriendRelationRepository extends JpaRepository<FriendRelation, Long> {
    @Modifying
    @Query(value = "select id from friend_relation where id_friend = :id1", nativeQuery = true)
    Iterable<BigInteger> findUserByIdFriend(@Param("id1") Long id2);

    @Modifying
    @Query(value = "select id from user_table where id not in (select id_friend from friend_relation where id_user = :id1) and id != :id2 ", nativeQuery = true)
    Iterable<BigInteger> findIdUserNotFriend(@Param("id1") Long id2, @Param("id2") Long id3);
}
