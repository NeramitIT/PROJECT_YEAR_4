package com.example.project_year_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login_with_EmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with__email);

        Button Login_btn = (Button)findViewById(R.id.login_btn);
        Button Forgot_btn = (Button)findViewById(R.id.Forgot_btn);
        Button Regis_btn = (Button)findViewById(R.id.regis_btn);




        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Forgot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_with_EmailActivity.this,Forgot_PasswordActivity.class));
            }
        });


        Regis_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_with_EmailActivity.this,RegisterActivity.class));
            }
        });





    }
}
