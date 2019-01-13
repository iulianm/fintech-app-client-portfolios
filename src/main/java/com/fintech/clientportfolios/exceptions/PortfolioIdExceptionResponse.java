package com.fintech.clientportfolios.exceptions;

public class PortfolioIdExceptionResponse {

    private String portfolioIdentifier;

    public PortfolioIdExceptionResponse(String portfolioIdentifier) {
        this.portfolioIdentifier = portfolioIdentifier;
    }

    public String getPortfolioIdentifier() {
        return portfolioIdentifier;
    }

    public void setPortfolioIdentifier(String portfolioIdentifier) {
        this.portfolioIdentifier = portfolioIdentifier;
    }
}
