package com.example.final_case_social_web.model;

import javax.persistence.*;

@Entity
@Table(name = "friendRelation")
public class FriendRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long user_id_send;
    private Long user_id_receive;
    private String status;
}
