package com.example.project_year_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn = (Button)findViewById(R.id.button4);
        Button btn2 = (Button)findViewById(R.id.button5);
        btn.setOnClickListener(new View.OnClickListener(){
            public  void  onClick(View v){
                startActivity(new Intent(LoginActivity.this,Login_with_EmailActivity.class));
            }
        });


        btn2.setOnClickListener(new View.OnClickListener(){
            public  void  onClick(View v){
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });


    }

}
