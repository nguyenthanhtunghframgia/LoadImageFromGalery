package com.example.nguyenthanhtungh.loadimagerecycler.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nguyenthanhtungh.loadimagerecycler.R;
import com.example.nguyenthanhtungh.loadimagerecycler.data.model.Human;
import com.example.nguyenthanhtungh.loadimagerecycler.ui.adapter.HumanAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    private ArrayList<Human> mListHumans;

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
            initData();
            initViews();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initData();
                    initViews();
                }
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void initViews() {
        HumanAdapter humanAdapter = new HumanAdapter(mListHumans);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(humanAdapter);
    }

    private void initData() {
        mListHumans = new ArrayList<>();
        String[] projection = {MediaStore.Images.Thumbnails.DATA};
        Cursor cursor = this.getContentResolver().query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                projection, null, null, null);
        int indexColumn = cursor.getColumnIndex(MediaStore.Images.Thumbnails.DATA);
        while (cursor.moveToNext()) {
            String path = cursor.getString(indexColumn);
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            mListHumans.add(new Human(bitmap));
        }
        cursor.close();
    }
}
