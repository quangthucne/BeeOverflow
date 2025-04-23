package com.beeoverflow.apibeeoverflow.mappers;

import com.beeoverflow.apibeeoverflow.dto.AnswerDTO;
import com.beeoverflow.apibeeoverflow.dto.AccountDTO;
import com.beeoverflow.apibeeoverflow.entities.*;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {AccountMapper.class})
public interface AnswerMapper {

    @Mapping(target = "account", source = "account")
    @Mapping(source = "ques.id", target = "quesId")
    @Mapping(source = "parent.id", target = "parentId")
    AnswerDTO answerToAnswerDTO(Answer answer);

    @Named("accountToAccountDTO")
    AccountDTO accountToAccountDTO(Account account);

    List<AnswerDTO> answersToAnswerDTOs(List<Answer> answers);

    List<Answer> answerDTOsToAnswers(List<AnswerDTO> answerDTOs);


}