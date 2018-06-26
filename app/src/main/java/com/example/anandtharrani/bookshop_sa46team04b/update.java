package com.example.anandtharrani.bookshop_sa46team04b;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class update extends Activity {

    String s;
    Book bk;
    String eid;
    TextView tBID, tCID, tISBN, tSWD, tFP;
    EditText Etitle, EAuthor, EPrice, EStock, ESynopsis;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        setTitle(R.string.Update);

        tBID = (TextView) findViewById(R.id.textViewBookID);
        tCID = (TextView) findViewById(R.id.textViewCatID);
        tISBN = (TextView) findViewById(R.id.textViewISBN);
        tSWD = (TextView) findViewById(R.id.textViewDiscount);
        tFP = (TextView) findViewById(R.id.textViewFinalPrice);

        Etitle = (EditText) findViewById(R.id.editText6);
        EAuthor = (EditText) findViewById(R.id.editText7);
        EPrice = (EditText) findViewById(R.id.editText10);
        EStock = (EditText) findViewById(R.id.editText8);
        ESynopsis = (EditText) findViewById(R.id.editText9);

        save = (Button) findViewById(R.id.Save);

        Intent i = getIntent();
        eid = i.getStringExtra("eid");
        new AsyncTask<String, Void, Book>() {
            @Override
            protected Book doInBackground(String... params) {
                return Book.getBook(eid);
            }

            @Override
            protected void onPostExecute(Book result) {
                show(result);
            }

        }.execute(eid);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book c = new Book();
                c.put("BookID", tBID.getText().toString());
                c.put("Title", Etitle.getText().toString());
                c.put("CategoryID", tCID.getText().toString());
                c.put("ISBN", tISBN.getText().toString());
                c.put("Author", EAuthor.getText().toString());
                c.put("Stock", EStock.getText().toString());
                c.put("Price", EPrice.getText().toString());
                c.put("Synopsis", ESynopsis.getText().toString());
                c.put("SWDiscount", tSWD.getText().toString());
                c.put("FinalPrice", tFP.getText().toString());


                new AsyncTask<Book, Void, Void>() {
                    @Override
                    protected Void doInBackground(Book... params) {
                        Book.updateBook(params[0]);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        finish();
                        Toast.makeText(getApplicationContext(), "Details Updated", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), BookDetails.class);
                        i.putExtra("eid", eid);
                        startActivity(i);
                    }
                }.execute(c);
            }
        });
    }

        void show(Book bk) {
        tBID.setText(bk.get("BookID"));
        tCID.setText(bk.get("CategoryID"));
        tISBN.setText(bk.get("ISBN"));
        tSWD.setText(bk.get("SWDiscount"));
        tFP.setText(bk.get("FinalPrice"));
        Etitle.setText(bk.get("Title"));
        EAuthor.setText(bk.get("Author"));
        EStock.setText(bk.get("Stock"));
        EPrice.setText(bk.get("Price"));
        ESynopsis.setText(bk.get("Synopsis"));
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
