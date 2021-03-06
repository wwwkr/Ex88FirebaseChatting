package com.rtw181204.ex88firebasechatting;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends BaseAdapter {

    ArrayList<MessageItem> messageItems;
    LayoutInflater inflater;
    String userUid;
    View itemView;
    ArrayList<String> datas = new ArrayList<>();

    public ChatAdapter(ArrayList<MessageItem> messageItems, LayoutInflater inflater) {
        this.messageItems = messageItems;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return messageItems.size();
    }

    @Override
    public Object getItem(int position) {
        return messageItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        //현재 보여줄 번째(position)의 데이터로 뷰를 생성
        MessageItem item = messageItems.get(position);


        //재활용하는 view(convertView)는 사용하지 않음




//        if(item.getName().equals(G.nickName)){
//            itemView = inflater.inflate(R.layout.my_msgbox, parent , false);
//        }else {
//            itemView = inflater.inflate(R.layout.other_msgbox, parent , false);
//        }

//        FirebaseDatabase.getInstance().getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//
//                    User user = snapshot.getValue(User.class);
//
//                    datas.add(user.uid);
//
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });




//        item.uid.equals(BaseActivity.getUid()))


        try {
            if(item.name.equals(BaseActivity.gNick)){
                itemView = inflater.inflate(R.layout.my_msgbox, parent , false);

            }else {

                itemView = inflater.inflate(R.layout.other_msgbox, parent , false);

            }

        }catch (Exception e){
            e.getStackTrace();
        }






        //2. bind View
        CircleImageView iv = itemView.findViewById(R.id.iv);
        TextView tvName = itemView.findViewById(R.id.tv_name);
        TextView tvMsg = itemView.findViewById(R.id.tv_msg);
        TextView tvTime = itemView.findViewById(R.id.tv_time);

        tvName.setText(item.getName());
        tvMsg.setText(item.getMessage());
        tvTime.setText(item.getTime());

        Glide.with(itemView).load(item.profileUrl).into(iv);



        return itemView;
    }
}
