package com.budget_app.controller.allocation;

import com.budget_app.controller.base.BaseController;
import com.budget_app.dto.allocation.budget.AllocationBudgetRequest;
import com.budget_app.dto.allocation.budget.AllocationBudgetResponse;
import com.budget_app.service.allocation.budget.AllocationBudgetService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/allocations")
public class AllocationController extends BaseController<Long, AllocationBudgetRequest, AllocationBudgetResponse> {

    public AllocationController(AllocationBudgetService service) {
        super(service);
    }

}
