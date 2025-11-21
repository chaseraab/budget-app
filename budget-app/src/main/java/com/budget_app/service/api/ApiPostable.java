package com.budget_app.service.api;

import org.springframework.http.ResponseEntity;

public interface ApiPostable<Req, Res> {

    public ResponseEntity<Res> create(Req req);
}
