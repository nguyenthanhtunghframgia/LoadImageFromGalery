package com.example.nguyenthanhtungh.loadimagerecycler.data.local;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.MediaStore;

import com.example.nguyenthanhtungh.loadimagerecycler.data.model.Image;

import java.util.ArrayList;

public class ImageManager {
    private Context context;
    private ILoadImage iLoadImage;

    public ImageManager(Context context) {
        this.context = context;
    }

    public void setILoadImage(ILoadImage iLoadImage) {
        this.iLoadImage = iLoadImage;
    }

    public void getImage() {
        LoadImageAsync imageAsync = new LoadImageAsync();
        imageAsync.execute();
    }

    public class LoadImageAsync extends AsyncTask<Void, Void, ArrayList<Image>> {

        @Override
        protected ArrayList<Image> doInBackground(Void... voids) {
            ArrayList<Image> mListImages = new ArrayList<>();
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    projection, null, null, null);
            int indexColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            while (cursor.moveToNext()) {
                String path = cursor.getString(indexColumn);
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                mListImages.add(new Image(bitmap));
            }
            cursor.close();
            return mListImages;
        }

        @Override
        protected void onPostExecute(ArrayList<Image> images) {
            super.onPostExecute(images);
            if (images == null) {
                iLoadImage.onImageNotAvailable();
            } else {
                iLoadImage.onLoadImageSuccess(images);
            }
        }
    }
}
