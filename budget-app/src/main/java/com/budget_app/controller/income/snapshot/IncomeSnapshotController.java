package com.budget_app.controller.income.snapshot;

import com.budget_app.controller.base.BaseController;
import com.budget_app.dto.income.budget.IncomeBudgetRequest;
import com.budget_app.dto.income.budget.IncomeBudgetResponse;
import com.budget_app.service.income.budget.IncomeBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/income")
public class IncomeSnapshotController extends BaseController<Long, IncomeBudgetRequest, IncomeBudgetResponse> {

    @Autowired
    private final IncomeBudgetService incomeBudgetService;

    public IncomeSnapshotController(IncomeBudgetService incomeBudgetService) {
        super(incomeBudgetService);
        this.incomeBudgetService = incomeBudgetService;
    }

}
