package com.coralogix.calculator.services;

import com.coralogix.calculator.ApiConfiguration;
import com.coralogix.calculator.model.Currency;
import com.coralogix.calculator.model.Exchange;
import com.coralogix.calculator.repository.ExchangeRepository;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeService implements ApiConfiguration {


    private CurrencyService currencyService;
    @Autowired
    private ExchangeRepository exchangeRepository;

    public ExchangeService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        currencyService = retrofit.create(CurrencyService.class);
    }

    public List<Exchange> getAllExchange(){
        return exchangeRepository.findAll();
    }

    public String getExchangeValue(String oc, String fc) throws IOException {

        Exchange e = exchangeRepository.getExchangeCValueByOriginCurrencyAndFinalCurrency(oc, fc);
        if(e==null){
            Call<Currency> retrofitCall = currencyService.getExchange(oc);

            Response<Currency> response = retrofitCall.execute();

            if (!response.isSuccessful()) {
                throw new IOException(response.errorBody() != null
                        ? response.errorBody().string() : "Unknown error");
            }
            if(response.body()!=null) {
                Map<String, String> currencies = response.body().getConversion_rates();
                for (Map.Entry<String, String> c : currencies.entrySet()) {
                    if (c.getKey().equals(fc)) {
                        Exchange ne = new Exchange();
                        ne.setOriginCurrency(oc);
                        ne.setFinalCurrency(fc);
                        ne.setDate(LocalDate.now().toString());
                        ne.setValue(c.getValue());
                        exchangeRepository.save(ne);
                        return ne.getValue();
                    }
                }
            }
        }
        assert e != null;
        return e.getValue();
    }

    public void saveOrUpdate(Exchange e) {
        exchangeRepository.save(e);
    }

}
