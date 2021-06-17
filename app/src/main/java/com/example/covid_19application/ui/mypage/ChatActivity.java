package com.example.covid_19application.ui.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19application.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    RecyclerView rvChat;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<ChatData> chatList;

    EditText edChat;
    Button btnSend;

    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setTitle("고객센터");

        Intent intent=getIntent();
        String id=intent.getStringExtra("userID");
        String match="[^\\uAC00-\\uD7A3xfe0-9a-zA-Z\\\\s]";
        FirebaseDatabase.getInstance().getReference().child("User").child(id.replaceAll(match,"")).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.getKey().equals("name")){
                    String nickname=(String)snapshot.getValue();

                    edChat=findViewById(R.id.edChat);
                    btnSend=findViewById(R.id.btnSend);
                    btnSend.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String msg=edChat.getText().toString();

                            if(msg!=null){
                                ChatData chat=new ChatData();
                                chat.setNickname(nickname);
                                chat.setMsg(msg);
                                myRef.push().setValue(chat);
                            }
                        }
                    });

                    rvChat=findViewById(R.id.rvChat);
                    rvChat.setHasFixedSize(true);
                    layoutManager=new LinearLayoutManager(ChatActivity.this);
                    rvChat.setLayoutManager(layoutManager);

                    chatList=new ArrayList<>();
                    adapter=new ChatAdapter(chatList, ChatActivity.this, nickname);
                    rvChat.setAdapter(adapter);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");

        ChatData chat=new ChatData();
//        chat.setNickname(nickname);
//        chat.setMsg("hi");
        myRef.setValue(chat);
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ChatData chat=snapshot.getValue(ChatData.class);
                ((ChatAdapter) adapter).addChat(chat);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}