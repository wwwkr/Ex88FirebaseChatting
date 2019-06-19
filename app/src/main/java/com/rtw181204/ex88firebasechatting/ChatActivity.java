package com.rtw181204.ex88firebasechatting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class ChatActivity extends BaseActivity {

    ListView listView;
    EditText etMsg;

    ArrayList<MessageItem> messageItems = new ArrayList<>();

    ChatAdapter adapter;

    DatabaseReference chatRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Log.i("TAGAA","Chat");

        //제목줄에 내 닉네임이 표시되도록
//        getSupportActionBar().setTitle(gNick);

        listView = findViewById(R.id.listview);
        etMsg = findViewById(R.id.et);

        adapter = new ChatAdapter(messageItems, getLayoutInflater());

        listView.setAdapter(adapter);

        //'chat' 노도의 참조객체 얻어오기
        chatRef = FirebaseDatabase.getInstance().getReference("chat");

        //'chat' 노드에 저장되어 있는 데이터들을 읽어오기
        //chatRef에 데이터가 변경되는 것을 듣는 리스너 추가

        chatRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                //첫번째 파라미터 : 새로 추가된 데이터노드의 정보를 가진 dataSnapshot객체
                MessageItem messageItem = dataSnapshot.getValue(MessageItem.class);



                //리스트뷰가 보여주는 대량의 데이터인 ArrayList에 추가
                messageItems.add(messageItem);

                //리스트뷰화면 갱신
                adapter.notifyDataSetChanged();
                //리스트뷰의 커서를 가장 마지막 위치로..
                listView.setSelection(messageItems.size()-1);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void clickSend(View view) {

        String nickName = gNick;
        String message = etMsg.getText().toString();
        String profileUrl = gProfile;

        Calendar calendar = Calendar.getInstance();
        String time = calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);


        //메세지를 firebase DB에 객체 통째로 저장
        MessageItem messageItem = new MessageItem(nickName, message ,time, profileUrl,getUid());

        //'chat' 노드에 객체 통째로 값 추가(push)
        chatRef.push().setValue(messageItem);

        etMsg.setText("");
        listView.setSelection(messageItems.size()-1);





    }


}
