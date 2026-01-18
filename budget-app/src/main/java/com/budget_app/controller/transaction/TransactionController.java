package com.budget_app.controller.transaction;

import com.budget_app.controller.base.BaseController;
import com.budget_app.domain.transaction.Transaction;
import com.budget_app.dto.transaction.TransactionRequest;
import com.budget_app.dto.transaction.TransactionResponse;
import com.budget_app.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions/")
public class TransactionController extends BaseController<Long, TransactionRequest, TransactionResponse> {

    @Autowired
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        super(transactionService);
        this.transactionService = transactionService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<TransactionResponse>> searchTransactions(@RequestBody Map<String, Object> filters) {
        return transactionService.searchTransactions(filters);
    }
}
