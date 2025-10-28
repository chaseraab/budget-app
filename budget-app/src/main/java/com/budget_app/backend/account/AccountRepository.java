package main.java.com.budget_app.backend.account;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import main.java.com.budget_app.backend.account.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByName(String name);
}