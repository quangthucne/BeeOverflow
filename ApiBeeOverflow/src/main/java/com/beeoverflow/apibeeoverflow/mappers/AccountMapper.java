package com.beeoverflow.apibeeoverflow.mappers;

import com.beeoverflow.apibeeoverflow.dto.AccountDTO;
import com.beeoverflow.apibeeoverflow.dto.RankingDTO;
import com.beeoverflow.apibeeoverflow.dto.ReputationDTO;
import com.beeoverflow.apibeeoverflow.entities.Account;
import com.beeoverflow.apibeeoverflow.entities.Ranking;
import com.beeoverflow.apibeeoverflow.entities.Reputation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ReputationMapper.class})
public interface AccountMapper {
//    @Mapping(source = "reputation", target = "reputation")
    AccountDTO toAccountDTO(Account account);
//
//    // Tránh circular mapping
//    ReputationDTO toReputationDTO(Reputation reputation);


    List<AccountDTO> accountsToAccountDTOs(List<Account> accounts);
}
