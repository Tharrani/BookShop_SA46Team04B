package com.example.anandtharrani.bookshop_sa46team04b;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class favourite extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.Favourites);


        new AsyncTask<Void,Void,List<Book>>(){
            @Override
            protected List<Book> doInBackground(Void... params){
                return Item.getItemBook();
            }

            @Override
            protected void onPostExecute(List<Book> result){
                setListAdapter(new SimpleAdapter(getApplicationContext(), result,
                        android.R.layout.simple_list_item_2, new String[]{"Title", "Author"}, new int[]{android.R.id.text1, android.R.id.text2}));
            }
        }.execute();
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
