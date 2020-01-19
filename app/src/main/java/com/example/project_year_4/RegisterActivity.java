package com.example.project_year_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    EditText first,last,user,pass,birth;
    Button regis,can;
    TextView result;
    Spinner gen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        gen = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gen.setAdapter(myAdapter);



        first = (EditText) findViewById(R.id.firstname);
        last = (EditText) findViewById(R.id.lastname);
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        gen = (Spinner) findViewById(R.id.spinner1);
        birth = (EditText) findViewById(R.id.birthdate);

        regis = (Button) findViewById(R.id.btn_Register);
        can = (Button) findViewById(R.id.btn_Cancle);
        result = (TextView) findViewById(R.id.registerview);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = user.getText().toString();
                final String password = pass.getText().toString();
                final String firstname = first.getText().toString();
                final String lastname = last.getText().toString();
                final String birthdate = birth.getText().toString();
                final String gender = gen.getSelectedItem().toString();
                //result.setText("UserName:\t" + username + "\nPassword:\t" + password + "\nFirstName:\t" + firstname + "\nLastName:\t" + lastname + "\nBirthDate:\t" + birthdate + "\nGender:\t" + gender);

                AsyncTask asyncTask = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] objects) {
                        try {
                            URL url = new URL("http://10.0.2.2:5000/api/Users");
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("POST");
                            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                            conn.setRequestProperty("Accept", "application/json");
                            conn.setDoOutput(true);
                            conn.setDoInput(true);

                            JSONObject jsonParam = new JSONObject();
                            //jsonParam.put("id", 3);
                            jsonParam.put("Username", username);
                            jsonParam.put("Password", password);
                            jsonParam.put("Firstname",firstname);
                            jsonParam.put("Lastname",lastname);
                            jsonParam.put("Birthdate",birthdate);
                            jsonParam.put("Gender",gender);


                            Log.i("JSON", jsonParam.toString());
                            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                            //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                            os.writeBytes(jsonParam.toString());

                            os.flush();
                            os.close();

                            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                            Log.i("MSG", conn.getResponseMessage());

                            conn.disconnect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute();
            }
        });

        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
