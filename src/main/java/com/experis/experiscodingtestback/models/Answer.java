package com.experis.experiscodingtestback.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "answer")
    private String answer;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @JsonGetter("user")
    public String user() {
        if(user != null){
            return "/api/v1/users/" + user.getId();
        } else {
            return null;
        }
    }

    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question question;

    @JsonGetter("question")
    public String question() {
        if(question != null){
            return "/api/v1/questions/" + question.getId();
        } else {
            return null;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
