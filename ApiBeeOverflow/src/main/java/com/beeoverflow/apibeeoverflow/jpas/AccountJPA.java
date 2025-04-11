package com.beeoverflow.apibeeoverflow.jpas;

import com.beeoverflow.apibeeoverflow.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJPA extends JpaRepository<Account, Long> {

    Account findByUsername(String username);

    Account findById(int id);
}
