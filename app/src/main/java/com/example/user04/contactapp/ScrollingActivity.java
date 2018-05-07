package com.example.user04.contactapp;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ScrollingActivity extends AppCompatActivity {

    private Bundle bundle;
//    private TextView desc;
    private TextView status;
//    private Toolbar toolbar;

    private CollapsingToolbarLayout toolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

        status = (TextView) findViewById(R.id.textview_desc);
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        bundle = getIntent().getExtras();

        setTitle(bundle.getString("name"));
        status.setText(bundle.getString("number")+(bundle.getString("company"))+(bundle.getString("email")));

//        status.setText(bundle.getString("email"));
//        status.setText(bundle.getString("company"));
        // toolbarLayout.setBackgroundResource(bundle.getInt("img"));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
