package com.budget_app.service.base;

import com.budget_app.repository.base.BaseRepository;
import com.budget_app.search.specification.base.BaseSpecification;
import com.budget_app.service.api.ApiDeletable;
import com.budget_app.service.api.ApiGetable;
import com.budget_app.service.api.ApiPutable;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public abstract class BaseService<T, ID, Req, Res> implements ApiGetable<ID, Res>, ApiDeletable<ID>, ApiPutable<ID, Req, Res> {

    protected final BaseRepository<T, ID> repository;

    protected BaseService(BaseRepository<T, ID> repository) {this.repository = repository;}

    public abstract T toEntity(Req req);
    public abstract Res toResponse(T entity);

    public abstract ResponseEntity<Res> update(ID id, Req req);

    public ResponseEntity<List<Res>> getAll() {
        return ResponseEntity.ok(repository.findAll()
                .stream().map(this::toResponse)
                .toList()
        );
    }

    public ResponseEntity<Res> getById(ID id) {
        return repository.findById(id)
                .map(value -> ResponseEntity.ok(toResponse(value)))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<String> deleteById(ID id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            repository.deleteById(id);
            return ResponseEntity.ok("Deleted");
        }
    }

    public ResponseEntity<Res> create(Req req) {
        T saved = repository.save(toEntity(req));
        return ResponseEntity.ok(toResponse(saved));
    }

    public ResponseEntity<List<Res>> search(Map<String, Object> filters) {
        return ResponseEntity.ok(repository.findAll(new BaseSpecification<T>(filters))
                .stream().map(this::toResponse)
                .toList()
        );
    }

}
