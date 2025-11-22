package com.budget_app.repository.accountBalance;

import com.budget_app.domain.accountBalance.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountBalanceRepository extends JpaRepository<AccountBalance, Long> {


}
