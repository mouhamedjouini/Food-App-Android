package com.example.projectandroid.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.projectandroid.R;
import com.example.projectandroid.databinding.ActivityIntroBinding;
import com.example.projectandroid.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignupActivity extends BaseActivity {
ActivitySignupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setVariable();
    }

    private void setVariable() {
        binding.SignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.useredt.getText().toString();
                String password = binding.passedt.getText().toString();
                if (password.length()<6){
                    Toast.makeText(SignupActivity.this,"your password must be 6 CHARACHTERS",Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete()){
                            Log.i(TAG,"On Complete");
                            startActivity(new Intent(SignupActivity.this,MainActivity.class));

                        }else {
                            Log.i(TAG,"faillure"+task.getException());
                            Toast.makeText(SignupActivity.this,"AUTHENTIFICATION FAILLURE",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}