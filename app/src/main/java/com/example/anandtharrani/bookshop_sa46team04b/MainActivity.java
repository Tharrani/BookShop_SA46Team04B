package com.example.anandtharrani.bookshop_sa46team04b;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardView C1 = (CardView) findViewById(R.id.CardView1);
        CardView C2 = (CardView) findViewById(R.id.CardView2);
        CardView C3 = (CardView) findViewById(R.id.CardView3);
        CardView C4 = (CardView) findViewById(R.id.CardView4);

        ImageView i1 = (ImageView) findViewById(R.id.browse);
        C1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AllBooks.class));
            }
        });
        ImageView i2 = (ImageView) findViewById(R.id.search);
        C2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SearchPage.class));
            }
        });
        ImageView i3 = (ImageView) findViewById(R.id.fav);
        C3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), favourite.class));
            }
        });
        ImageView i4 = (ImageView) findViewById(R.id.update);
        C4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Team.class));
            }
        });
    }

}
