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

public class Register extends AppCompatActivity {
        String email_pattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}";
    Button back_button,Register;
    EditText confirm_pass,enter_passwd,enter_email;
    TextView back_login;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        back_button =findViewById(R.id.back_Register);
        back_login = findViewById(R.id.back_login);
        Register = findViewById(R.id.Register);
        enter_passwd =findViewById(R.id.enter_pass_register);
        confirm_pass = findViewById(R.id.enter_Cpassword_Register);
        enter_email = findViewById(R.id.enter_email_Reg);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 perAuth();
            }
        });
        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register.this,Log_in.class);
                startActivity(i);
            }
        });

    }

    private void perAuth() {
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        String email = enter_email.getText().toString();
            String cPass = confirm_pass.getText().toString();
            String password = enter_passwd.getText().toString();
                   if(!email.matches(email_pattern)){
                       enter_email.setError("enter correct email");
                   }else if(password.isEmpty()|| password.length()<6){

                           enter_passwd.setError("password is too short");

                   }else if (!password.equals(cPass)){
                       confirm_pass.setError("password does not match");
                   }
                   else{
                       mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(
                               new OnCompleteListener<AuthResult>() {
                                   @Override
                                   public void onComplete(@NonNull Task<AuthResult> task) {
                                       if(task.isSuccessful()){
                                           sendUserToNextActivity();
                                           Toast.makeText(Register.this, "Registration is succesfull", Toast.LENGTH_SHORT).show();


                                       }else{
                                           Toast.makeText(Register.this, "Error", Toast.LENGTH_SHORT).show();
                                       }
                                   }
                               });
                   }


    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(Register.this,Home_page.class);
       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}