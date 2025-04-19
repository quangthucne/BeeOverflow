package com.beeoverflow.apibeeoverflow.jpas;

import com.beeoverflow.apibeeoverflow.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountJPA extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);

    Account getByUsername(String username);

    Account findById(int id);
}
