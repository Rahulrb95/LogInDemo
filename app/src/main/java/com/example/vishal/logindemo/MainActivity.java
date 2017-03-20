package com.example.vishal.logindemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

/**
 * Created by Vishal on 13-Mar-17.
 */
public class MainActivity extends AppCompatActivity {

    Button mBtnLogin,mBtnSignUp,mBtnSignIn,mBtnCreateAccount;
    EditText mEdtPassword,mEdtEmail,mEdtName,mEdtPhone;
    ViewFlipper viewFlipper;
    DataHelp dh;
    String pattern = "^[a-zA-Z0-9]*$";
    String phPattern = "^\\d{10}";
    String passPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contenet_main);

        mBtnLogin= (Button) findViewById(R.id.bt_login);
        mBtnSignUp= (Button) findViewById(R.id.bt_signup);
        mBtnSignIn= (Button) findViewById(R.id.bt_sign_in);
        mBtnCreateAccount= (Button) findViewById(R.id.bt_create);
        viewFlipper= (ViewFlipper) findViewById(R.id.view_flipper);

        mEdtName= (EditText) findViewById(R.id.et_name);
        mEdtPhone= (EditText) findViewById(R.id.et_phone);
        mEdtEmail= (EditText) findViewById(R.id.et_email);
        mEdtPassword= (EditText) findViewById(R.id.et_password);

        dh=new DataHelp(this);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnSignUp.setBackgroundColor(getResources().getColor(R.color.white));
                mBtnLogin.setBackgroundColor(getResources().getColor(R.color.buttoncolor));

                viewFlipper.showNext();
                mEdtName.setVisibility(View.INVISIBLE);
            }
        });
        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnLogin.setBackgroundColor(getResources().getColor(R.color.white));
                mBtnSignUp.setBackgroundColor(getResources().getColor(R.color.buttoncolor));

                mEdtName.requestFocus();
                viewFlipper.showPrevious();
                mEdtName.setVisibility(View.VISIBLE);
            }
        });
        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,LoginWelcomeActivity.class);

                Boolean result=dh.checkLogin(mEdtEmail.getText().toString(),mEdtPassword.getText().toString());


                if(result)
                {

                    Bundle b = new Bundle();
                    b.putString("Name",mEdtName.getText().toString());
                    b.putString("Email",mEdtEmail.getText().toString());
                    b.putString("Contact_No",mEdtPhone.getText().toString());
                    intent.putExtras(b);
                    startActivity(intent);

                }
                else
                {
                    mToast("User's login creditential are not found");
                }

            }

        });

        mBtnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name = mEdtName.getText().toString();
                String phone = mEdtPhone.getText().toString();
                String emailId = mEdtEmail.getText().toString();
                String pass = mEdtPassword.getText().toString();


                if (name.matches(pattern)) {
                    if (android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches() && pass.matches(passPattern)) {


                        dh.SignUpInsert(name, mEdtEmail.getText().toString(), mEdtPassword.getText().toString(), phone);
                //mEdtName.setText("");
                //mEdtEmail.setText("");
                //mEdtPassword.setText("");
                //mEdtPhone.setText("");

                    }
                    else
                    {
                        mToast("Please enter valid details.");

                    }
                }
                else
                {
                    mToast("Please enter valid details.");
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void mToast(String msg)
    {
        Toast.makeText(this,msg, Toast.LENGTH_LONG).show();
    }

}
