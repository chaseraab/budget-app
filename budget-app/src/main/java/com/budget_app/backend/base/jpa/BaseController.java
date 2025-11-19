package com.budget_app.backend.base.jpa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController<T, ID> {

    protected final BaseService<T, ID> service;

    protected BaseController(BaseService<T, ID> service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<T>> getAll() {
        return service.getAll();
    };

    @PostMapping("/new/")
    public ResponseEntity<String> addAccount(@RequestBody T obj) {
        return service.create(obj);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable ID id) {
        return service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<T> updateById(@PathVariable ID id, @RequestBody T obj) {
        return service.update(id, obj);
    }
}
