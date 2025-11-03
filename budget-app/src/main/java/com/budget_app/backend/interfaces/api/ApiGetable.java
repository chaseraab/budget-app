package com.budget_app.backend.interfaces.api;

import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ApiGetable<T> {

    public ResponseEntity<List<T>> getAll();
    public ResponseEntity<T> getByName(String name);

}
