package com.example.nickjobsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    private EditText emailRegistration;
    private EditText passwordRegistration;

    private Button btnRegistration;
    private Button btnLogin;

     private FirebaseAuth mAuth;
     private ProgressDialog mDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth=FirebaseAuth.getInstance();

        mDialog=new ProgressDialog(this);

        Registration();
    }
    private void Registration(){
        emailRegistration=findViewById(R.id.email_registration);
        passwordRegistration=findViewById(R.id.registration_password);

        btnRegistration=findViewById(R.id.btn_registration);
        btnLogin=findViewById(R.id.btn_login);

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email=emailRegistration.getText().toString().trim();
                String password=passwordRegistration.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    emailRegistration.setError("Required field..");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    passwordRegistration.setError("Required field..");
                    return;
                }

                mDialog.setMessage("Processing..");
                mDialog.show();

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Successful", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));

                            mDialog.dismiss();
                        }else {
                            Toast.makeText(getApplicationContext(),"Registration failed..",Toast.LENGTH_SHORT).show();

                            mDialog.dismiss();
                        }

                    }
                });

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });
    }
}