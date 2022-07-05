package com.example.sqlitedemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateItem extends AppCompatActivity {
    EditText editText;
    Button button;
    int id;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);
        editText = findViewById(R.id.edittext2);
        button = findViewById(R.id.button2);
        getData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp_name = editText.getText().toString().trim();
                DatabaseHelper databaseHelper = new DatabaseHelper(UpdateItem.this);
                databaseHelper.updateData(id,temp_name);
                Intent i = new Intent(UpdateItem.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
    void getData()
    {
        String id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        this.id = Integer.parseInt(id);
        editText.setText(name);

    }
}