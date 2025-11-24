package com.budget_app.controller.allocation;

import com.budget_app.controller.base.BaseController;
import com.budget_app.dto.allocation.AllocationRequest;
import com.budget_app.dto.allocation.AllocationResponse;
import com.budget_app.service.allocation.AllocationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/allocations")
public class AllocationController extends BaseController<Long, AllocationRequest, AllocationResponse> {

    public AllocationController(AllocationService service) {
        super(service);
    }

}
