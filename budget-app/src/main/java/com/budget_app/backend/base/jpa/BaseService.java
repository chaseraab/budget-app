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

public abstract class BaseService<T, ID> implements ApiGetable<T, ID>, ApiDeletable<ID>, ApiPostable<T>, ApiPutable<T, ID> {

    protected final JpaRepository<T, ID> repository;

    protected BaseService(JpaRepository<T, ID> repository) {this.repository = repository;}

    public ResponseEntity<List<T>> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<T> getById(ID id) {
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

    public ResponseEntity<String> create(T obj) {
        try {
            repository.save(obj);
            return new ResponseEntity<>("Object Successfully Created", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
            }
    }

    public ResponseEntity<T> update(ID id, T newObj) {
        Optional<T> search = repository.findById(id);
        if (search.isEmpty()) {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        T oldObj = search.get();
        updateFields(newObj, oldObj);
        repository.save(oldObj);
        return new ResponseEntity<>(oldObj, HttpStatus.OK);
    }

    protected abstract void updateFields(T newObj, T oldObj);

}
