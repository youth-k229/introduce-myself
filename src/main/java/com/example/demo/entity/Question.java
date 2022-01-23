package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "questions")
@Setter
@Getter
public class Question implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "statement")
    private String statement;

    @Column(name = "A")
    private String A;

    @Column(name = "B")
    private String B;

    @Column(name = "C")
    private String C;

    @Column(name = "D")
    private String D;

    @Column(name = "answer")
    private String answer;

}
