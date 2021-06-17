package com.example.covid_19application.ui.mypage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.covid_19application.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

public class PersonalDataActivity extends AppCompatActivity {

    private final int GET_GALLERY_IMAGE = 200;
    ImageView imageview;
    TextView tvName,tvPwd,tvEmail,tvTel;
    TextView tvRecipient,tvAddress,tvRecipientTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        setTitle("회원정보");
        tvName = findViewById(R.id.tvName);
        tvPwd = findViewById(R.id.tvPwd);
        tvEmail = findViewById(R.id.tvEmail);
        tvTel = findViewById(R.id.tvTel);
        tvRecipient = findViewById(R.id.tvRecipient);
        tvAddress = findViewById(R.id.tvAddress);
        tvRecipientTel = findViewById(R.id.tvRecipientTel);
//        imageview = findViewById(R.id.image);
//        imageview.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//                startActivityForResult(intent, GET_GALLERY_IMAGE);
//            }
//        });

        Intent intent=getIntent();
        String id=intent.getStringExtra("userID");
        String match="[^\\uAC00-\\uD7A3xfe0-9a-zA-Z\\\\s]";
        FirebaseDatabase.getInstance().getReference().child("User").child(id.replaceAll(match,"")).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.getKey().equals("name")){
                    tvName.setText((String)snapshot.getValue());
                    tvRecipient.setText((String)snapshot.getValue());
                }
                if(snapshot.getKey().equals("pwd")){
                    tvPwd.setText((String)snapshot.getValue());
                }
                if(snapshot.getKey().equals("id")){
                    tvEmail.setText((String)snapshot.getValue());
                }
                if(snapshot.getKey().equals("tel")){
                    tvTel.setText((String)snapshot.getValue());
                    tvRecipientTel.setText((String)snapshot.getValue());
                }
                if(snapshot.getKey().equals("address")){
                    tvAddress.setText((String)snapshot.getValue());
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            imageview.setImageURI(selectedImageUri);
        }
    }
}