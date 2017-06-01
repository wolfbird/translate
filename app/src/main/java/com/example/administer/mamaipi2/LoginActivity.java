package com.example.administer.mamaipi2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.toolbox.Loger;


public class LoginActivity extends AppCompatActivity {
    private TextView mTextView;
    private EditText login;
    private EditText password;
    private Button LoginButton;
    private Button RegistButton;
    private String Username;
    private String Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (EditText) findViewById(R.id.Username);
        password = (EditText) findViewById(R.id.Userpassowrd);
        LoginButton = (Button) findViewById(R.id.LoginButton);
        RegistButton = (Button) findViewById(R.id.RegistButton);


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username = String.valueOf(login.getText());
                Password = String.valueOf(password.getText());

                if (Username==null||"".equals(Username)){
                    Toast.makeText(LoginActivity.this,"输入用户名",Toast.LENGTH_SHORT).show();
                }
                else if (Password==null||"".equals(Password)){
                    Toast.makeText(LoginActivity.this,"输入密码",Toast.LENGTH_SHORT).show();
                }
                else {
                    HttpParams params = new HttpParams();
                    String url = "http://10.0.2.2:8080/Servlet/LoginServlet";
                    params.put("username",Username);
                    params.put("password",Password);
                    RxVolley.post(url, params, new HttpCallback() {
                        @Override
                        public void onSuccess(String t) {
                            String result = t;
                            Loger.debug("请求到的数据:" + t);
                            if (result.equals("true")){
                                Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(i);
                            }
                            else {
                                Toast.makeText(LoginActivity.this,"用户名或者密码错误",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


        RegistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegistActivity.class);
                startActivity(i);
            }
        });


    }
}
