package com.budget_app.controller.allocation.snapshot;

import com.budget_app.controller.base.BaseController;
import com.budget_app.dto.allocation.snapshot.AllocationSnapshotRequest;
import com.budget_app.dto.allocation.snapshot.AllocationSnapshotResponse;
import com.budget_app.service.allocation.snapshot.AllocationSnapshotService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/allocations")
public class AllocationSnapshotController extends BaseController<Long, AllocationSnapshotRequest, AllocationSnapshotResponse> {

    public AllocationSnapshotController(AllocationSnapshotService service) {
        super(service);
    }

}
