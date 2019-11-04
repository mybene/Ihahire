package com.example.ihahire.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ihahire.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;


public class choiceActivity extends AppCompatActivity {
   @BindView(R.id.buy) Button mBuy;
   @BindView(R.id.sell) Button mSell;

    private SharedPreferences looked;
    private SharedPreferences.Editor edited;
    private String rececentProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);


      ButterKnife.bind(this);


        ImageView choice=(ImageView)findViewById(R.id.img2);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.myanimation);
        choice.startAnimation(animation);


        mBuy.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {

               Intent intent = new Intent(choiceActivity.this, BuyMainActivity.class);
               startActivity(intent);


           }
       });

        mSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(choiceActivity.this, sellActivity.class);
                startActivity(intent1);


            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            signOut();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent= new Intent(choiceActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
