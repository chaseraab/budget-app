package com.budget_app.service.base;

import com.budget_app.service.api.ApiDeletable;
import com.budget_app.service.api.ApiGetable;
import com.budget_app.service.api.ApiPostable;
import com.budget_app.service.api.ApiPutable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

public abstract class BaseService<T, ID, Req, Res> implements ApiGetable<ID, Res>, ApiDeletable<ID>, ApiPutable<ID, Req, Res> {

    protected final JpaRepository<T, ID> repository;

    protected BaseService(JpaRepository<T, ID> repository) {this.repository = repository;}

    public abstract T toEntity(Req req);
    public abstract Res toResponse(T entity);
    protected abstract void updateFields(T newObj, T oldObj);

    public abstract ResponseEntity<Res> create(Req req);

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

//    public ResponseEntity<Res> create(Req req) {
//        T saved = repository.save(toEntity(req));
//        return ResponseEntity.ok(toResponse(saved));
//    }

    public ResponseEntity<Res> update(ID id, Req req) {
        Optional<T> search = repository.findById(id);
        if (search.isEmpty()) {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        T oldObj = search.get();
        updateFields(toEntity(req), oldObj);
        repository.save(oldObj);
        return ResponseEntity.ok(toResponse(oldObj));
    }

}
