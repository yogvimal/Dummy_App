package com.example.yogi.dummy_app;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by YOGI on 20-11-2017.
 */
@Entity
public class Quotes {

    //primary keys must be annotated with @NonNull. SQLite considers this as a bug and Room does not allow it.

    @PrimaryKey
    @NonNull
    private String quote;


    private String author;


    private String category;

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
