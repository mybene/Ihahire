package com.example.ihahire.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ihahire.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.createAccount) TextView registartion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        registartion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View createAccount) {
                Intent newAccount=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(newAccount);
               finish();
            }
        });
    }
}
