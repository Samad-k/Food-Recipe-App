package com.example.foodrecipeapp;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class RegistrationActivity extends AppCompatActivity {

    EditText username,email,password;
    Button Btn_Register;
    String url = "https://foodchoiceappapi.000webhostapp.com/Register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        username = findViewById(R.id.usernameEdit);
        email = findViewById(R.id.emailEdit);
        password = findViewById(R.id.passwordEdit);
        Btn_Register = findViewById(R.id.registerBtn);

        Btn_Register.setOnClickListener(new View.OnClickListener() {
            String myusername = username.getText().toString();
            String myemail = email.getText().toString();
            String mypassword = password.getText().toString();

            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
                progressDialog.setMessage("Please Wait...");
                progressDialog.show();

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();

                        username.setText("");
                        email.setText("");
                        password.setText("");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String > params = new HashMap<String,String>();

                        params.put("user_name", myusername);
                        params.put("user_email",myemail);
                        params.put("user_password",mypassword);

                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(RegistrationActivity.this);
                requestQueue.add(request);
            }
        });
    }
}