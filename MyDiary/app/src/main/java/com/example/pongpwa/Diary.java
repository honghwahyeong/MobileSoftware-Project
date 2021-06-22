package com.example.pongpwa;

import android.graphics.Bitmap;
import android.provider.MediaStore;

public class Diary {
    //여행 장소
    String place;
    //이미지 아이디
    int image_id;
    //여행 한줄평
    String note;
    //여행사진
    String im;
    //위도
    String lat;
    //경도
    String lon;

    public Diary(String place, String note, String im, String lat, String lon) {
        //this.image_id = id;
        this.place = place;
        this.note = note;
        this.im = im;
        this.lat = lat;
        this.lon = lon;
    }

    public String getPlace() {
        return place;
    }

    public int getImage_id() {
        return image_id;
    }

    public String getNote() {
        return note;
    }

    public String getIm() { return im; }

    public void setIm(String im) {
        this.im = im;
    }

//    public void setPlaceimage(Bitmap placeimage) {
//        this.placeimage = placeimage;
//    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }
}
