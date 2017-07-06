package com.example.user.leavemanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;
public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Button l=(Button)findViewById(R.id.btn);
        final EditText id = (EditText) findViewById(R.id.Emp_Id);
        final EditText psd = (EditText) findViewById(R.id.password);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String Emp_Id = id.getText().toString();
                final String password = psd.getText().toString();
                final String blank="";
                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");


                            if (success) {

                                String name = jsonResponse.getString("name");
                                final String phno = jsonResponse.getString("Ph_No");
                                int balance=jsonResponse.getInt("balance");
                                int visited=jsonResponse.getInt("visited");

                                if(visited==0 & balance==0 ) {
                                    Intent intent = new Intent(Login.this, SplashPage.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("name", name);
                                    bundle.putString("Ph_No", phno);
                                    intent.putExtra("bundled", bundle);

                                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                    id.setText("");
                                    psd.setText("");
                                    Intent intent = new Intent(LoginActivity.this, Splashscreen2.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("name", name);
                                    bundle.putString("phonenumber", phonenumber);
                                    bundle.putInt("balance",balance);
                                    intent.putExtra("bundledo", bundle);
                                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                    etUsername.setText("");
                                    etPassword.setText("");


                                }
                                if(visited==1 & balance<20)
                                {
                                    Intent intent = new Intent(LoginActivity.this, Splashscreen3.class);
                                    Bundle bundlle = new Bundle();
                                    bundlle.putString("name", name);
                                    bundlle.putString("phonenumber", phonenumber);
                                    bundlle.putInt("balance",balance);
                                    intent.putExtra("bund", bundlle);
                                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                    etUsername.setText("");
                                    etPassword.setText("");
                                    progressBar.setVisibility(View.INVISIBLE);
                                }




                            }
                            else {
                                progressBar.setVisibility(View.INVISIBLE);
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Username or Password is Invalid")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


                LoginRequest loginRequest = new LoginRequest(username,password,responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);

            }

        });








    }
}
catch (JSONException e) {
    e.printStackTrace();
}