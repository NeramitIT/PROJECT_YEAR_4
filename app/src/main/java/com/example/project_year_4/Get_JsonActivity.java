package com.example.project_year_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Get_JsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get__json);

        Button button = findViewById(R.id.button2);
        Button btnSend = findViewById(R.id.btn_send);
        final TextView textView = findViewById(R.id.textView);

        btnSend.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          AsyncTask asyncTask = new AsyncTask() {
                                              @Override
                                              protected Object doInBackground(Object[] objects) {
                                                  try {
                                                      URL url = new URL("http://10.0.2.2:12345/api/TodoItems/3");
                                                      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                                      conn.setRequestMethod("POST");
                                                      conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                                                      conn.setRequestProperty("Accept","application/json");
                                                      conn.setDoOutput(true);
                                                      conn.setDoInput(true);

                                                      JSONObject jsonParam = new JSONObject();
                                                      //jsonParam.put("id", 3);
                                                      jsonParam.put("name", "yoshi8910");
                                                      jsonParam.put("isComplete", true);

                                                      Log.i("JSON", jsonParam.toString());
                                                      DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                                                      //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                                                      os.writeBytes(jsonParam.toString());

                                                      os.flush();
                                                      os.close();

                                                      Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                                                      Log.i("MSG" , conn.getResponseMessage());

                                                      conn.disconnect();
                                                  } catch (Exception e) {
                                                      e.printStackTrace();
                                                  }
                                                  return null;
                                              }
                                          }.execute();
                                      }
                                  });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask asyncTask = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] objects) {

                        OkHttpClient client = new OkHttpClient();

                        Request request = new Request.Builder()
                                .url("http://10.0.2.2:5000/api/Users")
                                .build();

                        Response response = null;

                        try {

                            response = client.newCall(request).execute();
                            //ArrayList<Video> videos = new ArrayList<Video>();
                            String a = response.body().string();
                            StringBuffer bf = new StringBuffer();
                            try {
                                //JSONObject json = new JSONObject(a);
                                JSONArray items = new JSONArray(a);
                                //JSONArray items = json.getJSONArray("summary");
                                //JSONArray items = dataObject.getJSONArray("summary");
                                bf.append("*********Data");
                                for (int i = 0; i < items.length(); i++) {
                                    JSONObject todoItems = items.getJSONObject(i);

                                    bf.append(todoItems.getString("name"));
                                    bf.append("\n");
//                                    Video video = new Video(videoObject.getString("title"),
//                                            videoObject.getString("description"),
//                                            videoObject.getJSONObject("player")
//                                                    .getString("default"),
//                                            videoObject.getJSONObject("thumbnail")
//                                                    .getString("sqDefault"));
//                                    videos.add(video);
                                }

                            } catch (JSONException e) {
                                // manage exceptions
                                e.printStackTrace();
                            }
                            //return response.body().string();

                            return bf.toString();

                        }catch (IOException e){
                            e.printStackTrace();
                        }


                        return null;
                    }

                    @Override
                    protected void onPostExecute(Object o) {
                        if (o != null){
                            textView.setText(o.toString());
                        }
                        else {
                            textView.setText("No Data");
                        }

                    }
                }.execute();
            }
        });
    }
}
