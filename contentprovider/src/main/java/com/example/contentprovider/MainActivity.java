package com.example.contentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchContacts();
    }

    private void fetchContacts()
    {
        ArrayList<String> list = new ArrayList<>();
        //Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Uri uri = Uri.parse("content://com.example.yogi.syncapp.syncprovider");
        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(uri,projection,selection,selectionArgs,sortOrder);

        while (cursor.moveToNext())
        {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int syncstatus = cursor.getInt(cursor.getColumnIndex("syncstatus"));
            String temp = "Name : "+name+"\nsyncstatus : "+syncstatus;
            list.add(temp);
            Log.d("YOGI",temp);

        }

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list));
    }
}
