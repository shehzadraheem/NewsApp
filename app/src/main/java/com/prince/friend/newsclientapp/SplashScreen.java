package com.prince.friend.newsclientapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        // splash screen wait for 2 sec and then launch login activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(user != null){
                    // if user is already login then it will go to home screen
                    // he do not need to login again
                    startActivity(new Intent(SplashScreen.this, Home.class));
                    finish();
                }else{
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    finish();
                }
            }
        },3000);
    }
}