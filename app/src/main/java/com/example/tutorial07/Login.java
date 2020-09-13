package com.example.tutorial07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username,pass;
    Button login,register;
    Database Db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        Db = new Database(this);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username,password;
                Username = username.getText().toString();
                password = pass.getText().toString();

                boolean res = Db.verifylogin(Username,password);
                if(res == true){
                    Intent intent = new Intent(Login.this,Welcome.class);
                    startActivity(intent);
                    Toast.makeText(Login.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(Login.this, "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                }
                /* Cursor result = Db.getData();
                if(result.getString(4).equals(Username) && result.getString(5).equals(password)){
                    Toast.makeText(Login.this, "Login successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Login.this, "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                    return;
                }*/
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
                finish();
            }
        });
    }
}