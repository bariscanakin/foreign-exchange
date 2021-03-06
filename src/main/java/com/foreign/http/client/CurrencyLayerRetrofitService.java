package com.foreign.http.client;

import com.foreign.http.client.model.LiveCurrency;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by bariscanakin on 7.3.2017.
 */
@Component
public class CurrencyLayerRetrofitService implements CurrencyServiceStrategy {

    @Value("${currencyLayer.url.base}")
    private String baseUrl;

    @Value("${currencyLayer.api.key}")
    private String apiKey;

    private CurrencyLayerRetrofitClient currencyLayerRetrofitClient;

    @PostConstruct
    private void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        currencyLayerRetrofitClient = retrofit.create(CurrencyLayerRetrofitClient.class);
    }

    @Override
    public BigDecimal getLiveCurrency(String currencyFrom, String currencyTo) throws Exception {
        Call<LiveCurrency> liveCurrencyCall = currencyLayerRetrofitClient.getLive(apiKey, currencyFrom, currencyTo);

        Response<LiveCurrency> liveCurrencyResponse = liveCurrencyCall.execute();

        if (liveCurrencyResponse.isSuccessful()) {
            LiveCurrency liveCurrency = liveCurrencyResponse.body();

            Map<String, BigDecimal> quotes = liveCurrency.getQuotes();
            return quotes.get(currencyFrom + currencyTo);
        } else {
            ResponseBody responseBody = liveCurrencyResponse.errorBody();
            throw new RetrofitCallException(liveCurrencyResponse.code(), responseBody.string());
        }
    }
}
