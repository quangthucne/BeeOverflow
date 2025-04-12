package com.beeoverflow.apibeeoverflow.service;

import com.beeoverflow.apibeeoverflow.beans.QuestionBean;
import com.beeoverflow.apibeeoverflow.dto.QuestionDTO;
import com.beeoverflow.apibeeoverflow.entities.Account;
import com.beeoverflow.apibeeoverflow.entities.ImagesQue;
import com.beeoverflow.apibeeoverflow.entities.Question;
import com.beeoverflow.apibeeoverflow.jpas.AccountJPA;
import com.beeoverflow.apibeeoverflow.jpas.QuestionJPA;
import com.beeoverflow.apibeeoverflow.mappers.QuestionMapper;
import com.beeoverflow.apibeeoverflow.utils.CookiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService {

    @Autowired
    QuestionJPA questionJPA;

    @Autowired
    ImageService imageService;

    @Autowired
    AccountService accountService;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    CookiesUtil cookiesUtil;

    public List<QuestionDTO> getQuestions() {
        return questionMapper.questionsToQuestionDTOs(questionJPA.findAll());
    }

    public Question createQuestion(QuestionBean questionBean) {
        LocalDateTime now = LocalDateTime.now();
        Map<String, String> cookie = cookiesUtil.getCookie();
        Account account = accountService.getAccountById(Integer.parseInt(cookie.get("accountId")));
        Question question = new Question();

        question.setAccount(account);
        question.setTitle(questionBean.getTitle());
        question.setDetail(questionBean.getDetail());
        question.setCreatedDate(now);
        question.setIsCheck(false);
        question.setIsDeleted(false);

        Question newQues = questionJPA.save(question);

        if(questionBean.getImages() != null) {
            imageService.saveImagesQues(questionBean.getImages(), newQues);
        }


        return newQues;
    }

    public Question updateQuestion(QuestionBean questionBean) {
        LocalDateTime now = LocalDateTime.now();
        Question question = questionJPA.findById(questionBean.getId());

        System.out.println("question id: " + questionBean.getId());

        question.setAccount(accountService.getAccountById(questionBean.getAccountId()));
        question.setTitle(questionBean.getTitle());
        question.setDetail(questionBean.getDetail());
        question.setCreatedDate(now);
        question.setIsCheck(false);
        question.setIsDeleted(false);

        Question newQues = questionJPA.save(question);

        if(questionBean.getImages() != null) {
            imageService.deleteImagesQues(question.getImagesQues());
            imageService.saveImagesQues(questionBean.getImages(), newQues);
        }

        return newQues;
    }

    public Question deleteQuestion(QuestionBean questionBean) {
        Question question = questionJPA.findById(questionBean.getId());
        question.setIsDeleted(true);
        Question delQues = questionJPA.save(question);

        if (delQues.getIsDeleted()) {
            return delQues;
        } else {
            return null;
        }
    }
}
