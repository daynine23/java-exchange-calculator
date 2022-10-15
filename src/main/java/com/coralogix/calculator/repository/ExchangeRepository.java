package com.coralogix.calculator.repository;

import com.coralogix.calculator.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {

    Exchange getExchangeCValueByOriginCurrencyAndFinalCurrency(String oc, String fc);

    List<Exchange> findAll();
}
