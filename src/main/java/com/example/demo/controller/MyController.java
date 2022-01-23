package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Question;
import com.example.demo.form.AnswerForm;
import com.example.demo.service.QuestionService;
import com.example.demo.sub.ChooseAtRandom;
import com.example.demo.sub.CountNumOfCorrectAnswer;
import com.example.demo.sub.IndexCounter;

@Controller
public class MyController {

    @Autowired
    QuestionService questionService;

    @Autowired
    ChooseAtRandom choose;

    @Autowired
    IndexCounter counter;

    @Autowired
    CountNumOfCorrectAnswer count;

    @GetMapping("/")
    public ModelAndView index(ModelAndView mv) {

        counter.setI(0);

        // 正解数0にリセット
        count.setNumOfCorrectAnswer(0);

        // 正しい答えをリセット
        count.setCorrectAnswer(null);

        // 問題番号をランダムに10個選択する
        choose.createRandomList();

        mv.setViewName("index");
        return mv;
    }

    @PostMapping("/toQuestion")
    public ModelAndView toQ(ModelAndView mv, @ModelAttribute AnswerForm form) {
        List<Integer> list = choose.getList();

        // 問題番号を取得
        int i = counter.getI();

        // 解答者の答えを取得
        String yourAnswer = form.getAnswer();

        // 正しい答えを取得
        String correctAnswer = count.getCorrectAnswer();

        // 正しい正解数を取得
        int numOfCorrectAnswer = count.getNumOfCorrectAnswer();

        // 正解していれば正解数を1増やす
        if (i > 0 && yourAnswer.equals(correctAnswer)) {
            numOfCorrectAnswer++;
        }

        count.setNumOfCorrectAnswer(numOfCorrectAnswer);

        Question q = null;
        int num = 0;

        // 問題番号が10未満であれば、次の問題と解答をセット
        if (i < 10) {
            int number = list.get(i);
            q = questionService.getQuestion(number);
            count.setCorrectAnswer(q.getAnswer());

            num = i + 1;
        }

        i++;
        counter.setI(i);

        form.setAnswer("A");

        if (i > 10) {
            mv.addObject("numOfCorrectAnswer", numOfCorrectAnswer);
            mv.setViewName("result");
        } else {
            mv.addObject("form", form);
            mv.addObject("num", num);
            mv.addObject("q", q);
            mv.addObject("numOfCorrectAnswer", numOfCorrectAnswer);
            mv.setViewName("question");
        }

        return mv;
    }

    @GetMapping("/toTOP")
    public String toTOP() {
        return "redirect:/";
    }

}
