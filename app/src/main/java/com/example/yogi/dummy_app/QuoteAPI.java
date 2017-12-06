package com.example.yogi.dummy_app;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by YOGI on 20-11-2017.
 */

public interface QuoteAPI {

    //Please Put your own key here
    @Headers({
            "X-Mashape-Key: fc2GsH3QNsmshA4VCnG7kELPOv7up1vWnS1jsnuy6ATpTXjaTC",
            "Accept: application/json",
            "Content-Type: application/x-www-form-urlencoded"
    })

    @GET("/")
    Call<List<Quotes>> getQuotes(@Query("cat") String category, @Query("count") int count);

}
