package com.example.sqlitedemo2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    private adapter mAdapter;
    private EditText mEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
        mDatabase = helper.getWritableDatabase();
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mAdapter = new adapter(MainActivity.this,MainActivity.this,getAllItems());
        recyclerView.setAdapter(mAdapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove( RecyclerView recyclerView,  RecyclerView.ViewHolder viewHolder,  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped( RecyclerView.ViewHolder viewHolder, int direction) {
                    removeItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);
        mEditText = (EditText)findViewById(R.id.edittext);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFruit();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            recreate();
        }
    }

    private void addFruit()
    {
        if(mEditText.getText().toString().trim().length() == 0 )
        {
            return;
        }
        String name = mEditText.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(fruitlist.fruitadd.COLUMN_NAME,name);
        mDatabase.insert(fruitlist.fruitadd.TABLE_NAME,null,cv);
        mAdapter.swapCursor(getAllItems());
        mEditText.getText().clear();
    }

    private void removeItem(long id)
    {
        mDatabase.delete(fruitlist.fruitadd.TABLE_NAME,fruitlist.fruitadd._ID + "=" + id,null);
        mAdapter.swapCursor(getAllItems());
    }
    private Cursor getAllItems()
    {
        return mDatabase.query(
                fruitlist.fruitadd.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                fruitlist.fruitadd.COLUMN_NAME
        );
    }
}