package com.budget_app.service.api;

import org.springframework.http.ResponseEntity;

public interface ApiDeletable<ID> {

    public ResponseEntity<String> deleteById(ID id);

}
