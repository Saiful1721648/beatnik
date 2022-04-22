package com.example.beatnik;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler=new Handler();
        handler.postDelayed(() -> {
                    startActivity(new Intent(MainActivity.this,logIn.class));
                    finish();

                },3000

        );
        Toast.makeText(this,"Welcome to Beatnik Technology",Toast.LENGTH_SHORT).show();
    }
}