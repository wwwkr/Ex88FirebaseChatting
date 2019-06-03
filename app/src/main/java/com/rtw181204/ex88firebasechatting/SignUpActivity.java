package com.rtw181204.ex88firebasechatting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class SignUpActivity extends BaseActivity {

    FirebaseAuth firebaseAuth;

    EditText etId, etPw, etName, etNick;

    ProgressDialog progressDialog;

    Uri imgUri;

    ImageView ivProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ((TextView) findViewById(R.id.tv_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle("");

        etId = findViewById(R.id.et_id);
        etPw = findViewById(R.id.et_pw);
        etName = findViewById(R.id.et_name);
        etNick = findViewById(R.id.et_nick);
        ivProfile = findViewById(R.id.iv_profile);



        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);




    }


//    닉네임 중복검사
    public void btnNick(View view) {

        Toast.makeText(this, "사용 가능한 닉네임입니다.", Toast.LENGTH_SHORT).show();
    }

//    이미지 선택
    public void btnImage(View view) {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 10);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 10:
                if(resultCode==RESULT_OK){
                    imgUri = data.getData();

                    if(imgUri!=null){
                        ivProfile.setVisibility(View.VISIBLE);
                    }

//                   Toast.makeText(this, imgUri.toString(), Toast.LENGTH_SHORT).show();
                   Picasso.get().load(imgUri).into(ivProfile);

                    gProfile = imgUri.toString();


                }
                break;
        }
    }

//    완료버튼
    public void clickCreate(View view) {

        gId = etId.getText().toString();
        gPw = etPw.getText().toString();
        gName = etName.getText().toString();
        gNick = etNick.getText().toString();




        firebaseAuth.createUserWithEmailAndPassword(gId, gPw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull final Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            gId = task.getResult().getUser().getEmail();






                            User user = new User(gId, gName, gNick, gProfile);

                            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                            database.child("users").child(getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {


                                    finish();
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                }
                            });





                        } else {
                            //에러발생시
                            Toast.makeText(SignUpActivity.this, "등록 에러!" +" 에러유형\n - 이미 등록된 이메일  \n -암호 최소 6자리 이상 \n - 서버에러", Toast.LENGTH_SHORT).show();


                        }

                    }
                });
    }
}
