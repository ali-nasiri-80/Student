package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        StringRequest request=new StringRequest(Request.Method.GET, "http://expertdevelopers.ir/api/v1/experts/student", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {//get response
                Log.i(TAG, "onResponse: ");

                List<Student>students=new ArrayList<>();
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i = 0; i < jsonArray.length() ; i++) {
                        JSONObject studentJsonObject=jsonArray.getJSONObject(i);
                        Student student=new Student();
                        student.setId(studentJsonObject.getInt("id"));
                        student.setFirstName(studentJsonObject.getString("first_name"));
                        student.setLastName(studentJsonObject.getString("last_name"));
                        student.setScore(studentJsonObject.getInt("score"));
                        student.setCourse(studentJsonObject.getString("course"));
                        students.add(student);
                    }
                    Log.i(TAG, "onResponse: "+students.size());

                    RecyclerView recyclerView=findViewById(R.id.rv_main_students);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false));
                    recyclerView.setAdapter(new StudentAdapter(students));

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, "onErrorResponse: "+volleyError );
            }
        });

        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

}
