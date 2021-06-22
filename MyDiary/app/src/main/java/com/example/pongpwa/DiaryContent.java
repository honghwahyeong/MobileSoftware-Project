package com.example.pongpwa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.PrimitiveIterator;

public class DiaryContent extends AppCompatActivity {

    TextView tv_content_place;
    TextView tv_content_note;
    ImageView im_place;
    String imageUri;
    String myPermission = Manifest.permission.READ_EXTERNAL_STORAGE;
    private static final int REQUEST_CODE_PERMISSION = 2;
    double lat;
    double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_content);

        //////////// 권한 확인하기/////////////
        try{
            if (ActivityCompat.checkSelfPermission(this,myPermission)
            != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{myPermission},
                        REQUEST_CODE_PERMISSION);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        ///////////////////////////////////

        Intent intent = getIntent();

        tv_content_place = (TextView)findViewById(R.id.tv_content_place);
        tv_content_note = (TextView)findViewById(R.id.tv_content_note);
        im_place = (ImageView)findViewById(R.id.im_place);

        tv_content_place.setText(intent.getStringExtra("place"));
        tv_content_note.setText(intent.getStringExtra("note"));
        imageUri = intent.getStringExtra("im");
        setImage(Uri.parse(imageUri));

        lat = Double.parseDouble(intent.getStringExtra("lat"));
        lon = Double.parseDouble(intent.getStringExtra("lon"));

        im_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiaryContent.this, MapView.class);
                intent.putExtra("location",tv_content_place.getText());
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                startActivity(intent);
            }
        });
    }

    private void setImage(Uri uri) {
        try{
            InputStream in = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            im_place.setImageBitmap(bitmap);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}