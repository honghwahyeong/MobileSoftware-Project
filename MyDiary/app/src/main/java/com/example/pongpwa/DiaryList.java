package com.example.pongpwa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class DiaryList extends AppCompatActivity {


    private RecyclerView diaryRecyclerView;
    private RecyclerView.LayoutManager diaryLayoutManager;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);

        //리사이클러 뷰에 레이아웃 설정, 이 레이아웃은 xml로 만들어 준 내용을 사용
        diaryRecyclerView = (RecyclerView)findViewById(R.id.diary_RecyclerView);
        diaryRecyclerView.setHasFixedSize(true);
        diaryLayoutManager = new LinearLayoutManager(this);
        diaryRecyclerView.setLayoutManager(diaryLayoutManager);

        //다이어리들을 목록으로 만들어준다. 목록은 ArrayList 객체를 사용한다.
        ArrayList<Diary> diaryInfo = new ArrayList<>();

        String[] columns = new String[]{"_id", "place", "note", "im", "lat", "lon"};

        Cursor c = getContentResolver().query(MyContentProvider.CONTENT_URI, columns, null, null, null, null);
        if(c != null) {
            while(c.moveToNext()) {
                int id = c.getInt(0);
                String place = c.getString(1);
                String note = c.getString(2);
                String im = c.getString(3);
                String lat = c.getString(4);
                String lon = c.getString(5);
                diaryInfo.add(new Diary(place,note,im,lat,lon));
            }
            c.close();
        }
        //목록을 어댑터에 연결
        MyAdapter myAdapter = new MyAdapter(diaryInfo);
        //어댑터를 뷰에 연결
        diaryRecyclerView.setAdapter(myAdapter);
        //myAdapter.notifyDataSetChanged();
    }

}
///제주도 33.49977,126.53127
///부산 35.17977,129.07501
///서울 37.56666,126.97846
///대구 35.87141, 128.60179
///강릉 37.75185, 128.87607