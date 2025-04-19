package com.beeoverflow.apibeeoverflow.services;

import com.beeoverflow.apibeeoverflow.beans.AnswerBean;
import com.beeoverflow.apibeeoverflow.entities.Account;
import com.beeoverflow.apibeeoverflow.entities.Answer;
import com.beeoverflow.apibeeoverflow.entities.Question;
import com.beeoverflow.apibeeoverflow.utils.CookiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beeoverflow.apibeeoverflow.jpas.AnswerJPA;

@Service
public class AnswerService {
    
    @Autowired
    AnswerJPA answerJPA;

    @Autowired
    AccountService accountService;

    @Autowired
    QuestionService questionService;

    @Autowired
    ImageService imageService;

    @Autowired
    CookiesUtil cookiesUtil;

    public Answer findById(int id) {
        return answerJPA.findById(id);
    }

    public Answer create(AnswerBean answerBean) {
        Answer answer = new Answer();
        Account account = accountService.getAccountById(Integer.parseInt(cookiesUtil.getCookie().get("accountId")));
        Question question = questionService.getByQuestion(answerBean.getQuestionId());

        answer.setAccount(account);
        answer.setQues(question);
        answer.setDetail(answerBean.getDetail());
        answer.setIsDeleted(false);

        if (answerBean.getParentId() != 0) {
            answer.setParent(findById(answerBean.getParentId()));
        }

        Answer newAns = answerJPA.save(answer);

        if(answerBean.getImages() != null) {
            imageService.saveImagesAns(answerBean.getImages(), newAns);
        }

        return newAns;
    }

    public Answer update(AnswerBean answerBean) {
        Answer answer = answerJPA.findById(answerBean.getId());
        Account account = accountService.getAccountById(Integer.parseInt(cookiesUtil.getCookie().get("accountId")));
        Question question = questionService.getByQuestion(answerBean.getQuestionId());

        answer.setAccount(account);
        answer.setQues(question);
        answer.setDetail(answerBean.getDetail());
        answer.setIsDeleted(answerBean.getIsDeleted());

        if (answerBean.getParentId() != 0) {
            answer.setParent(findById(answerBean.getParentId()));
        }

        Answer updateAns = answerJPA.save(answer);

        if(answerBean.getImages() != null) {
            imageService.saveImagesAns(answerBean.getImages(), updateAns);
        }

        return updateAns;
    }

    public void delete(int id) {
        answerJPA.softDeleteAnswerTree(id);
    }

    
}
