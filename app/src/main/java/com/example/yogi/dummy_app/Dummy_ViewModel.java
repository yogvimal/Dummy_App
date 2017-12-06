package com.example.yogi.dummy_app;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.provider.MediaStore;

import java.util.List;

/**
 * Created by YOGI on 23-11-2017.
 */

public class Dummy_ViewModel extends ViewModel {

    private LiveData<List<Quotes>> quotesList;
    private String category;
    private int count;
    private Repository repository;

    public void init(String category,int count)
    {
        if(quotesList!=null)
        {
            return;
        }
        this.category = category;
        this.count = count;
        repository = new Repository();
        quotesList = repository.getQuoteOnline(category,count);
    }

    public LiveData<List<Quotes>> getQuotes()
    {
        return quotesList;
    }

    public void refreshData()
    {
        quotesList = repository.getQuoteOnline(category,count);
    }



}
