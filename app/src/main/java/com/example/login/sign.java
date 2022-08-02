package com.example.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class sign extends AppCompatActivity {

    EditText txt_id, txt_pw, txt_email, txt_phone, txt_cardnum, txt_name, txt_nick;
    Button btn_join;

    RequestQueue requestQueue;
    StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        txt_id = findViewById(R.id.txt_id);
        txt_pw = findViewById(R.id.txt_pw);
        txt_email = findViewById(R.id.txt_email);
        txt_phone = findViewById(R.id.txt_phone);
        txt_cardnum = findViewById(R.id.txt_cardnum);
        txt_name = findViewById(R.id.txt_name);
        txt_nick = findViewById(R.id.txt_nick);
        btn_join = findViewById(R.id.btn_join);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        btn_join.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String id = txt_id.getText().toString();
                String pw = txt_pw.getText().toString();
                String email = txt_email.getText().toString();
                String phone = txt_phone.getText().toString();
                String cardnum = txt_cardnum.getText().toString();
                String name = txt_name.getText().toString();
                String nick = txt_nick.getText().toString();

                Log.d("성공","성공");

                request = new StringRequest(
                        Request.Method.POST,
                        "http://172.30.1.33:8083/web/Join.do",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response != null) {
                                    Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(), login.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Log.d("error",error.getMessage());

                            }
                        }
                ) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> params = new HashMap<>();

                        params.put("mb_id", id);
                        params.put("mb_nick", nick);
                        params.put("mb_email", email);
                        params.put("mb_phone", phone);
                        params.put("mb_cardnum", cardnum);
                        params.put("mb_name", name);
                        params.put("mb_pw", pw);

                        return params;
                    }
                };

                requestQueue.add(request);
                Log.d("요청", "요청완료");            }
        });

    }
}
