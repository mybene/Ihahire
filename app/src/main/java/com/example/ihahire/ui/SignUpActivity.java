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
import com.google.firebase.auth.UserProfileChangeRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG= SignUpActivity.class.getSimpleName();

    private FirebaseAuth authorized;

    private FirebaseAuth.AuthStateListener authorizedListener;

    private ProgressDialog mAuthProgressDialog;



    @BindView(R.id.input) EditText nameOrg;
    @BindView(R.id.input1)EditText emailOrg;
    @BindView(R.id.input2)EditText passwordOrg;
    @BindView(R.id.input3)EditText confirmedPassword;
    @BindView(R.id.create) Button createAccount;
    @BindView(R.id.loginTextView) TextView login;


    private String fName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        createAccount.setOnClickListener(this);

        login.setOnClickListener(this);
        
        authorized=FirebaseAuth.getInstance();
        
        createAuthStateListener();


        createAuthProgressDialog();
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(true);
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
        fName=nameOrg.getText().toString().trim();


        boolean validEmail=isValidEmail(email);
        boolean validName=isValidName(name);
        boolean validPassword=isValidPassword(password,confrimPasword);

        if(!validEmail||!validName||!validPassword)return;

        mAuthProgressDialog.show();

        authorized.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> action) {

                        mAuthProgressDialog.dismiss();

                        if (action.isSuccessful()) {
                            Log.d(TAG, "Authentication successful");

                            createFirebaseUserProfile(action.getResult().getUser());

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
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
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

    private boolean isValidEmail(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            emailOrg.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidName(String fName) {
        if (fName.equals("")) {
            nameOrg.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            passwordOrg.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            passwordOrg.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    private void createFirebaseUserProfile(final FirebaseUser user) {

        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(fName).build();

        user.updateProfile(addProfileName)
                .addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "The displayed name has been set", Toast.LENGTH_LONG).show();

                        }
                    }

                });
    }
}
