package com.rtw181204.ex88firebasechatting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity {

    TextView tvNick;
    CircleImageView ivProfile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNick = findViewById(R.id.tv_nick);
        ivProfile = findViewById(R.id.iv_profile);

        loadData();



        tvNick.setText(gNick);

        Glide.with(this).load(gProfile).into(ivProfile);



    }




    public void clickBtn(View view) {
        //닉네임 얻어오기



        startActivity(new Intent(this, ChatActivity.class));

    }






    void loadData(){



        String uid = FirebaseAuth.getInstance().getUid();
        // 데이터 불러오기
        FirebaseDatabase.getInstance().getReference().child("users").child(uid).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);


                if(user!=null){
                    gId = user.id;
                    gName = user.name;
                    gNick = user.nick;
                    gProfile = user.profile;
                    gUid = FirebaseAuth.getInstance().getUid();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }




}

