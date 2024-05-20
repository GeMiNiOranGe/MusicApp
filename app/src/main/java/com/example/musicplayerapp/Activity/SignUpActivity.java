package com.example.musicplayerapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.musicplayerapp.R;

import java.util.HashMap;
import java.util.Map;
public class SignUpActivity extends AppCompatActivity {

    private EditText signup_fullname;
    private EditText signup_phonenumber;
    private EditText signup_username;
    private EditText signup_password;
    private Button signup_button;
    private TextView loginRedirectText;


    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup_fullname = findViewById(R.id.signup_fullname);
        signup_phonenumber = findViewById(R.id.signup_phonenumber);
        signup_username = findViewById(R.id.signup_username);
        signup_password = findViewById(R.id.signup_password);
        signup_password.setTransformationMethod(new PasswordTransformationMethod());
        signup_button = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        signup_button.setOnClickListener(v -> {
                String fullname = signup_fullname.getText().toString();
                String phone = signup_phonenumber.getText().toString();
                String username = signup_username.getText().toString();
                String password = signup_password.getText().toString();

                //Đăng ký tài khoản
                performSignUp(fullname, phone, username, password);
        });
        loginRedirectText.setOnClickListener(v -> {
            // Chuyển đến màn hình đăng nhập
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });


    }

    private void performSignUp(String fullname, String phone, String username, String password) {
        // Kiểm tra các trường có đúng định dạng hay không

        boolean isValid = validateFields();

        if (isValid) {
            // Gửi thông tin đăng ký lên server và xử lý logic đăng ký

            // Tạo request đăng ký
            String url = "https://highfive-app.000webhostapp.com/Server/signup.php";
            StringRequest request = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Xử lý phản hồi từ server sau khi đăng ký
                            if (response.equals("Success")) {
                                // Đăng ký thành công
                                Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // Đăng ký thất bại
                                Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }
                    },
                    error -> {
                        // Xử lý lỗi khi kết nối đến server
                        Toast.makeText(SignUpActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    // Đưa các thông tin đăng ký vào trong parameters của request
                    Map<String, String> params = new HashMap<>();
                    params.put("name", fullname);
                    params.put("sdt", phone);
                    params.put("username", username);
                    params.put("password", password);
                    return params;
                }
            };

            // Thực hiện request đăng ký bằng Volley
            Volley.newRequestQueue(this).add(request);
        } else {
            // Hiển thị thông báo lỗi khi các trường không hợp lệ
            Toast.makeText(this, "Thông tin đăng ký không hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean validateFields() {
        // Kiểm tra và xác thực dữ liệu nhập vào
        String fullname = signup_fullname.getText().toString().trim();
        String phone = signup_phonenumber.getText().toString().trim();
        String username = signup_username.getText().toString().trim();
        String password = signup_password.getText().toString().trim();

        // Kiểm tra các trường bắt buộc không được để trống
        if (fullname.isEmpty()) {
            signup_fullname.setError("Vui lòng nhập họ tên");
            return false;
        }

        if (phone.isEmpty()) {
            signup_phonenumber.setError("Vui lòng nhập số điện thoại");
            return false;
        }

        if (username.isEmpty()) {
            signup_username.setError("Vui lòng nhập tên đăng nhập");
            return false;
        }

        if (password.isEmpty()) {
            signup_password.setError("Vui lòng nhập mật khẩu");
            return false;
        }

        // Kiểm tra các điều kiện khác cho các trường dữ liệu (nếu cần)

        return true;
    }


}

