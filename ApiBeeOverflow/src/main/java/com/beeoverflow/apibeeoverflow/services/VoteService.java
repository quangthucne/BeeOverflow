package com.beeoverflow.apibeeoverflow.services;

import com.beeoverflow.apibeeoverflow.beans.AnsVoteBean;
import com.beeoverflow.apibeeoverflow.beans.QuesVoteBean;
import com.beeoverflow.apibeeoverflow.entities.*;
import com.beeoverflow.apibeeoverflow.jpas.AnswerVoteJPA;
import com.beeoverflow.apibeeoverflow.jpas.QuestionJPA;
import com.beeoverflow.apibeeoverflow.jpas.QuestionVoteJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    QuestionVoteJPA questionVoteJPA;

    @Autowired
    AnswerVoteJPA answerVoteJPA;

    @Autowired
    ReputationService reputationService;

    @Autowired
    QuestionJPA questionJPA;


    // Vote Question Service
    public QuestionVote getQuesVoteById(int id) {
        return questionVoteJPA.findById(id);
    }

    public QuestionVote createQuesVote(Question question) {
        QuestionVote questionVote = new QuestionVote();
        questionVote.setQues(question);
        questionVote.setCount(0);
        return questionVoteJPA.save(questionVote);
    }

    public QuestionVote voteQuestion(QuesVoteBean quesVoteBean, int point) {
        Question question = questionJPA.findById(quesVoteBean.getQuestionId());
        QuestionVote questionVote = getQuesVoteById(question.getId());
        Account account = question.getAccount();
        questionVote.setCount(questionVote.getCount() + quesVoteBean.getCount());
        QuestionVote newQuesVote = questionVoteJPA.save(questionVote);
        reputationService.update(account, point);
        return newQuesVote;
    }

    // Vote Answer Service
    public AnsVote getAnsVoteById(int id) {
        return answerVoteJPA.findById(id);
    }

    public AnsVote createAnsVote(Answer answer) {
        AnsVote ansVote = new AnsVote();
        ansVote.setAns(answer);
        ansVote.setCount(0);
        return answerVoteJPA.save(ansVote);
    }

    public AnsVote voteAnsVote(AnsVoteBean ansVoteBean, int point) {
        AnsVote ansVote = getAnsVoteById(ansVoteBean.getId());
        Account account = ansVote.getAns().getAccount();
        ansVote.setCount(ansVote.getCount() + ansVoteBean.getCount());
        AnsVote newAnsVote = answerVoteJPA.save(ansVote);
        reputationService.update(account, point);
        return newAnsVote;
    }

}
