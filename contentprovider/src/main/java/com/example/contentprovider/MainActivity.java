package com.example.contentprovider;

import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fetchContacts(View view)
    {
        if (view.getId()==R.id.button)
        {
            //initLoader() callback only creates a new loader if it does not exist
            // otherwise it will just return earlier created loader and in that case
            // the onLoadFinished method will get called directly
            //but restartLoader() callback creates a new loader if it does not exist
            // otherwise restarts the already existing loader by creating a new loader
            //again and performs the load work and destroys the old loader then.
            getLoaderManager().restartLoader(1,null,this);
        }

        /*ArrayList<String> list = new ArrayList<>();
        //Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Uri uri = Uri.parse("content://com.example.yogi.syncapp.syncprovider");
        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(uri,projection,selection,selectionArgs,sortOrder);
/
        while (cursor.moveToNext())
        {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int syncstatus = cursor.getInt(cursor.getColumnIndex("syncstatus"));
            String temp = "Name : "+name+"\nsyncstatus : "+syncstatus;
            list.add(temp);
            Log.d("YOGI",temp);

        }

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list));*/
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if(id==1)
        {
            return new CursorLoader(this,
                    Uri.parse("content://com.example.yogi.syncapp.syncprovider"),
                    null,
                    null,
                    null,
                    null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        ArrayList<String> list = new ArrayList<>();
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

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
