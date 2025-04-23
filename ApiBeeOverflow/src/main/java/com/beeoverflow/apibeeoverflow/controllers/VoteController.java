package com.beeoverflow.apibeeoverflow.controllers;

import com.beeoverflow.apibeeoverflow.beans.AnsVoteBean;
import com.beeoverflow.apibeeoverflow.beans.QuesVoteBean;
import com.beeoverflow.apibeeoverflow.response.Response;
import com.beeoverflow.apibeeoverflow.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    VoteService voteService;

    @PostMapping("/question")
    public ResponseEntity<Response> voteQuestion(@RequestBody QuesVoteBean quesVoteBean) {
        Response response = new Response();
        int point =0;
        switch (quesVoteBean.getType()){
            case 0:
                quesVoteBean.setCount(-1);
                point = -5;
                break;
            case 1:
                quesVoteBean.setCount(1);
                point = 3;
                break;
        }
        response.setStatus(1);
        response.setMessage("Vote successful");
        response.setData(voteService.voteQuestion(quesVoteBean, point));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/answer")
    public ResponseEntity<Response> voteAnswer(@RequestBody AnsVoteBean ansVoteBean) {
        Response response = new Response();
        int point = 0;
        switch (ansVoteBean.getType()) {
            case 0:
                ansVoteBean.setCount(-1);
                point = -5;
                break;
            case 1:
                ansVoteBean.setCount(1);
                point = 3;
                break;
        }
        response.setStatus(1);
        response.setMessage("Vote successful");
        response.setData(voteService.voteAnsVote(ansVoteBean, point));
        return ResponseEntity.ok(response);
    }
}
