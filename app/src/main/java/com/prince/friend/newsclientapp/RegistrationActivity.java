package com.prince.friend.newsclientapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private TextView haveAccount;
    private FirebaseAuth mAuth;
    private EditText userName,userEmail,userPassword,confirmPassword;
    private AppCompatButton signUpButton;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Email = "emailKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        haveAccount = findViewById(R.id.have_account);
        userName = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email);
        userPassword = findViewById(R.id.user_password);
        confirmPassword = findViewById(R.id.user_confirm_password);
        signUpButton = findViewById(R.id.sign_up_button);


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        mAuth = FirebaseAuth.getInstance();
        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                finish();
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userName.getText().toString().trim().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                }else if(userEmail.getText().toString().trim().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Enter vaild email", Toast.LENGTH_SHORT).show();
                }else if(userPassword.getText().toString().trim().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                }else if(!userPassword.getText().toString().trim().equals(confirmPassword.getText().toString().trim())){
                    Toast.makeText(RegistrationActivity.this, "Enter valid password", Toast.LENGTH_SHORT).show();
                }else {
                    if(emailChecker(userEmail.getText().toString().trim())){

                        createUser(userEmail.getText().toString().trim(),
                                userPassword.getText().toString().trim());

                    }else{
                        Toast.makeText(RegistrationActivity.this, "Enter Valid email", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    boolean emailChecker(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    void createUser(String email , String password){

        SharedPreferences.Editor editor = sharedpreferences.edit();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    editor.putString(Email, email);
                    editor.apply();

                    startActivity(new Intent(RegistrationActivity.this,Home.class));
                    finish();
                    Toast.makeText(RegistrationActivity.this, "User Created Successfully..", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(RegistrationActivity.this, "Email already existed", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegistrationActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }


}