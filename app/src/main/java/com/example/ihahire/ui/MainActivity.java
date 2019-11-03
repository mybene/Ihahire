package com.example.ihahire.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ihahire.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.above)Button login ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v) {
                //Toast to show the user that  buying  login page is loading
                Toast.makeText(MainActivity.this, "Welcome!!!", Toast.LENGTH_LONG).show();

                Intent intent= new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }

        });

    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


}