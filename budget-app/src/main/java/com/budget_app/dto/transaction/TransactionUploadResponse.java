package com.budget_app.dto.transaction;

import com.budget_app.domain.transaction.Transaction;
import java.util.List;

public class TransactionUploadResponse {
    private List<Transaction> inserted;
    private List<Transaction> notInserted;

    public TransactionUploadResponse(List<Transaction> inserted, List<Transaction> notInserted) {
        this.inserted = inserted;
        this.notInserted = notInserted;
    }

    public List<Transaction> getInserted() { return inserted; }
    public List<Transaction> getNotInserted() { return notInserted; }

    public void setInserted(List<Transaction> inserted) { this.inserted = inserted; }
    public void setNotInserted(List<Transaction> notInserted) { this.notInserted = notInserted; }
}
