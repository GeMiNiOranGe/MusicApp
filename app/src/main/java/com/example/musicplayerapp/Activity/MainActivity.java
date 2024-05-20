 package com.example.musicplayerapp.Activity;

 import android.content.Intent;
 import android.os.Bundle;

 import androidx.appcompat.app.AppCompatActivity;

 public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Mở trang đăng nhập (LoginActivity)
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);

         //Đóng Activity hiện tại (MainActivity)
        finish();
    }
}