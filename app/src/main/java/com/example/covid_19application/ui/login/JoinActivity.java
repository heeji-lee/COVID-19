package com.example.covid_19application.ui.login;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.covid_19application.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JoinActivity extends AppCompatActivity {
    EditText edId, edPwd, edPwdCheck, edName, edAddress, edTel;
    Button btn;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        setTitle("회원가입");
        edId=findViewById(R.id.edId);
        edPwd=findViewById(R.id.edPwd);
        edPwdCheck=findViewById(R.id.edPwdCheck);
        edName=findViewById(R.id.edName);
        edAddress=findViewById(R.id.edAddress);
        edTel=findViewById(R.id.edTel);
        btn=findViewById(R.id.btnRegister);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edId.getText().toString();
                String pwd = edPwd.getText().toString();
                String pwdCheck = edPwdCheck.getText().toString();
                String name = edName.getText().toString();
                String address = edAddress.getText().toString();
                String tel = edTel.getText().toString();

                if (id.isEmpty()) {
                    edId.setError("이메일을 입력하세요!");
                    edId.requestFocus();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(id).matches()) {
                    edId.setError("이메일 형식이 올바르지 않습니다.");
                    edId.requestFocus();
                    return;
                } else if (pwd.isEmpty()) {
                    edPwd.setError("비밀번호를 입력하세요!");
                    edPwd.requestFocus();
                    return;
                } else if (pwdCheck.isEmpty()) {
                    edPwdCheck.setError("비밀번호를 입력하세요!");
                    edPwdCheck.requestFocus();
                    return;
                } else if (!pwd.equals(pwdCheck)) {
                    edPwdCheck.setError("비밀번호가 일치하지 않습니다.");
                    edPwdCheck.requestFocus();
                    return;
                } else if (pwd.length() < 6) {
                    edPwd.setError("비밀번호는 6자리 이상이어야 합니다.");
                    edPwd.requestFocus();
                    return;
                } else if (name.isEmpty()) {
                    edName.setError("이름을 입력하세요!");
                    edName.requestFocus();
                    return;
                } else if (address.isEmpty()) {
                    edAddress.setError("주소를 입력하세요!");
                    edAddress.requestFocus();
                    return;
                } else if (tel.isEmpty()) {
                    edTel.setError("전화번호를 입력하세요!");
                    edTel.requestFocus();
                    return;
                }
                register(id,pwd,name,address,tel);
                saveDB(id,pwd,name,address,tel);
            }
        });
    }

    private void register(String email, String pw, String name, String address, String tel){
        firebaseAuth.createUserWithEmailAndPassword(email,pw).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(JoinActivity.this,"회원가입성공",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    private void saveDB(String email, String pw, String name, String address, String tel){
        long now=System.currentTimeMillis();
        Date date=new Date(now);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd aa HH:mm");
        String regDate=sdf.format(date);

        User user=new User(email, pw, name, address, tel, regDate);

        String match="[^\\uAC00-\\uD7A3xfe0-9a-zA-Z\\\\s]";

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference=database.getReference("User");
        databaseReference.child(email.replaceAll(match,"")).setValue(user);
    }
}