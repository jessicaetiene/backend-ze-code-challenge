package com.ze.codechallenge.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PartnerAlreadyExistsException extends RuntimeException {
    public PartnerAlreadyExistsException() {
        super("Partner already exists!");
    }
}
