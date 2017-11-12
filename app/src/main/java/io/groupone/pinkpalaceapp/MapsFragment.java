package io.groupone.pinkpalaceapp;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by pjawneh on 11/5/2017.
 */

public class MapsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        MapsFragment.ContentAdapter adapter = new MapsFragment.ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mapImg;

        private ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_maps, parent, false));
            mapImg = itemView.findViewById(R.id.map_img);
        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<MapsFragment.ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 3;
        private final Drawable[] mMapImg;


        private ContentAdapter(Context context) {
            Resources rsc = context.getResources();
            TypedArray a = rsc.obtainTypedArray(R.array.map_picture);
            mMapImg = new Drawable[a.length()];
            for (int i =0; i < mMapImg.length; i++){
                mMapImg[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @Override
        public MapsFragment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MapsFragment.ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(MapsFragment.ViewHolder holder, int position) {
            holder.mapImg.setImageDrawable(mMapImg[position % mMapImg.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}