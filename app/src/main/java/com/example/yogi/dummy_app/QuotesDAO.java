package com.example.yogi.dummy_app;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by YOGI on 20-11-2017.
 */
@Dao
public interface QuotesDAO {
    @Insert(onConflict = REPLACE)
    void cacheOnDB(List<Quotes> quotes);

    @Query("SELECT * FROM Quotes")
    LiveData<List<Quotes>> getCachedQuotes();
}
