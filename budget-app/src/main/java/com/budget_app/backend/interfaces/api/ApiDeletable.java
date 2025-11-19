package com.budget_app.backend.interfaces.api;

import org.springframework.http.ResponseEntity;

public interface ApiDeletable<ID> {

    public ResponseEntity<String> deleteById(ID id);

}
