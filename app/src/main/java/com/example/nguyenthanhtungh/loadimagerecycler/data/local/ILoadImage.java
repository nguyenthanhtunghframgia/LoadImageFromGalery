package com.example.nguyenthanhtungh.loadimagerecycler.data.local;

import com.example.nguyenthanhtungh.loadimagerecycler.data.model.Image;

import java.util.ArrayList;

public interface ILoadImage {
    void onLoadImageSuccess(ArrayList<Image> images);

    void onImageNotAvailable();
}
