package com.beeoverflow.apibeeoverflow.services;

import com.beeoverflow.apibeeoverflow.beans.QuestionBean;
import com.beeoverflow.apibeeoverflow.beans.TagBean;
import com.beeoverflow.apibeeoverflow.dto.QuestionDTO;
import com.beeoverflow.apibeeoverflow.entities.Account;
import com.beeoverflow.apibeeoverflow.entities.Question;
import com.beeoverflow.apibeeoverflow.entities.Tag;
import com.beeoverflow.apibeeoverflow.jpas.QuestionJPA;
import com.beeoverflow.apibeeoverflow.mappers.QuestionMapper;
import com.beeoverflow.apibeeoverflow.utils.CookiesUtil;
import com.beeoverflow.apibeeoverflow.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    TagService tagService;

    @Autowired
    VoteService voteService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CookiesUtil cookiesUtil;
    @Autowired
    private ReputationService reputationService;

    public List<QuestionDTO> getQuestions() {
//        List<Question> questions = questionJPA.findAll().stream()
//                .sorted(Comparator.comparing(Question::getCreatedDate))
//                .collect(Collectors.toList());
        return questionMapper.questionsToQuestionDTOs(questionJPA.findAll());
    }

    public Question getByQuestion(int id) {
        return questionJPA.findById(id);
    }

    public QuestionDTO createQuestion(QuestionBean questionBean, TagBean tagBean) {
        LocalDateTime now = LocalDateTime.now();
        int point = 1;
        Account account = accountService.getAccountById(jwtUtil.extractUserId(jwtUtil.getTokenFromRequest()));
        Question question = new Question();

        question.setAccount(account);
        question.setTitle(questionBean.getTitle());
        question.setDetail(questionBean.getDetail());
        question.setCreatedDate(now);
        question.setIsCheck(false);
        question.setIsDeleted(false);

        Question newQues = questionJPA.save(question);

        voteService.createQuesVote(newQues);
        reputationService.update(account, point);

        if (questionBean.getImages() != null) {
            imageService.saveImages(questionBean.getImages(), newQues);
        }

        if (tagBean.getName() != null) {
            for (String name : tagBean.getName()) {
                Tag tag = new Tag();
                tag.setName(name);
                tag.setQuestion(newQues);
                tagService.createTag(tag);
            }

        }

        return questionMapper.questionToQuestionDTO(newQues);
    }

    public Question updateQuestion(QuestionBean questionBean) {
        LocalDateTime now = LocalDateTime.now();
        Question question = questionJPA.findById(questionBean.getId());
        Account account = accountService.getAccountById(jwtUtil.extractUserId(jwtUtil.getTokenFromRequest()));

        System.out.println("question id: " + questionBean.getId());

        question.setAccount(account);
        question.setTitle(questionBean.getTitle());
        question.setDetail(questionBean.getDetail());
        question.setCreatedDate(now);
        question.setIsCheck(false);
        question.setIsDeleted(false);

        Question newQues = questionJPA.save(question);

        if (questionBean.getImages() != null) {
            imageService.deleteImagesQues(question.getImagesQues());
            imageService.saveImages(questionBean.getImages(), newQues);
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
