package com.rtw181204.ex88firebasechatting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends BaseActivity {

    EditText etId, etPw;

    FirebaseAuth firebaseAuth;

    String userId, userPw;

    CheckBox cbAutoLogin;

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


        cbAutoLogin = findViewById(R.id.cb_autologin);


        if(firebaseAuth.getCurrentUser()!=null&&cbAutoLogin.isChecked()){


            finish();

            startActivity(new Intent(this, MainActivity.class));

        }





    }

    public void clickLogin(View view) {



        userId = etId.getText().toString();
        userPw = etPw.getText().toString();

        if (TextUtils.isEmpty(userId)) {
            Toast.makeText(getApplication(), "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(userPw)) {
            Toast.makeText(getApplication(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }



        firebaseAuth.signInWithEmailAndPassword(userId, userPw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {




                            finish();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));

                        } else {
                            Toast.makeText(getApplicationContext(), "로그인 실패!"+ " 로그인 실패 유형\n - password가 맞지 않습니다.\n -서버에러", Toast.LENGTH_LONG).show();

                        }
                    }
                });

    }

    public void clickSignUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }




}
