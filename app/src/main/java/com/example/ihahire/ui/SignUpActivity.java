package com.example.ihahire.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ihahire.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG= SignUpActivity.class.getSimpleName();

    private FirebaseAuth authorized;

    private FirebaseAuth.AuthStateListener authorizedListener;

    @BindView(R.id.input) EditText nameOrg;
    @BindView(R.id.input1)EditText emailOrg;
    @BindView(R.id.input2)EditText passwordOrg;
    @BindView(R.id.input3)EditText confirmedPassword;
    @BindView(R.id.create) Button createAccount;
    @BindView(R.id.loginTextView) TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        createAccount.setOnClickListener(this);
        login.setOnClickListener(this);
        
        authorized=FirebaseAuth.getInstance();
        
        createAuthStateListener();
    }



    @Override
    public void onClick(View push) {
        if(push==login){
            Intent go=new Intent(SignUpActivity.this,LoginActivity.class);
            go.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(go);
            finish();
        }
        if(push==createAccount){
            createNewAccount();
        }

    }
    private void createNewAccount() {
        final String name=nameOrg.getText().toString().trim();
        final String email=emailOrg.getText().toString().trim();
        String password=passwordOrg.getText().toString().trim();
        String confrimPasword=confirmedPassword.getText().toString().trim();


        authorized.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Authentication successful");
                        } else {
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void createAuthStateListener() {

        authorizedListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser account = firebaseAuth.getCurrentUser();
                if (account != null) {
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }

        };

    }


    @Override
    public void onStart() {
        super.onStart();
        authorized.addAuthStateListener(authorizedListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authorizedListener != null) {
            authorized.removeAuthStateListener(authorizedListener);
        }
    }
}
