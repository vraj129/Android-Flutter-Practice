package com.example.sqlitedemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "idz.db";
    public static final String DBLOCATION = "/data/data/com.example.sqlitedemo/databases/";
    private Context mContext;
    private SQLiteDatabase  mDatabase;

    public DatabaseHelper(Context context)
    {
        super(context,DBNAME,null,1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void openDatabase()
    {
        String dbpath = mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase != null && mDatabase.isOpen())
        {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbpath,null,SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase()
    {
        if(mDatabase!=null)
        {
            mDatabase.close();
        }
    }
    public List<model> getItem()
    {
        model m = null;
        List<model> mlist = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("select * from fruit",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            m = new model(cursor.getString(0));
            mlist.add(m);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return mlist;
    }
}
