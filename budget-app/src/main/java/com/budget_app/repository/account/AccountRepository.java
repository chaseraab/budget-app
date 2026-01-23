package com.budget_app.repository.account;

import com.budget_app.domain.account.Account;
import com.budget_app.repository.base.BaseRepository;
import java.util.Optional;

public interface AccountRepository extends BaseRepository<Account, Long> {

    Optional<Account> findByName(String name);
}