package com.example.anandtharrani.bookshop_sa46team04b;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.List;

public class SearchResult extends ListActivity {


    List<Book> books;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.SearchResult);

        getIntent();
        Bundle extras = getIntent().getExtras();
        s = extras.getString("title");

        new AsyncTask<String,Void,List<Book>>(){
            @Override
            protected List<Book> doInBackground(String... params){
                return Book.getbyTitles(s);
            }
            @Override
            protected void onPostExecute(List<Book> result){

                if(result.size()>0) {
                    setListAdapter(new SimpleAdapter(getApplicationContext(), result,
                            android.R.layout.simple_list_item_2, new String[]{"Title", "Author"}, new int[]{android.R.id.text1, android.R.id.text2}));
                }

                else
                {
                    Toast.makeText(getApplicationContext(), "No Books matching Title", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),SearchPage.class));
                }
            }
        }.execute(s);
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
