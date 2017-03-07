package com.foreign.client;

import com.foreign.client.model.LiveCurrency;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by bariscanakin on 7.3.2017.
 */
interface CurrencyLayerService {

    @GET("live")
    Call<LiveCurrency> getLive(@Query("access_key") String accessKey, @Query("source") String source, @Query("currencies") String currency);
}
