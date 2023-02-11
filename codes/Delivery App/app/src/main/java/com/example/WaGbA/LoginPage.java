package com.example.WaGbA;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.WaGbA.databinding.ActivityLoginPageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ActivityLoginPageBinding binding;
    Intent mainPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth = FirebaseAuth.getInstance();
        mainPage=new Intent(this, RestaurantsPage.class);

        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.loginbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loginAccount();
                    }
                });

            }
        });
    }

    private void loginAccount() {

        String mail = binding.mailtxt.getText().toString();
        String password = binding.passwordtxt.getText().toString();

        if (mail.isEmpty()) {
            binding.mailtxt.setError("Email is required!");
            binding.mailtxt.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            binding.passwordtxt.setError("Password is required!");
            binding.passwordtxt.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(mail,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginPage.this, "Logged in successfully!", Toast.LENGTH_LONG).show();
                            startActivity(mainPage);
                            finish();


                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginPage.this,
                                    "Email or Password is incorrect please try again", Toast.LENGTH_LONG).show();

                        }
                    }
                });



    }
}