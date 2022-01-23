package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Question;
import com.example.demo.repository.QuestionRepository;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Transactional
    public Question getQuestion(Integer id) {
        Question question = questionRepository.getOne(id);
        return question;
    }

    @Transactional
    public int count() {
        int question = (int) questionRepository.count();
        return question;
    }

}
