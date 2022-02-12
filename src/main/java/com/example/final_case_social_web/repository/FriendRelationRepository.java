package com.example.final_case_social_web.repository;

import com.example.final_case_social_web.model.FriendRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Optional;

public interface FriendRelationRepository extends JpaRepository<FriendRelation, Long> {
    @Modifying
    @Query(value = "select id_user from friend_relation where id_friend = :id1 and status = '1'", nativeQuery = true)
    Iterable<BigInteger> findUserByIdFriend(@Param("id1") Long id2);

    @Modifying
    @Query(value = "select id from user_table where id not in (select id_friend from friend_relation where id_user = :id1) and id != :id2 ", nativeQuery = true)
    Iterable<BigInteger> findIdUserNotFriend(@Param("id1") Long id2, @Param("id2") Long id3);

    Optional<FriendRelation> findByIdUserAndIdFriend(Long idUser, Long idFriend);

    @Modifying
    @Query(value = "select COUNT(DISTINCT id_friend) from friend_relation where id_user = :id1 and status = '2'", nativeQuery = true)
    Integer countListFriend(@Param("id1") Long id2);

}
