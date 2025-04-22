package com.beeoverflow.apibeeoverflow.services;

import com.beeoverflow.apibeeoverflow.beans.QuesVoteBean;
import com.beeoverflow.apibeeoverflow.entities.Account;
import com.beeoverflow.apibeeoverflow.entities.Question;
import com.beeoverflow.apibeeoverflow.entities.QuestionVote;
import com.beeoverflow.apibeeoverflow.jpas.QuestionVoteJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    QuestionVoteJPA questionVoteJPA;

    @Autowired
    ReputationService reputationService;

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
        QuestionVote questionVote = getQuesVoteById(quesVoteBean.getId());
        Account account = questionVote.getQues().getAccount();
        questionVote.setCount(questionVote.getCount() + quesVoteBean.getCount());
        questionVoteJPA.save(questionVote);
        reputationService.update(account, point);
        return questionVote;
    }


}
