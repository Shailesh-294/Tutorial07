package com.example.tutorial07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText username,passw,fname,lname;
    Button register;
    TextView login;
    Database Db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        Db = new Database(this);
        username = findViewById(R.id.username);
        passw = findViewById(R.id.pass);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        register = findViewById(R.id.submit);
        login = findViewById(R.id.returnlogin);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username,Password,Fname,Lname;
                Username = username.getText().toString();
                Password = passw.getText().toString();
                Fname = fname.getText().toString();
                Lname = lname.getText().toString();

                if(Username.equals("") || Password.equals("") || Fname.equals("") || Lname.equals("")){
                    Toast.makeText(Register.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean result = Db.verifyUser(Username);
                    if(result == false){
                        boolean ins = Db.insertData(Fname,Lname,Username,Password);
                        if(ins == true){
                            Toast.makeText(Register.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this,Login.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(Register.this, "Not Register", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

               /* boolean result = Db.insertData(Fname,Lname,Username,Password);
                if(result == true){
                    Toast.makeText(Register.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this,Login.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(Register.this, "Not Register", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}