package com.rtw181204.ex88firebasechatting;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class BaseActivity extends AppCompatActivity {


    public static String gId;
    public static String gPw;
    public static String gName;
    public static String gNick;
    public static String gProfile;
    public static String gUid;

    public static String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
}
