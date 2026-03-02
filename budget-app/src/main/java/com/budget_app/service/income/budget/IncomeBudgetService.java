//package com.budget_app.service.income.budget;
//
//import com.budget_app.domain.account.Account;
//import com.budget_app.domain.budget.Budget;
//import com.budget_app.domain.income.budget.IncomeBudget;
//import com.budget_app.dto.income.budget.IncomeBudgetRequest;
//import com.budget_app.dto.income.budget.IncomeBudgetResponse;
//import com.budget_app.mapper.income.budget.IncomeBudgetMapper;
//import com.budget_app.repository.account.AccountRepository;
//import com.budget_app.repository.income.budget.IncomeBudgetRepository;
//import com.budget_app.service.base.BaseService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//@Service
//public class IncomeBudgetService extends BaseService<IncomeBudget, Long, IncomeBudgetRequest, IncomeBudgetResponse> {
//
//    private final AccountRepository accountRepository;
//    private final IncomeBudgetMapper mapper;
//
//    public IncomeBudgetService(IncomeBudgetRepository incomeBudgetRepository, AccountRepository accountRepository, IncomeBudgetMapper mapper) {
//        super(incomeBudgetRepository);
//        this.accountRepository = accountRepository;
//        this.mapper = mapper;
//    }
//
//    private Account getAccount(Long id) {
//        return accountRepository.findById(id)
//                .orElse(null);
//    }
//
//    @Override
//    public IncomeBudget toEntity(IncomeBudgetRequest req) { return mapper.toEntity(req); }
//
//    @Override
//    public IncomeBudgetResponse toResponse(IncomeBudget entity) { return mapper.toResponse(entity); }
//
//    public ResponseEntity<IncomeBudgetResponse> update(Long id, IncomeBudgetRequest request) {
//        return repository.findById(id)
//                .map(existing -> {
//                    Account account = accountRepository.findById(request.accountId())
//                            .orElseThrow(() -> new RuntimeException("Account not found"));
//                    existing.setAccount(account);
//                    existing.setAmount(request.amount());
//                    existing.setName(request.name());
//
//
//                    repository.save(existing);
//                    return ResponseEntity.ok(toResponse(existing));
//                })
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    public ResponseEntity<IncomeBudgetResponse> create(IncomeBudgetRequest request) {
//        Account account = getAccount(request.accountId());
//        Budget budget =
//
//        IncomeBudget incomeBudget = new IncomeBudget()
//                .setAmount(request.amount())
//                .setName(request.name())
//
//                .setAccount(account);
//
//        repository.save(incomeBudget);
//
////        return new ResponseEntity<>(HttpStatus.OK);
//        return ResponseEntity.ok(toResponse(incomeBudget));
//    }
//
//}
