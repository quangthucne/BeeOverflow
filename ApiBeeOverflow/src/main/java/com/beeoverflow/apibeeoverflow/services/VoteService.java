package com.beeoverflow.apibeeoverflow.services;

import com.beeoverflow.apibeeoverflow.beans.QuesVoteBean;
import com.beeoverflow.apibeeoverflow.entities.Account;
import com.beeoverflow.apibeeoverflow.entities.Question;
import com.beeoverflow.apibeeoverflow.entities.QuestionVote;
import com.beeoverflow.apibeeoverflow.jpas.QuestionJPA;
import com.beeoverflow.apibeeoverflow.jpas.QuestionVoteJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    QuestionVoteJPA questionVoteJPA;

    @Autowired
    ReputationService reputationService;

    @Autowired
    QuestionJPA questionJPA;

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

        questionVote.setCount(questionVote.getCount() + quesVoteBean.getCount());
        questionVoteJPA.save(questionVote);
        reputationService.update(question.getAccount(), point);
        return questionVote;
    }


}
