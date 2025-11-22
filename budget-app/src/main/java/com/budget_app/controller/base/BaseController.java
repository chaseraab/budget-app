package com.budget_app.controller.base;

import com.budget_app.service.base.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController<ID, Req, Res> {

    protected final BaseService<?, ID, Req, Res> service;

    protected BaseController(BaseService<?, ID, Req, Res> service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Res>> getAll() {
        return service.getAll();
    };

    @GetMapping("/{id}")
    public ResponseEntity<Res> getById(@PathVariable ID id) {
        return service.getById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<Res> create(@RequestBody Req req) {
        return service.create(req);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable ID id) {
        return service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Res> updateById(@PathVariable ID id, @RequestBody Req req) {
        return service.update(id, req);
    }
}
