package com.fintech.clientportfolios.exceptions;

public class PortfolioNotFoundExceptionResponse {

    private String portfolioNotFound;

    public PortfolioNotFoundExceptionResponse(String portfolioNotFound) {
        this.portfolioNotFound = portfolioNotFound;
    }

    public String getPortfolioNotFound() {
        return portfolioNotFound;
    }

    public void setPortfolioNotFound(String portfolioNotFound) {
        this.portfolioNotFound = portfolioNotFound;
    }
}
