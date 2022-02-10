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

    public FriendRelation() {
    }

    public FriendRelation(Long id, Long idUser, Long idFriend, String status) {
        this.id = id;
        this.idUser = idUser;
        this.idFriend = idFriend;
        this.status = status;
    }

    public FriendRelation(Long idUser, Long idFriend, String status) {
        this.idUser = idUser;
        this.idFriend = idFriend;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdFriend() {
        return idFriend;
    }

    public void setIdFriend(Long idFriend) {
        this.idFriend = idFriend;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
