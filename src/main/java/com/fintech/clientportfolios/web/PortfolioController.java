package com.fintech.clientportfolios.web;

import com.fintech.clientportfolios.domain.Portfolio;
import com.fintech.clientportfolios.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/portfolio")
@CrossOrigin
public class PortfolioController {


    @Autowired
    private PortfolioService portfolioService;

    @PostMapping("")
    public ResponseEntity<?> createNewPortfolio(@Valid @RequestBody Portfolio portfolio, String username){

//        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
//        if(errorMap != null) return errorMap;

        Portfolio portfolio1 = portfolioService.saveOrUpdatePortfolio(portfolio, username);
        return new ResponseEntity<>(portfolio1, HttpStatus.CREATED);
    }

    @GetMapping("/{portfolioId}")
    public ResponseEntity<?> getPortfolioById(@PathVariable String portfolioId, String username){

        Portfolio portfolio = portfolioService.findByPortfolioIdentifier(portfolioId, username);

        return new ResponseEntity<>(portfolio, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Portfolio> getAllPortfolios(String username){
        return portfolioService.findAllPortfolios(username);
    }

    @DeleteMapping("/{portfolioId}")
    public ResponseEntity<?> deletePortfolio(@PathVariable String portfolioId, String username){
        portfolioService.deletePortfolioByIdentifier(portfolioId, username);

        return new ResponseEntity<>("Portfolio "+portfolioId+" deleted successfully", HttpStatus.OK);
    }


}
