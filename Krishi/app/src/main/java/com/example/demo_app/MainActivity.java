package com.example.demo_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText email;
    private Button camera;
    private Button button,button2;
    private TextView d_name;
    private TextView e_name;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String NAME = "name";
    public static final String EMAIL = "email";

    private String name1;
    private String email1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        camera = findViewById(R.id.camera);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        d_name = findViewById(R.id.d_name);
        e_name = findViewById(R.id.d_email);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(emailValidator(email));
                {
                    d_name.setText(name.getText().toString());
                    e_name.setText(email.getText().toString());
                    saveData();
                }

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,RetrofitActivity.class);
                startActivity(i);
            }
        });
        loadData();
        updateViews();
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(openCamera);
            }
        });
    }

    public void saveData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NAME,name.getText().toString());
        editor.putString(EMAIL,email.getText().toString());
        editor.apply();
    }
    public void loadData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        name1 = sharedPreferences.getString(NAME,"");
        email1 = sharedPreferences.getString(EMAIL,"");
    }
    public void updateViews()
    {
        d_name.setText(name1);
        e_name.setText(email1);
    }
    public boolean emailValidator(EditText etMail) {
        String emailToText = etMail.getText().toString();


        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            Toast.makeText(this, "Email Verified !", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}