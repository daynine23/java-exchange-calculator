package com.coralogix.calculator;

import com.coralogix.calculator.model.Exchange;
import com.coralogix.calculator.services.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/exchange")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @GetMapping("/all")
    public List<Exchange> getAllCurrencyExchange(){
        return exchangeService.getAllExchange();
    }

    @GetMapping()
    public String getCurrency(@RequestParam("origincurrency") String ocurrency,
                                  @RequestParam("finalcurrency") String fcurrency) throws IOException {
        return exchangeService.getExchangeValue(ocurrency, fcurrency);
    }
}
