package com.example.pongpwa;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    //레이아웃의 뷰 객체들과 연결고리 역할을 하는 inner 클래스
    public class MyViewHolder extends RecyclerView.ViewHolder {
        //ImageView myPicture;
        TextView myText;

        MyViewHolder(View view) {
            super(view);
            //myPicture = view.findViewById(R.id.imageView);
            myText = view.findViewById(R.id.textView);
            //클릭 이벤트 리스너 등록
        }
    }
    //Diary 객체들을 담을 수 있는 리스트
    private ArrayList<Diary> myDiaryList;

    public MyAdapter(ArrayList<Diary> diary) {
        this.myDiaryList = diary;
    }

    //아래는 어댑터 클래스의 기본 기능이므로 오버라이드 해야 함. 리사이클러의 각 행에 사용되는 레이아웃 xml을 가져옴.
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_view, parent, false);
        return new MyViewHolder(v);
    }
    //리싸이클러에 보여지는 이미지와 문장들을 설정함.
    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder)holder;
        myViewHolder.myText.setText(myDiaryList.get(position).getPlace());

        myViewHolder.myText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DiaryContent.class);

                intent.putExtra("place",myDiaryList.get(position).getPlace());
                intent.putExtra("note",myDiaryList.get(position).getNote());
                intent.putExtra("im",myDiaryList.get(position).getIm());
                intent.putExtra("lat",myDiaryList.get(position).getLat());
                intent.putExtra("lon",myDiaryList.get(position).getLon());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return myDiaryList.size();
    }
}
