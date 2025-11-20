package com.budget_app.backend.interfaces.api;

import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ApiGetable<ID, Res> {

    public ResponseEntity<List<Res>> getAll();
    public ResponseEntity<Res> getById(ID id);
}
