package com.budget_app.backend.interfaces.api;

import org.springframework.http.ResponseEntity;

public interface ApiDeletable {

    public ResponseEntity<String> deleteById(Long id);

}
