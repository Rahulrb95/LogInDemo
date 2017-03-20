package com.example.vishal.logindemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vishal on 13-Mar-17.
 */
public class DataHelp {

    SQLiteDatabase db;
    Context myContext;
    ArrayList<String> info=new ArrayList<>();

    public DataHelp(Context myContext) {
        this.myContext = myContext;
        SQLiteOpenHelper myHelper = new MyOpenHelper(this.myContext);
        this.db = myHelper.getWritableDatabase();
    }

    public void LoginInsert(String email, String password){
        ContentValues con = new ContentValues();
        con.put("Email",email);
        con.put("Password",password);
        long newresult = db.insert(MyOpenHelper.L_TABLE_NAME,null,con);
        if(newresult >=0)
        {
            mToast("Login Successful....");
        }else{
            mToast("Login Fail !!!!!");
        }

    }
    public void SignUpInsert(String name,String email,String password,String contact)
    {
        ContentValues con = new ContentValues();
        con.put("Name",name);
        con.put("Email", email);
        con.put("Password", password);
        con.put("Contact_No",contact);

        long newresult = db.insert(MyOpenHelper.TABLE_NAME, null, con);

        if (newresult >= 0) {
            mToast("Account created Successfully...");
        } else {
            mToast("Account creation failed!!!!!!");
        }

    }


    public Boolean checkLogin(String unm, String p) {
        Boolean result = false;

        Cursor c = db.rawQuery("Select * from SignUp", null);

        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String uid = c.getString(c.getColumnIndex("Email"));
                    String pass = c.getString(c.getColumnIndex("Password"));

                    if (uid.equals(unm) && pass.equals(p)) {
                        result = true;
                        break;

                    }
                } while (c.moveToNext());

            }
        } else {

            result = false;
        }
        c.close();

        return result;
    }


    public List<String> display()
    {
        info.clear();
        Cursor c=db.rawQuery("Select * from EmployeeInfo",null);


        if(c!=null)
        {
            if(c.moveToFirst())
            {
                do {
                    String id=c.getString(c.getColumnIndex("Name"));
                    String name=c.getString(c.getColumnIndex("Email"));
                    String con=c.getString(c.getColumnIndex("Contact_No"));
                    info.add(id+"  "+name+"  "+con);
                }while(c.moveToNext());
            }
        }
        else
        {
            mToast("Table has no contents");
        }
        c.close();
        return info;
    }


    public void mToast(String msg)
    {
        Toast.makeText(myContext,msg, Toast.LENGTH_LONG).show();
    }

}

