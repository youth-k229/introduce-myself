package com.example.demo.sub;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Setter
@Getter
public class CountNumOfCorrectAnswer {

    private int numOfCorrectAnswer;

    private String correctAnswer;

}
