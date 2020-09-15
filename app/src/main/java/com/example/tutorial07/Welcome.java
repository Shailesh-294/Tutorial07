package com.example.tutorial07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    TextView welcome;
    SharedPreferences share;
    SharedPreferences.Editor editor;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getSupportActionBar().setTitle("Welcome");

        share = getSharedPreferences("Ldata",MODE_PRIVATE);
        editor = share.edit();
        builder = new AlertDialog.Builder(Welcome.this);
        String user = share.getString("username","");
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
            builder.setTitle("Logout")
                    .setCancelable(false)
                    .setMessage("Are you sure want to logout ?")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            editor.putBoolean("login",false);
                            editor.commit();
                            Intent intent = new Intent(Welcome.this,Login.class);
                            startActivity(intent);
                            finish();
                        }
                    }).setNegativeButton("Cancle",null);
            AlertDialog alert = builder.create();
            alert.show();
            }
        return true;
    }
}
