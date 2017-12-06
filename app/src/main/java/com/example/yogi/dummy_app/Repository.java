package com.example.yogi.dummy_app;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.util.Log;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by YOGI on 24-11-2017.
 */

public class Repository {
    QuoteAPI quoteAPI;
    QuotesDAO quotesDAO;
    ExecutorService executorService;


    Repository(){
        executorService = Executors.newSingleThreadExecutor();
        MyQuotesDB db = Room.databaseBuilder(DummyApplication.getContext(),MyQuotesDB.class,"myDb").build();
        quotesDAO = db.quotesDAO();
    }

    LiveData<List<Quotes>> getQuoteOnline(String category,int count)
    {
        fetchData(category,count);
        return quotesDAO.getCachedQuotes();
    }

    void fetchData(String category,int count)
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://andruxnet-random-famous-quotes.p.mashape.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        quoteAPI = retrofit.create(QuoteAPI.class);

        quoteAPI.getQuotes(category,count).enqueue(new Callback<List<Quotes>>() {
            @Override
            public void onResponse(Call<List<Quotes>> call, final Response<List<Quotes>> response) {


                Future<String> future = (Future<String>) executorService.submit(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        quotesDAO.cacheOnDB(response.body());
                            //newlist = quotesDAO.getCachedQuotes();
                            for (int i=0;i<response.body().size();i++)
                            {
                                Log.d("YOGI","NO/ : "+i+"\nQuote : "+response.body().get(i).getQuote());
                            }
                            /*for (int i=0;i<newlist.size();i++)
                            {
                                Log.d("YOGI","NO/ : "+i+"\nQuote : "+newlist.get(i).getQuote());
                            }*/

                        return "done";
                    }
                });


                    /*try {
                        if(future.get().equals("done")){
                            recyclerAdapter1.insertData(newlist,count);
                            recyclerView.scrollToPosition(0);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }*/


            }

            @Override
            public void onFailure(Call<List<Quotes>> call, Throwable t) {

            }
        });

    }
}
