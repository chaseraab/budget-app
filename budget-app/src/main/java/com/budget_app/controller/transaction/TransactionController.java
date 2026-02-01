package com.budget_app.controller.transaction;

import com.budget_app.controller.base.BaseController;
import com.budget_app.dto.transaction.TransactionRequest;
import com.budget_app.dto.transaction.TransactionResponse;
import com.budget_app.dto.transaction.TransactionUploadResponse;
import com.budget_app.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/transactions/")
public class TransactionController extends BaseController<Long, TransactionRequest, TransactionResponse> {

    @Autowired
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        super(transactionService);
        this.transactionService = transactionService;
    }

    @PostMapping("/upload/{account}")
    public ResponseEntity<TransactionUploadResponse> upload(@RequestParam("file") MultipartFile file, @PathVariable long account) {
        return this.transactionService.upload(file, account);
    }

}
