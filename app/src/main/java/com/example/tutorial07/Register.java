package com.example.tutorial07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText pass,username,fname,lname;
    Button register;
    TextView login;
    Database Db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        Db = new Database(this);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        register = findViewById(R.id.submit);
        login = findViewById(R.id.returnlogin);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Password,Username,Fname,Lname;
                Password = pass.getText().toString();
                Username = username.getText().toString();
                Fname = fname.getText().toString();
                Lname = lname.getText().toString();

                if( !Patterns.EMAIL_ADDRESS.matcher(Username).matches() || Username.equals(" ") || Password.equals(" ") ){
                    Toast.makeText(Register.this, "Please Enter Valid Details", Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean result = Db.verifyUser(Username);
                    if(!result){
                        boolean ins = Db.insertData(Username,Password,Fname,Lname);
                        if(ins){
                            Toast.makeText(Register.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this,Login.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(Register.this, "Not Register", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        username.setText("");
                        pass.setText("");
                        fname.setText("");
                        lname.setText("");
                        fname.requestFocus();
                        Toast.makeText(Register.this, "Already Register", Toast.LENGTH_SHORT).show();
                    }
                }

               /* boolean result = Dbm.insertData(userid,password);
                if(result){
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