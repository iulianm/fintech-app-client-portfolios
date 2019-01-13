package com.fintech.clientportfolios.services;

import com.fintech.clientportfolios.domain.Portfolio;
import com.fintech.clientportfolios.domain.User;
import com.fintech.clientportfolios.exceptions.PortfolioIdException;
import com.fintech.clientportfolios.exceptions.PortfolioNotFoundException;
import com.fintech.clientportfolios.repositories.PortfolioRepository;
import com.fintech.clientportfolios.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private UserRepository userRepository;

    public Portfolio saveOrUpdatePortfolio(Portfolio portfolio, String username){
        if(portfolio.getId() != null){
            Portfolio existingPortfolio = portfolioRepository.findByPortfolioIdentifier(portfolio.getPortfolioIdentifier());
            if(existingPortfolio !=null &&(!existingPortfolio.getUser().equals(username))){
                throw new PortfolioNotFoundException("Portfolio not found in your account");
            }else if(existingPortfolio == null){
                throw new PortfolioNotFoundException("Portfolio with ID: '"+portfolio.getPortfolioIdentifier()+"' cannot be updated because it does not exist");
            }
        }

        try{

            User user = userRepository.findByUsername(username);
            portfolio.setUser(user);
//            portfolio.setPortfolioLeader(user.getUsername());
            portfolio.setPortfolioIdentifier(portfolio.getPortfolioIdentifier().toUpperCase());

//            if(portfolio.getId()==null){
//                Backlog backlog = new Backlog();
//                portfolio.setBacklog(backlog);
//                backlog.setPortfolio(portfolio);
//                backlog.setPortfolioIdentifier(portfolio.getPortfolioIdentifier().toUpperCase());
//            }
//
//            if(portfolio.getId()!=null){
//                portfolio.setBacklog(backlogRepository.findByPortfolioIdentifier(portfolio.getPortfolioIdentifier().toUpperCase()));
//            }

            return portfolioRepository.save(portfolio);

        }catch (Exception e){
            throw new PortfolioIdException("Portfolio with ID '"+portfolio.getPortfolioIdentifier().toUpperCase()+"' already exists");
        }
    }

    public Portfolio findByPortfolioIdentifier(String portfolioId, String username){
        Portfolio portfolio =  portfolioRepository.findByPortfolioIdentifier(portfolioId.toUpperCase());

        if(portfolio == null){
            throw new PortfolioIdException("Portfolio with ID '" + portfolioId + "' does not exist");
        }

        if(!portfolio.getUser().equals(username)){
            throw new PortfolioNotFoundException("Portfolio not found in your account");
        }

        return portfolio;
    }

    public Iterable<Portfolio> findAllPortfolios(String username){
        return portfolioRepository.findAllByUser(username);
    }

    public void deletePortfolioByIdentifier(String portfolioId, String username){

        portfolioRepository.delete(findByPortfolioIdentifier(portfolioId, username));
    }

}
