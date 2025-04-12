package com.beeoverflow.apibeeoverflow.mappers;

import com.beeoverflow.apibeeoverflow.dto.AccountDTO;
import com.beeoverflow.apibeeoverflow.entities.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDTO accountToAccountDTO(Account account);

    List<AccountDTO> accountsToAccountDTOs(List<Account> accounts);
}
