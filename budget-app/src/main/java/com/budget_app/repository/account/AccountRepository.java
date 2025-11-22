package com.budget_app.repository.account;

import com.budget_app.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByName(String name);
}