package com.budget_app.repository.accountBalance;

import com.budget_app.domain.accountBalance.AccountBalance;
import com.budget_app.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountBalanceRepository extends BaseRepository<AccountBalance, Long> {
    @Query("""
    select ab
    from AccountBalance ab
    where ab.account.isActive = true
      and ab.date = (
          select max(ab2.date)
          from AccountBalance ab2
          where ab2.account = ab.account
      )
""")
    List<AccountBalance> findLatestPerActiveAccount();
}
