package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private adapter adapter;
    private List<model> mlist;
    private DatabaseHelper mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Toast.makeText(this,"Copy Database success",Toast.LENGTH_LONG).show();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mDatabaseHelper = new DatabaseHelper(this);
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(false == database.exists())
        {
            mDatabaseHelper.getReadableDatabase();
            if(copyDatabase(MainActivity.this))
            {
                Toast.makeText(this,"Copy Database success",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this,"Copy Database error",Toast.LENGTH_LONG).show();
                return;
            }
        }
        //Toast.makeText(this,"success",Toast.LENGTH_LONG).show();
        mlist = mDatabaseHelper.getItem();
        adapter = new adapter(mlist);
        recyclerView.setAdapter(adapter);

    }

    private boolean copyDatabase(Context context)
    {
        try{

            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff))>0)
            {
                outputStream.write(buff,0,length);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}