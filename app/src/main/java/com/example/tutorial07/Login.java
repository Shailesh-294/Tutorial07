package com.example.tutorial07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username,pass;
    Button login,register;
    Database Db;
    boolean IsLogin;
    SharedPreferences share;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        share = getSharedPreferences("Ldata",MODE_PRIVATE);
        editor =share.edit();

        Db = new Database(this);
        username = findViewById(R.id.fname);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        IsLogin = share.getBoolean("login",false);
        if(IsLogin){
            Intent intent = new Intent(Login.this,Welcome.class);
            startActivity(intent);
            finish();
        }
        else {
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String Username, password;
                    Username = username.getText().toString();
                    password = pass.getText().toString();

                    boolean res = Db.verifylogin(Username, password);
                    if ( Patterns.EMAIL_ADDRESS.matcher(Username).matches() && res) {
                        saveInShared();
                        Intent intent = new Intent(Login.this, Welcome.class);
                        startActivity(intent);
                        Toast.makeText(Login.this, "Login successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(Login.this, "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void saveInShared(){
        editor.putString("username",username.getText().toString());
        editor.putString("password",pass.getText().toString());
        editor.putBoolean("login",true);
        editor.commit();
    }
}