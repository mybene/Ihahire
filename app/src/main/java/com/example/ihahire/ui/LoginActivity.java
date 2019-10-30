package com.example.ihahire.ui;

import android.app.ProgressDialog;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener  {

    public static final String TAG = LoginActivity.class.getSimpleName();



    @BindView(R.id.createAccount) TextView registration;
    @BindView(R.id.input) EditText emailOrg;
    @BindView(R.id.input1)EditText passwordlOrg;
    @BindView(R.id.push) Button login;


    private FirebaseAuth authorized;

    private FirebaseAuth.AuthStateListener authorizedListener;

    private ProgressDialog mAuthProgressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        authorized=FirebaseAuth.getInstance();
        createAuthProgressDialog();
        
       
       authorizedListener=new FirebaseAuth.AuthStateListener(){

           @Override
           public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               FirebaseUser user = firebaseAuth.getCurrentUser();
               if (user != null) {
                   Intent intent = new Intent(LoginActivity.this, choiceActivity.class);
                   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                   startActivity(intent);
                   finish();
               }
           }
       };

        registration.setOnClickListener(this);

        login.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if(v==registration){
            Intent newAccount=new Intent(LoginActivity.this,SignUpActivity.class);
            startActivity(newAccount);
            finish();

        }
        if(v==login){
            loginWithPassword();
        }

    }
    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
    }


    private void loginWithPassword() {
        String email = emailOrg.getText().toString().trim();
        String password = passwordlOrg.getText().toString().trim();
        if (email.equals("")) {
            emailOrg.setError("Please enter your email");
            return;
        }
        if (password.equals("")) {
            passwordlOrg.setError("Password cannot be blank");
            return;
        }

        mAuthProgressDialog.show();
        authorized.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mAuthProgressDialog.dismiss();
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(LoginActivity.this,choiceActivity.class);
                            startActivity(intent);
                        }
                    }
                });


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

