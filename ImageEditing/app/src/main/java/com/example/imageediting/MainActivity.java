package com.example.imageediting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Drawable drawable;
    Button galleryBtn,selfieBtn;
    boolean whichBtn=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.image);
        drawable=getResources().getDrawable(R.drawable.img);
        imageView.setImageDrawable(drawable);
        galleryBtn = findViewById(R.id.gallery);
        selfieBtn = findViewById(R.id.take_selfie);

        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whichBtn = true;
                checkPermission();
            }
        });
        selfieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whichBtn = false;
                checkPermission();

            }
        });
    }

    private void openCamera() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //i.putExtra("android.intent.extras.CAMERA_FACING", android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT);
        i.putExtra("android.intent.extras.CAMERA_FACING", 1);
        startActivityForResult(i,200);
    }

    private void checkPermission() {
        int permission = ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            if(whichBtn){
                pickImage();
            }
            else {
                openCamera();
            }
        }
        else {
            if(permission != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        100);
            }
            else {
                if(whichBtn){
                    pickImage();
                }
                else {
                    openCamera();
                }
            }
        }
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,100);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100 && grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if(whichBtn){
                pickImage();
            }
            else {
                openCamera();
            }
        }
        else  {
            Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            if(data.getData() != null ){
                Uri uri = data.getData();
                Intent i = new Intent(MainActivity.this, DsPhotoEditorActivity.class);
                    i.setData(uri);
                    i.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY,"Images");

                    i.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE,
                            new int[]{DsPhotoEditorActivity.TOOL_WARMTH,
                            DsPhotoEditorActivity.TOOL_PIXELATE});
                    startActivityForResult(i,101);
            }
        }
        if (requestCode == 101){
            imageView.setImageURI(data.getData());
        }
        if(requestCode == 200){
            Bitmap bitmap=(Bitmap) data.getExtras().get("data");
            Uri tempuri = getImageUri(getApplicationContext(),bitmap);
            Intent i = new Intent(MainActivity.this, DsPhotoEditorActivity.class);
            i.setData(tempuri);
            i.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY,"Images");

            i.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE,
                    new int[]{DsPhotoEditorActivity.TOOL_WARMTH,
                            DsPhotoEditorActivity.TOOL_PIXELATE});
            startActivityForResult(i,201);
            //imageView.setImageURI(tempuri);
        }
        if (requestCode == 201){
            imageView.setImageURI(data.getData());
        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}