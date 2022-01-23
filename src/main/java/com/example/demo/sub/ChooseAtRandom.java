package com.example.demo.sub;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.example.demo.service.QuestionService;

import lombok.Getter;
import lombok.Setter;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Setter
@Getter
public class ChooseAtRandom {
    @Autowired
    QuestionService questionService;

    private List<Integer> list;

    // 問題番号のリストをランダムに作成
    public void createRandomList() {
        int count = questionService.count();
        list = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int randomValue = random.nextInt(count) + 1;
            while (list.contains(randomValue)) {
                randomValue = random.nextInt(count) + 1;
            }
            list.add(randomValue);
        }
    }
}
