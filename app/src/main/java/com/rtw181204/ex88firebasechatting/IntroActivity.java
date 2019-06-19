package com.rtw181204.ex88firebasechatting;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class IntroActivity extends BaseActivity {


    ImageView iv;

    Timer timer = new Timer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        iv = findViewById(R.id.iv);

        Animation ani = AnimationUtils.loadAnimation(this,R.anim.appear_logo);
        iv.startAnimation(ani);



        timer.schedule(task,3000);



        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){


            Log.i("TAG",firebaseAuth.getCurrentUser().getEmail());

            final String uid =  firebaseAuth.getCurrentUser().getUid();
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


            FirebaseDatabase.getInstance().getReference("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    int cnt=0;
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        uids.add(snapshot.getKey());

                        Log.i("TAG","Intro " +uids.get(cnt));
                        cnt++;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        };

    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {

            Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
            startActivity(intent);

            finish();
        }
    };

}
