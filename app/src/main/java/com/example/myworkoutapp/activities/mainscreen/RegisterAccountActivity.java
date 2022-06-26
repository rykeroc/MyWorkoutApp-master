package com.example.myworkoutapp.activities.mainscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myworkoutapp.R;
import com.example.myworkoutapp.activities.mainmenu.MainMenuActivity;
import com.example.myworkoutapp.app.NewAccountHandler;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterAccountActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button registerButton;
    private EditText fNameInput;
    private EditText lNameInput;
    private EditText uNameInput;
    private EditText emailInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.mAuth = FirebaseAuth.getInstance();
        this.registerButton = findViewById(R.id.raRegisterButton);
        this.fNameInput = findViewById(R.id.raFNameInput);
        this.lNameInput = findViewById(R.id.raLNameInput);
        this.uNameInput = findViewById(R.id.raUsernameInput);
        this.emailInput = findViewById(R.id.raEmailInput);
        this.passwordInput = findViewById(R.id.raPasswordInput);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewAccount();
            }
        });
    }

    public void registerNewAccount() {
        boolean verifyCred;
        String fName = this.fNameInput.getText().toString().trim();
        String lName = this.lNameInput.getText().toString().trim();
        String uName = this.uNameInput.getText().toString().trim();
        String email = this.emailInput.getText().toString().trim();
        String password = this.passwordInput.getText().toString().trim();

        verifyCred = verifyCredentials(fName, lName, uName, email, password);

        if (verifyCred)
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                NewAccountHandler newAccount = new NewAccountHandler(fName, lName, email);
                                String uId = mAuth.getCurrentUser().getUid();

                                FirebaseDatabase.getInstance().getReference("accounts").child(uId)
                                        .setValue(newAccount).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(RegisterAccountActivity.this, "New user registered successfully!", Toast.LENGTH_LONG).show();
                                                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                                            if(!task.isSuccessful()) {
                                                                Toast.makeText(RegisterAccountActivity.this, "Unable to sign in", Toast.LENGTH_SHORT).show();
                                                            }
                                                            startActivity(new Intent(RegisterAccountActivity.this, MainMenuActivity.class));
                                                        }
                                                    });
                                                }
                                            }
                                        });

                            } else {
                                Toast.makeText(RegisterAccountActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                emailInput.requestFocus();
                            }
                        }
                    });
    }


    private boolean verifyCredentials(String fName, String lName, String uName, String email, String password) {
        if (fName.isEmpty()) {
            fNameInput.setError("First name is required!");
            fNameInput.requestFocus();
            return false;
        }
        if (lName.isEmpty()) {
            lNameInput.setError("Last name is required!");
            lNameInput.requestFocus();
            return false;
        }
        if (uName.isEmpty()) {
            uNameInput.setError("Username is required!");
            uNameInput.requestFocus();
            return false;
        }
        if (email.isEmpty()) {
            emailInput.setError("Email address is required!");
            emailInput.requestFocus();
            return false;
        }
        if (password.isEmpty()) {
            passwordInput.setError("Password is required!");
            passwordInput.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("Please enter a valid email address!");
            emailInput.requestFocus();
            return false;
        }
        if (password.length() < 6) {
            passwordInput.setError("Password length is less than 6 characters");
            passwordInput.requestFocus();
            return false;
        }
        return true;
    }
}

