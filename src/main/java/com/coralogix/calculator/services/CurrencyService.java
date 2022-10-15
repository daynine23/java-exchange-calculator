package com.coralogix.calculator.services;

import com.coralogix.calculator.model.Currency;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CurrencyService {


    @GET("{oc}")
    Call<Currency> getExchange(
            @Path("oc") String oc);
}
