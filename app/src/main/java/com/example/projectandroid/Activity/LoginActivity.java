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
import com.example.projectandroid.databinding.ActivityLoginBinding;
import com.example.projectandroid.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setVariable();
    }

    private void setVariable() {
        binding.LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.useredt.getText().toString();
                String password = binding.passedt.getText().toString();
                if (!email.isEmpty() && !password.isEmpty()) {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.i(TAG, "On Complete");
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));

                            } else {
                                Log.i(TAG, "faillure" + task.getException());
                                Toast.makeText(LoginActivity.this, "AUTHENTIFICATION FAILLURE", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });
    }
}