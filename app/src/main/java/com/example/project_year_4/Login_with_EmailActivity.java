package com.example.project_year_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login_with_EmailActivity extends AppCompatActivity {


    private EditText mPswEditText;
    private TextView mToggleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with__email);

        Button Login_btn = (Button) findViewById(R.id.login_btn);
        Button Forgot_btn = (Button) findViewById(R.id.Forgot_btn);
        Button Regis_btn = (Button) findViewById(R.id.regis_btn);

        mPswEditText = (EditText) findViewById(R.id.pass_txt);
        mToggleTextView = (TextView) findViewById(R.id.toggleTxt);
        mPswEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        mPswEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(mPswEditText.getText().length()>0){
                    mToggleTextView.setVisibility(View.VISIBLE);
                }
                else {
                    mToggleTextView.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mToggleTextView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(mToggleTextView.getText()=="SHOW") {
                    mToggleTextView.setText("HIDE");
                    mPswEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mPswEditText.setSelection(mPswEditText.length());
                }
                else{
                    mToggleTextView.setText("SHOW");
                    mPswEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mPswEditText.setSelection(mPswEditText.length());
                }
            }
        });



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
