package com.budget_app.backend.interfaces.api;

import org.springframework.http.ResponseEntity;

public interface ApiPutable<ID, Req, Res> {

    public ResponseEntity<Res> update(ID id, Req req);

}
