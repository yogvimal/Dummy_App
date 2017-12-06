package com.example.yogi.dummy_app;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by YOGI on 20-11-2017.
 */

@Database(entities = {Quotes.class},version = 1)
public abstract class MyQuotesDB extends RoomDatabase {
    public abstract QuotesDAO quotesDAO();
}
