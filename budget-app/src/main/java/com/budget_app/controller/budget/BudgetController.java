package com.budget_app.controller.budget;

import com.budget_app.controller.base.BaseController;
import com.budget_app.domain.income.budget.IncomeBudget;
import com.budget_app.dto.allocation.budget.AllocationBudgetRequest;
import com.budget_app.dto.budget.BudgetRequest;
import com.budget_app.dto.budget.BudgetResponse;
import com.budget_app.dto.income.budget.IncomeBudgetRequest;
import com.budget_app.dto.transaction.TransactionRequest;
import com.budget_app.service.budget.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budgets/")
public class BudgetController extends BaseController<Long, BudgetRequest, BudgetResponse> {

    @Autowired
    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        super(budgetService);
        this.budgetService = budgetService;
    }

    @PostMapping("/{budgetId}/allocation/new")
    public ResponseEntity<BudgetResponse> addAllocation(@PathVariable Long budgetId, @RequestBody AllocationBudgetRequest request) {
        return budgetService.addAllocation(budgetId, request);
    }

    @DeleteMapping("/{budgetId}/allocation/{allocationId}")
    public ResponseEntity<String> deleteAllocation(@PathVariable Long budgetId, @PathVariable Long allocationId) {
        return budgetService.deleteAllocation(budgetId, allocationId);
    }

    @PutMapping("/{budgetId}/allocation/{allocationId}")
    public ResponseEntity<BudgetResponse> updateAllocation(@PathVariable Long budgetId, @PathVariable Long allocationId, @RequestBody AllocationBudgetRequest request) {
        return budgetService.updateAllocation(budgetId, allocationId, request);
    }

    @PostMapping("/{budgetId}/income/new")
    public ResponseEntity<BudgetResponse> addIncome(@PathVariable Long budgetId, @RequestBody IncomeBudgetRequest request) {
        return budgetService.addIncome(budgetId, request);
    }

    @DeleteMapping("/{budgetId}/income/{incomeId}")
    public ResponseEntity<String> deleteIncome(@PathVariable Long budgetId, @PathVariable Long incomeId) {
        return budgetService.deleteIncome(budgetId, incomeId);
    }

    @PutMapping("/{budgetId}/income/{incomeId}")
    public ResponseEntity<BudgetResponse> updateIncome(@PathVariable Long budgetId, @PathVariable Long incomeId, @RequestBody IncomeBudgetRequest request) {
        return budgetService.updateIncome(budgetId, incomeId, request);
    }

    @PostMapping("/{budgetId}/allocation/{allocationId}/transaction/new")
    public ResponseEntity<BudgetResponse> addTransaction(@PathVariable Long budgetId, @PathVariable Long allocationId, @RequestBody TransactionRequest request) {
        return budgetService.addTransaction(budgetId, allocationId, request);
    }

    @DeleteMapping("/{budgetId}/allocation/{allocationId}/transaction/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long budgetId, @PathVariable Long allocationId, @PathVariable Long transactionId) {
        return budgetService.deleteTransaction(budgetId, allocationId, transactionId);
    }

    @PutMapping("/{budgetId}/allocation/{allocationId}/transaction/{transactionId}")
    public ResponseEntity<BudgetResponse> updateTransaction(@PathVariable Long budgetId, @PathVariable Long allocationId, @PathVariable Long transactionId, @RequestBody TransactionRequest request) {
            return budgetService.updateTransaction(budgetId, allocationId, transactionId, request);
    }
}
