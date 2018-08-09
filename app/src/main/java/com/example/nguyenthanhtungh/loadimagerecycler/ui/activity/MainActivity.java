package com.example.nguyenthanhtungh.loadimagerecycler.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.nguyenthanhtungh.loadimagerecycler.R;
import com.example.nguyenthanhtungh.loadimagerecycler.data.local.ILoadImage;
import com.example.nguyenthanhtungh.loadimagerecycler.data.local.ImageManager;
import com.example.nguyenthanhtungh.loadimagerecycler.data.model.Image;
import com.example.nguyenthanhtungh.loadimagerecycler.ui.adapter.ImageAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ILoadImage {
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPermission();
    }

    private void initPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        } else {
            doLoadImageAsync();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doLoadImageAsync();
                }
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void doLoadImageAsync() {
        ImageManager imageManager = new ImageManager(getApplicationContext());
        imageManager.setILoadImage(this);
        imageManager.getImage();
    }

    @Override
    public void onLoadImageSuccess(ArrayList<Image> images) {
        ImageAdapter imageAdapter = new ImageAdapter(images);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(imageAdapter);
    }

    @Override
    public void onImageNotAvailable() {
        Toast.makeText(this, getApplicationContext()
                .getResources().getString(R.string.image_not_available), Toast.LENGTH_LONG).show();
    }
}
