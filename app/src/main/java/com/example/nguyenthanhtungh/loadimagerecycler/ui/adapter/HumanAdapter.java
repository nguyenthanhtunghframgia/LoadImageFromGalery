package com.example.nguyenthanhtungh.loadimagerecycler.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nguyenthanhtungh.loadimagerecycler.R;
import com.example.nguyenthanhtungh.loadimagerecycler.data.model.Human;

import java.util.ArrayList;

public class HumanAdapter extends RecyclerView.Adapter<HumanAdapter.HumanHolder> {
    private ArrayList<Human> mListHumans;

    public HumanAdapter(ArrayList<Human> listHumans) {
        mListHumans = listHumans;
    }

    @NonNull
    @Override
    public HumanHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_human_items, viewGroup, false);
        return new HumanHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HumanHolder heroHolder, int i) {
        heroHolder.bindData(mListHumans.get(i));
    }

    @Override
    public int getItemCount() {
        return mListHumans.size() != 0 ? mListHumans.size() : 0;
    }

    public class HumanHolder extends RecyclerView.ViewHolder {
        private ImageView mImageHuman;

        private HumanHolder(@NonNull View itemView) {
            super(itemView);
            mImageHuman = itemView.findViewById(R.id.image_human);
        }

        private void bindData(Human human) {
            mImageHuman.setImageBitmap(human.getImageBitmap());
        }
    }
}

