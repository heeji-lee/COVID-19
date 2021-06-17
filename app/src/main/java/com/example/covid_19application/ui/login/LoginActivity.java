package com.example.covid_19application.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.covid_19application.MainActivity;
import com.example.covid_19application.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText edId, edPwd;
    Button btn;

    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("로그인");
        edId = findViewById(R.id.id);
        edPwd = findViewById(R.id.pwd);
        btn = findViewById(R.id.btnLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginID = edId.getText().toString();
                String loginPW = edPwd.getText().toString();

                if (loginID.isEmpty()) {
                    edId.setError("이메일을 입력하세요!");
                    edId.requestFocus();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(loginID).matches()) {
                    edId.setError("이메일 형식이 올바르지 않습니다.");
                    edId.requestFocus();
                    return;
                } else if (loginPW.isEmpty()) {
                    edPwd.setError("비밀번호를 입력하세요!");
                    edPwd.requestFocus();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(loginID,loginPW).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            intent.putExtra("response","success");
                            intent.putExtra("loginID",loginID);
                            finish();
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "ID 혹은 비밀번호를 잘못 입력하셨거나 등록되지 않은 ID입니다.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}