package com.budget_app.controller.income;

import com.budget_app.controller.base.BaseController;
import com.budget_app.dto.income.IncomeRequest;
import com.budget_app.dto.income.IncomeResponse;
import com.budget_app.service.income.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/income")
public class IncomeController extends BaseController<Long, IncomeRequest, IncomeResponse> {

    @Autowired
    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        super(incomeService);
        this.incomeService = incomeService;
    }

}
