package com.example.nguyenthanhtungh.loadimagerecycler.data.model;


import android.graphics.Bitmap;

public class Image {
    private Bitmap mImageBitmap;

    public Image(Bitmap imageBitmap) {
        mImageBitmap = imageBitmap;
    }

    public Bitmap getImageBitmap() {
        return mImageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        mImageBitmap = imageBitmap;
    }
}
