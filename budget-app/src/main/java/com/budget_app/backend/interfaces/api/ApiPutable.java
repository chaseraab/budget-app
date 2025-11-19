package com.budget_app.backend.interfaces.api;

import org.springframework.http.ResponseEntity;

public interface ApiPutable<T, ID> {

    public ResponseEntity<T> update(ID id, T obj);

}
