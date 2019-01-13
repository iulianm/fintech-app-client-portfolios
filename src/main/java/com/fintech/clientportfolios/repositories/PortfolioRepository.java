package com.fintech.clientportfolios.repositories;

import com.fintech.clientportfolios.domain.Portfolio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends CrudRepository<Portfolio, Long> {

    Portfolio findByPortfolioIdentifier(String portfolioId);

    Iterable<Portfolio> findAllByUser(String username);

}
