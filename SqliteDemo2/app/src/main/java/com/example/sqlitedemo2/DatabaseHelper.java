package com.example.sqlitedemo2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.sqlitedemo2.fruitlist.fruitadd.*;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "idz.db";
    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String Sql_create_table = "CREATE TABLE "+ fruitlist.fruitadd.TABLE_NAME +" ("+ fruitlist.fruitadd._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                fruitlist.fruitadd.COLUMN_NAME+" TEXT);";

         sqLiteDatabase.execSQL(Sql_create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+fruitlist.fruitadd.TABLE_NAME);
            onCreate(sqLiteDatabase);
    }
    void updateData(int id,String name)
    {
        SQLiteDatabase sq = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(fruitlist.fruitadd.COLUMN_NAME,name);
        sq.update(fruitlist.fruitadd.TABLE_NAME,cv,fruitlist.fruitadd._ID + "=?",new String[]{String.valueOf(id)});

    }
}
