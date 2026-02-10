package com.budget_app.controller.base;

import com.budget_app.service.base.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
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

    @GetMapping("/search")
    public ResponseEntity<List<Res>> search(@RequestBody Map<String, Object> params) {
        Map<String, Object> filters = new HashMap<>();
        params.forEach((key, value) -> {
            if (key.equals("date")) {
                filters.put(key, LocalDate.parse((CharSequence) value));
            } else {
                filters.put(key, value);
            }
        });
        return service.search(filters);
    }
}
