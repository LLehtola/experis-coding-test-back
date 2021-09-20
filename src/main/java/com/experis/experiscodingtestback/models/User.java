package com.experis.experiscodingtestback.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "appusers")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name="answerdate")
    private Date answerdate;

    @Column(name="points")
    private Integer points;

    @Column(name="hasbeencontacted",columnDefinition = "boolean default false")
    private boolean hasbeencontacted;



    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    List<Answer> answers;

    @JsonGetter("answers")
    public List<String> answersGetter() {
        if(answers != null){
            return answers.stream()
                    .map(answer -> {
                        return "/api/v1/answers/" + answer.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isHasbeencontacted() {
        return hasbeencontacted;
    }

    public void setHasbeencontacted(boolean hasbeencontacted) {
        this.hasbeencontacted = hasbeencontacted;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Date getAnswerdate() {
        return answerdate;
    }

    public void setAnswerdate(Date answerdate) {
        this.answerdate = answerdate;
    }

    

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
