package com.kor.challenger.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Data
//@Table
//@Entity
public class Post {
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    //@OneToMany(mappedBy = "challenge", orphanRemoval = true)
    //@JoinColumn(name = "user_id")
    private List<ChallengeContent> challengeContents;

    //@ManyToOne
    //@JoinColumn(name = "user_id")
    private User author;

    //@OneToMany(mappedBy = "message", orphanRemoval = true)
    //@JsonManagedReference //УБЕРАЕТ ЦИКЛИЧЕСКИЕ ССЫЛКИ
    private List<ChallengeComment> challengeComments;

    public Post(Long id, String text) {
        this.id = id;
        this.text = text;
        challengeContents = new ArrayList<>(10);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ChallengeContent> getChallengeContents() {
        return challengeContents;
    }

    public void setChallengeContents(List<ChallengeContent> challengeContents) {
        this.challengeContents = challengeContents;
    }
}
