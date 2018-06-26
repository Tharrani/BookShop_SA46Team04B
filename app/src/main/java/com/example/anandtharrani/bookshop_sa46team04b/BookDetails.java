package com.example.anandtharrani.bookshop_sa46team04b;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class BookDetails extends Activity {

    ImageView image;
    String s;
    Book bk;
    String eid;
    CollapsingToolbarLayout CTBL;
    TextView Book_Title, Book_Author, Book_Price, Book_Stock, Book_Synopsis;
    ImageButton fv, ed;

    public View.OnClickListener favClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                    new AsyncTask<String, Void, Book>() {
                        @Override
                        protected Book doInBackground(String... params) {
                            return Book.getBook(eid);
                        }

                        @Override
                        protected void onPostExecute(Book result) {
                            Item.insert(result);
                            Toast.makeText(getApplicationContext(),"Book added to wishlist", Toast.LENGTH_SHORT).show();
                        }
                    }.execute(eid);
                }
            };

    public View.OnClickListener editClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(getApplicationContext(), update.class);
            i.putExtra("eid", eid);
            startActivity(i);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookdetail);
        setTitle(R.string.BookDetails);

        CTBL = (CollapsingToolbarLayout) findViewById(R.id.collapsing) ;
        CTBL.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        CTBL.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);
        Book_Title = (TextView) findViewById(R.id.TextViewTitle);
        Book_Author = (TextView) findViewById(R.id.TextViewAuthor);
        Book_Price = (TextView) findViewById(R.id.BookPrice);
        Book_Stock = (TextView) findViewById(R.id.TextViewStock);
        Book_Synopsis= (TextView) findViewById(R.id.TextViewSynopsis);

        fv = (ImageButton) findViewById(R.id.AddtoFavButton);
        ed = (ImageButton) findViewById(R.id.EditButton);

        Intent i = getIntent();
        eid = i.getStringExtra("eid");
        new AsyncTask<String, Void, Book>(){
            @Override
            protected Book doInBackground(String... params){
                return Book.getBook(eid);
            }
            @Override
            protected void onPostExecute(Book result){
                show(result);
            }

        }.execute(eid);

        ed.setOnClickListener(editClickListener);

        fv.setOnClickListener(favClickListener);
    }

    void show(Book bk) {

        CTBL.setTitle(bk.get("Title"));
        Book_Title.setText(bk.get("Title"));
        Book_Author.setText(bk.get("Author"));
        Book_Price.setText(bk.get("Price"));
        Book_Stock.setText(bk.get("Stock"));
        Book_Synopsis.setText(bk.get("Synopsis"));

        image = (ImageView) findViewById(R.id.img_book);
        s = bk.get("ISBN");
        new AsyncTask<String, Void, Bitmap>(){
            @Override
            protected Bitmap doInBackground(String... params){
                return Book.getPhoto(false,s);
            }
            @Override
            protected void onPostExecute(Bitmap result){
                image.setImageBitmap(result);
            }

        }.execute(s);

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
