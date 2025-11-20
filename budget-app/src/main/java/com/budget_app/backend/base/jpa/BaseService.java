package com.budget_app.backend.base.jpa;

import com.budget_app.backend.interfaces.api.ApiDeletable;
import com.budget_app.backend.interfaces.api.ApiGetable;
import com.budget_app.backend.interfaces.api.ApiPostable;
import com.budget_app.backend.interfaces.api.ApiPutable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

public abstract class BaseService<T, ID, Req, Res> implements ApiGetable<ID, Res>, ApiDeletable<ID>, ApiPostable<Req>, ApiPutable<ID, Req, Res> {

    protected final JpaRepository<T, ID> repository;

    protected BaseService(JpaRepository<T, ID> repository) {this.repository = repository;}

    public ResponseEntity<List<Res>> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Res> getById(ID id) {
        try {
            Optional<T> result = repository.findById(id);
            return result
                    .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteById(ID id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>("Deletion Successful", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> create(Req req) {
        try {
            repository.save(req);
            return new ResponseEntity<>("Object Successfully Created", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
            }
    }

    public ResponseEntity<Res> update(ID id, Req req) {
        Optional<T> search = repository.findById(id);
        if (search.isEmpty()) {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        T oldObj = search.get();
        updateFields(req, oldObj);
        repository.save(oldObj);
        return new ResponseEntity<>(oldObj, HttpStatus.OK);
    }

    protected abstract void updateFields(T newObj, T oldObj);

}
