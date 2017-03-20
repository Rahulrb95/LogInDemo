package com.example.vishal.logindemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Vishal on 13-Mar-17.
 */
public class MyOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "LoginDatabase";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "SignUp";
    public static final String L_TABLE_NAME = "Login";
    Context mycontext;//this class(MyOpenHelper)is not extended by Activity..So we created the object of Context so that we get form memory..which is required for our normal controls..

    public MyOpenHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mycontext= context;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)//object of SQLiteDatabase holds memory of database name
    {
        sqLiteDatabase.execSQL("CREATE TABLE SignUp(Name Text,Email TEXT,Password TEXT,Contact_No Integer)");
        sqLiteDatabase.execSQL("Create Table Login(Email Text,Password Text)");
    }
    private void mToast(String string)
    {
        // TODO Auto-generated method stub
        Toast.makeText(mycontext,string,Toast.LENGTH_SHORT).show();
    }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        // TODO Auto-generated method stub
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + L_TABLE_NAME);
        onCreate(sqLiteDatabase);
        System.out.println("On Upgrade call");
    }
}