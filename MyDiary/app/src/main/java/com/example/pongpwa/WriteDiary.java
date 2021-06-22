package com.example.pongpwa;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;

public class WriteDiary extends AppCompatActivity {

    private final int GET_GALLERY_IMAGE = 200;
    private ImageView im_place;
    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);

        im_place = (ImageView)findViewById(R.id.im_place);
        im_place.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                startActivityForResult(intent,GET_GALLERY_IMAGE);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try{
                selectedImageUri = data.getData();
                im_place.setImageURI(selectedImageUri);
                getContentResolver().takePersistableUriPermission(selectedImageUri,Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
 ///////////////////////////////////////////////////////////////////////////////////////////////////

    public void addDiary(View view) {
        ContentValues addValues = new ContentValues();
        addValues.put(MyContentProvider.PLACE,
                ((EditText)findViewById(R.id.et_Place)).getText().toString());
        addValues.put(MyContentProvider.NOTE,
                ((EditText)findViewById(R.id.et_Note)).getText().toString());
        addValues.put(MyContentProvider.IM,
                selectedImageUri.toString());
        addValues.put(MyContentProvider.LAT,
                ((EditText)findViewById(R.id.et_Lat)).getText().toString());
        addValues.put(MyContentProvider.LON,
                ((EditText)findViewById(R.id.et_Lon)).getText().toString());

        getContentResolver().insert(MyContentProvider.CONTENT_URI, addValues);

        Toast.makeText(getBaseContext(), "저장되었습니다.", Toast.LENGTH_LONG).show();
        ((EditText)findViewById(R.id.et_Place)).setText("");
        ((EditText)findViewById(R.id.et_Note)).setText("");
        ((ImageView)findViewById(R.id.im_place)).setImageResource(R.drawable.androidimage);
        ((EditText)findViewById(R.id.et_Lat)).setText("");
        ((EditText)findViewById(R.id.et_Lon)).setText("");
    }
}