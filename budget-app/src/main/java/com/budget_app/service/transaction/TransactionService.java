package com.budget_app.service.transaction;

import com.budget_app.csvHandler.CSVReader;
import com.budget_app.csvHandler.CSVTransactionConverter;
import com.budget_app.domain.account.Account;
import com.budget_app.domain.allocation.Allocation;
import com.budget_app.domain.transaction.Transaction;
import com.budget_app.dto.transaction.TransactionRequest;
import com.budget_app.dto.transaction.TransactionResponse;
import com.budget_app.mapper.transaction.TransactionMapper;
import com.budget_app.repository.account.AccountRepository;
import com.budget_app.repository.allocation.AllocationRepository;
import com.budget_app.repository.transaction.TransactionRepository;
import com.budget_app.service.base.BaseService;
import com.budget_app.service.storageService.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionService extends BaseService<Transaction, Long, TransactionRequest, TransactionResponse> {

    private final AccountRepository accountRepository;
    private final AllocationRepository allocationRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper mapper;
    private final StorageService storageService;
    private final CSVReader csvReader;
    private final CSVTransactionConverter csvTransactionConverter;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, AllocationRepository allocationRepository,
                              TransactionMapper mapper, StorageService storageService, CSVReader csvReader, CSVTransactionConverter csvTransactionConverter) {
        super(transactionRepository);
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.allocationRepository = allocationRepository;
        this.mapper = mapper;
        this.storageService = storageService;
        this.csvReader = csvReader;
        this.csvTransactionConverter = csvTransactionConverter;
    }

    @Override
    public Transaction toEntity(TransactionRequest req) { return mapper.toEntity(req); }

    @Override
    public TransactionResponse toResponse(Transaction entity) { return mapper.toResponse(entity); }

    public ResponseEntity<TransactionResponse> update(Long id, TransactionRequest request) {
        return repository.findById(id)
                .map(existing -> {
                    Account account = accountRepository.findById(request.accountId())
                            .orElseThrow(() -> new RuntimeException("Account not found"));
                    Allocation allocation = allocationRepository.findById(request.allocationId())
                            .orElseThrow(() -> new RuntimeException("Allocation not found"));
                    existing.setAccount(account);
                    existing.setAllocation(allocation);
                    existing.setAmount(request.amount());
                    existing.setDate(request.date());
                    existing.setItem(request.item());
                    existing.setCompany(request.company());

                    repository.save(existing);
                    return ResponseEntity.ok(toResponse(existing));
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<TransactionResponse> create(TransactionRequest request) {
        System.out.println("Received request: " + request.accountId() + " " + request.allocationId());
        Account account = accountRepository.findById(request.accountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        Allocation allocation = allocationRepository.findById(request.allocationId())
                .orElseThrow(() -> new RuntimeException("Allocation not found"));

        Transaction transaction = new Transaction()
                .setAccount(account)
                .setAllocation(allocation)
                .setAmount(request.amount())
                .setItem(request.item())
                .setDate(request.date())
                .setCompany(request.company());
        repository.save(transaction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

   public ResponseEntity<String> upload(MultipartFile file) {
        String fileLocation = storageService.store(file);
        try {
            List<String> contents = csvReader.ReadCSV(fileLocation);
            List<Transaction> transactions = contents.stream()
                    .map(csvTransactionConverter::convert)
                    .filter(Objects::nonNull)
                    .toList();
            transactions
                    .forEach(t ->
                            System.out.println(
                                    "Date: " + t.getDate() +
                                            ", Item: " + t.getItem() +
                                            ", Company: " + t.getCompany() +
                                            ", Amount: " + t.getAmount()
                            )
                    );

        } catch (Exception e){
            throw new RuntimeException(e);
       }


        return null;
    }

}
