package com.rtw181204.ex88firebasechatting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText etId, etPw;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar= findViewById(R.id.toolbar);
        //Toolbar를 액션바로 대체하도록 설정하기!
        setSupportActionBar(toolbar);

        etId = findViewById(R.id.et_id);
        etPw = findViewById(R.id.et_pw);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){


            finish();

            startActivity(new Intent(this, MainActivity.class));
        }
    }

    public void clickLogin(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void clickSignUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }
}
