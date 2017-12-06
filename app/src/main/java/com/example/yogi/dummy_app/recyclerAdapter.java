package com.example.yogi.dummy_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YOGI on 20-11-2017.
 */

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.ViewHolder> {

    List<Quotes> quotesList;
    Context context;
    LayoutInflater inflater;
    public recyclerAdapter(Context context)
    {
        quotesList = new ArrayList<>();
        this.context = context;
        this.quotesList = quotesList;
        inflater = LayoutInflater.from(context);
        Log.d("YOGI","in constructor");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Log.d("YOGI","oncreateViewHolder");
        View view = inflater.inflate(R.layout.singleview,parent,false);
        return new ViewHolder(view);
    }

    public void insertData(List<Quotes> quotesList){

        this.quotesList = quotesList;
        /*for (int i=quotesList.size()-1;i>=0;i--)
        {
            this.quotesList.add(0,quotesList.get(i));
        }*/
        notifyDataSetChanged();
        //notifyItemRangeInserted(0,quotesList.size()-1);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.quote.setText(quotesList.get(position).getQuote());
        holder.author.setText(quotesList.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return quotesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView quote,author;
        public ViewHolder(View itemView) {
            super(itemView);
            quote = itemView.findViewById(R.id.textQuote);
            author = itemView.findViewById(R.id.textAuthor);
        }
    }
}
