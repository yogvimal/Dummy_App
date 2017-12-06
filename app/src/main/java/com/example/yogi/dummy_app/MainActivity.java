package com.example.yogi.dummy_app;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
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

public class MainActivity extends AppCompatActivity {

    QuoteAPI quoteAPI;
    private String category = "famous";
    private int count =2;
    //private ExecutorService executorService;
    //private List<Quotes> quotesList;
    //private LiveData<List<Quotes>> newlist;
    private RecyclerView recyclerView;
    private recyclerAdapter recyclerAdapter1;
    //private QuotesDAO quotesDAO;
    private Dummy_ViewModel dummy_viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //executorService = Executors.newSingleThreadExecutor();
        //quotesList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerAdapter1 = new recyclerAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter1);
        recyclerView.setHasFixedSize(true);

        /*MyQuotesDB quotesDB = Room.databaseBuilder(this,MyQuotesDB.class,"myDb").build();
        quotesDAO = quotesDB.quotesDAO();*/
    }

    void refresh(View view)
    {
        if (view.getId()==R.id.button)
        {
            dummy_viewModel.refreshData();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //newlist = quotesDAO.getCachedQuotes();

        dummy_viewModel = ViewModelProviders.of(this).get(Dummy_ViewModel.class);
        dummy_viewModel.init(category,count);

        dummy_viewModel.getQuotes().observe(this, new Observer<List<Quotes>>() {
            @Override
            public void onChanged(@Nullable List<Quotes> quotesList) {

                if(quotesList.size()==0)
                {
                    return;
                }
                Log.d("YOGI",quotesList.size()+"");
                recyclerAdapter1.insertData(quotesList);
                recyclerView.scrollToPosition(0);
            }
        });
    }
}
