package com.example.anandtharrani.bookshop_sa46team04b;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.PrecomputedText;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.List;

public class AllBooks extends ListActivity {

    List<Book> books;
    SwipeMenuListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.AllBooks);
        listView = (SwipeMenuListView) findViewById(R.id.listView);

        new AsyncTask<Void,Void,List<Book>>(){
            @Override
            protected List<Book> doInBackground(Void... params){
                return Book.list();
            }
            @Override
            protected void onPostExecute(List<Book> result){
                setListAdapter(new SimpleAdapter(getApplicationContext(), result,
                        android.R.layout.simple_list_item_2, new String[]{"Title", "Author"}, new int[]{android.R.id.text1, android.R.id.text2}));
            }
        }.execute();
    }

    @Override
    protected void onListItemClick(ListView l, View v,
                                   int position, long id) {
        Book item = (Book) getListAdapter().getItem(position);
        Intent intent = new Intent(this, BookDetails.class);
        intent.putExtra("eid", item.get("BookID"));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                startActivity(new Intent(this, SearchPage.class));
                return true;
            case R.id.item2:
                startActivity(new Intent(this, AllBooks.class));
                return true;
            case R.id.item3:
                startActivity(new Intent(this, favourite.class));
                return true;
            case R.id.item5:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}