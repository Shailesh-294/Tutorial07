package com.example.tutorial07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getSupportActionBar().setTitle("Welcome");

        String user = getIntent().getStringExtra("username");

        welcome = findViewById(R.id.welcome);
        welcome.setText("Welcome "+user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.logout){
            Intent intent = new Intent(Welcome.this,Login.class);
            startActivity(intent);
            finish();
        }
        return true;
    }
}