package com.example.WaGbA;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.WaGbA.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class SignIn extends AppCompatActivity {

    private ActivitySignInBinding binding;
    private FirebaseAuth mAuth;
    private Intent mainPage;
    public static final Pattern School_Email
            = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +"eng.asu.edu.eg"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        mAuth = FirebaseAuth.getInstance();
        mainPage=new Intent(this, RestaurantsPage.class);
        binding.createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createAccount();

            }
        });

    }

    private void createAccount() {

        String name=binding.nameEdt.getText().toString();
        String mail=binding.mailEdt.getText().toString();
        String phone=binding.phoneEdt.getText().toString();
        String address=binding.addrEdt.getText().toString();
        String password=binding.passEdt.getText().toString();

        if(name.isEmpty()){
            binding.nameEdt.setError("Name is required!");
            binding.nameEdt.requestFocus();
            return;
        }
        if(mail.isEmpty()){
            binding.mailEdt.setError("Email is required!");
            binding.mailEdt.requestFocus();
            return;
        }

        if(!Patterns.PHONE.matcher(phone).matches()){
            binding.phoneEdt.setError("Please provide a valid phone number!");
            binding.phoneEdt.requestFocus();
            return;

        }
        if(address.isEmpty()){
            binding.addrEdt.setError("Address is required!");
            binding.addrEdt.requestFocus();
            return;
        }
        if(password.isEmpty()){
            binding.passEdt.setError("Password is required!");
            binding.passEdt.requestFocus();
            return;
        }
        if(password.length()<6){
            binding.passEdt.setError("Password length must be >=6 characters!");
            binding.passEdt.requestFocus();
            return;
        }

        if(!School_Email.matcher(mail).matches()){
            binding.mailEdt.setError("Please provide a valid email address!");
            binding.mailEdt.requestFocus();
            return;

        }

        UserEntity userEntity=new UserEntity();
        userEntity.setUsername(binding.nameEdt.getText().toString());
        userEntity.setUsermail(binding.mailEdt.getText().toString());
        userEntity.setUserphone(Integer.parseInt(binding.phoneEdt.getText().toString()));
        userEntity.setUseraddress(binding.addrEdt.getText().toString());
        userEntity.setUserpassword(binding.passEdt.getText().toString());

        UserDatabase userDatabase=UserDatabase.getUserDatabase(getApplicationContext());
        UserDao userDao=userDatabase.userDao();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });

            mAuth.createUserWithEmailAndPassword(mail, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignIn.this, "Successfully Signed In!", Toast.LENGTH_LONG).show();
                                startActivity(mainPage);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignIn.this, "Sign in failed!Make another trial!", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }
    }

