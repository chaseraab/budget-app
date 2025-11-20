package com.budget_app.backend.base.jpa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController<T, ID, Req> {

    protected final BaseService<T, ID, Req> service;

    protected BaseController(BaseService<T, ID, Req> service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<T>> getAll() {
        return service.getAll();
    };

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable ID id) {
        return service.getById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<String> create(@RequestBody Req req) {
        return service.create(req);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable ID id) {
        return service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<T> updateById(@PathVariable ID id, @RequestBody Req req) {
        return service.update(id, req);
    }
}
