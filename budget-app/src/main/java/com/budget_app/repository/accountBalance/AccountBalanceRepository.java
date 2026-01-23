package com.budget_app.repository.accountBalance;

import com.budget_app.domain.accountBalance.AccountBalance;
import com.budget_app.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountBalanceRepository extends BaseRepository<AccountBalance, Long> {
}
