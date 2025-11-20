package com.budget_app.backend.interfaces.api;

import com.budget_app.backend.implementation.account.Account;
import org.springframework.http.ResponseEntity;

public interface ApiPostable<Req> {

    public ResponseEntity<String> create(Req req);
}
