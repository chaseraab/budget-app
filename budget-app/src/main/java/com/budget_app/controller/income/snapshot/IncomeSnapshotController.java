package com.budget_app.controller.income.snapshot;

import com.budget_app.controller.base.BaseController;
import com.budget_app.dto.income.snapshot.IncomeSnapshotRequest;
import com.budget_app.dto.income.snapshot.IncomeSnapshotResponse;
import com.budget_app.service.income.snapshot.IncomeSnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/income")
public class IncomeSnapshotController extends BaseController<Long, IncomeSnapshotRequest, IncomeSnapshotResponse> {

    @Autowired
    private final IncomeSnapshotService incomeSnapshotService;

    public IncomeSnapshotController(IncomeSnapshotService incomeSnapshotService) {
        super(incomeSnapshotService);
        this.incomeSnapshotService = incomeSnapshotService;
    }

}
