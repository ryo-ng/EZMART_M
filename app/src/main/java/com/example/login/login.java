package com.example.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    EditText txt_id2, txt_pw2;
    Button btn_login;

    RequestQueue requestQueue;
    StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt_id2 = findViewById(R.id.txt_id2);
        txt_pw2 = findViewById(R.id.txt_pw2);
        btn_login = findViewById(R.id.btn_login);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = txt_id2.getText().toString();
                String pw = txt_pw2.getText().toString();


                request = new StringRequest(
                        Request.Method.POST,
                        "http://172.30.1.33:8083/web/Login.do",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response != null){
                                    Toast.makeText(getApplicationContext(),"로그인 성공", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), main.class);
                                    startActivity(intent);
                                    finish();

                                }else{
                                    Toast.makeText(getApplicationContext(),"로그인 실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                ){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params = new HashMap<>();

                        params.put("mb_id", id);
                        params.put("mb_pw", pw);

                        return params;
                    }
                };

                requestQueue.add(request);
            }
        });

    }
}

