package com.budget_app.backend.interfaces.api;

import org.springframework.http.ResponseEntity;

public interface ApiPutable<T> {

    public ResponseEntity<T> update(long id, T obj);

}
