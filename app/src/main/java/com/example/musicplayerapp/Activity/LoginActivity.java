package com.example.musicplayerapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.method.PasswordTransformationMethod;


import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.musicplayerapp.R;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText login_username, login_password;
    private Button login_button;
    private TextView signupRedirectText;
    public static String user = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_username = findViewById(R.id.login_username);
        login_password = findViewById(R.id.login_password);
        login_password.setTransformationMethod(new PasswordTransformationMethod());
        signupRedirectText = findViewById(R.id.signupRedirectText);
        login_button = findViewById(R.id.login_button);

        // Thêm sự kiện click vào TextView signupRedirectText
        signupRedirectText.setOnClickListener(v -> {
            // Chuyển sang trang SignUpActivity
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        });


        // Thêm sự kiện click vào Button login_button
        login_button.setOnClickListener(v -> {
            // Xử lý đăng nhập
            String username = login_username.getText().toString();
            String password = login_password.getText().toString();

            // Gọi phương thức kiểm tra đăng nhập
            if (performLogin(username, password)) {
                // Đăng nhập thành công
                // Đóng Activity hiện tại (LoginActivity)
                finish();
            }

        });
    }

    private boolean performLogin(String username, String password) {
        String url = "https://highfive-app.000webhostapp.com/Server/login.php";

        final boolean isLoginSuccessful = false;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    if (response.equals("success")) {
                        boolean isLoginSucessful = true;
                        // Đăng nhập thành công
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        user = login_username.getText().toString().trim();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);


                    } else {
                        // Đăng nhập thất bại
                        Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(LoginActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                // Đưa giá trị vào Map
                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);

        return isLoginSuccessful;
    }

}