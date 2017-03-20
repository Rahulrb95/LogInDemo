package com.example.vishal.logindemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Vishal on 13-Mar-17.
 */

public class LoginWelcomeActivity extends AppCompatActivity

    {

    TextView mTxtdispName, mTxtdispEmail, mTxtdispPhone;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(LoginWelcomeActivity.this, MainActivity.class);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(in);
    }

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.content_welcome);

            Bundle b=getIntent().getExtras();
            String name = b.getString("Name");
            String email =b.getString("Email");
            String phone =b.getString("Contact_No");


            mTxtdispName= (TextView) findViewById(R.id.tv_name);
            mTxtdispEmail = (TextView)findViewById(R.id.tv_email);
            mTxtdispPhone = (TextView)findViewById(R.id.tv_phone);

            mTxtdispName.setText(name.toString());
            mTxtdispEmail.setText(email.toString());
            mTxtdispPhone.setText(phone.toString());



        }
    }

