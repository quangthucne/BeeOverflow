package com.beeoverflow.apibeeoverflow.mappers;

import com.beeoverflow.apibeeoverflow.dto.AccountDTO;
import com.beeoverflow.apibeeoverflow.dto.QuestionDTO;
import com.beeoverflow.apibeeoverflow.entities.Account;
import com.beeoverflow.apibeeoverflow.entities.Question;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AnswerMapper.class, AccountMapper.class})
public interface QuestionMapper {
    @Mapping(target = "answers", source = "answers")
    @Mapping(source = "account", target = "account")
    QuestionDTO questionToQuestionDTO(Question question);

//    @Named("accountToAccountDTO")
//    AccountDTO accountToAccountDTO(Account account);

    List<QuestionDTO> questionsToQuestionDTOs(List<Question> questions);

    List<AccountDTO> accountsToAccountDTOs(List<Account> accounts);

}
