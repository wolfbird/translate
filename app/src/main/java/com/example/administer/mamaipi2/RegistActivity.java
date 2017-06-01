package com.example.administer.mamaipi2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;

public class RegistActivity extends AppCompatActivity {
    private EditText Username;
    private EditText Password;
    private Button RegistButton;
    private String UsernameText;
    private String PasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        Username = (EditText)findViewById(R.id.RegistUsername);
        Password = (EditText)findViewById(R.id.RegistUserpassowrd);
        RegistButton = (Button) findViewById(R.id.RegistButton2);


        RegistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsernameText = String.valueOf(Username.getText());
                PasswordText = String.valueOf(Password.getText());

                if (Username==null||"".equals(Username)){
                    Toast.makeText(RegistActivity.this,"输入用户名",Toast.LENGTH_SHORT).show();
                }
                else if (Password==null||"".equals(Password)){
                    Toast.makeText(RegistActivity.this,"输入密码",Toast.LENGTH_SHORT).show();
                }
                else {
                    String url = "http://10.0.2.2:8080/Servlet/SignUpRegisterServlet";
                    HttpParams params = new HttpParams();
                    params.put("username",UsernameText);
                    params.put("password",PasswordText);
                    RxVolley.post(url, params, new HttpCallback() {
                        @Override
                        public void onSuccess(String t) {
                            String result = t;
                            if (result.equals("true")){
                                Toast.makeText(RegistActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegistActivity.this,LoginActivity.class);
                                startActivity(i);

                            }
                            else {
                                Toast.makeText(RegistActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });



    }
}
