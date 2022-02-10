package com.example.final_case_social_web.model;

import javax.persistence.*;

@Entity
@Table(name = "friendRelation")
public class FriendRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUser;
    private Long idFriend;
    private String status;
}
