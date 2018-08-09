package com.example.nguyenthanhtungh.loadimagerecycler.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nguyenthanhtungh.loadimagerecycler.R;
import com.example.nguyenthanhtungh.loadimagerecycler.data.model.Image;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.HumanHolder> {
    private ArrayList<Image> mListImages;

    public ImageAdapter(ArrayList<Image> listImages) {
        mListImages = listImages;
    }

    @NonNull
    @Override
    public HumanHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_image_items, viewGroup, false);
        return new HumanHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HumanHolder heroHolder, int i) {
        heroHolder.bindData(mListImages.get(i));
    }

    @Override
    public int getItemCount() {
        return mListImages.size() != 0 ? mListImages.size() : 0;
    }

    public class HumanHolder extends RecyclerView.ViewHolder {
        private ImageView mImage;

        private HumanHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image_human);
        }

        private void bindData(Image image) {
            mImage.setImageBitmap(image.getImageBitmap());
        }
    }
}

