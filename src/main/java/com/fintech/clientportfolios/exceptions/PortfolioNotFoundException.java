package com.fintech.clientportfolios.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PortfolioNotFoundException extends RuntimeException{

    public PortfolioNotFoundException(String message) {
        super(message);
    }
}
