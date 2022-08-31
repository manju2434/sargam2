package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Log_in extends AppCompatActivity {
    Button log_in,back;
    EditText enterEmail,enterPasswd;
    TextView log_in_page;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    ProgressDialog progressDialog;
    String email_pattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        log_in =findViewById(R.id.log_in);
        back = findViewById(R.id.back_login);
        enterEmail = findViewById(R.id.enter_name_login);
        enterPasswd = findViewById(R.id.enter_passwd_login);
        log_in_page = findViewById(R.id.back_Register);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               performLogin();
            }
        });
        log_in_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Log_in.this,Register.class);
                startActivity(i);
            }
        });

    }

    private void performLogin() {
        String email = enterEmail.getText().toString();

        String password = enterPasswd.getText().toString();
        if(!email.matches(email_pattern)){
            enterEmail.setError("enter correct email");
        }else if(password.isEmpty()|| password.length()<6){
            if(password.isEmpty()){
                enterPasswd.setError("entered password is empty please provide a valid password");
            }else{

                enterPasswd.setError("password is too short");
            }
        }
        else{
            progressDialog.setMessage("Please wait while we login");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        Toast.makeText(Log_in.this, "Registration is succesfull", Toast.LENGTH_SHORT).show();
                        sendUserToNextActivity();
                        progressDialog.dismiss();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(Log_in.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
    private void sendUserToNextActivity(){
        Intent intent = new Intent(Log_in.this,Home_page.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    }
