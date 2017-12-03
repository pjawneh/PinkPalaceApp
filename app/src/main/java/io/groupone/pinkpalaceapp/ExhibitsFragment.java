package io.groupone.pinkpalaceapp;

import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;

/**
 * Created by pjawneh on 11/5/2017.
 */

public class ExhibitsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView   exhibitImg;
        private TextView    exhibitTitle;

        private ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.exhibits_list, parent, false));
            exhibitImg = itemView.findViewById(R.id.list_img);
            exhibitTitle = itemView.findViewById(R.id.tweet_content);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ExhibitDetailView.class);
                    intent.putExtra(ExhibitDetailView.EXTRA_POSITION,getAdapterPosition());
                    context.startActivity(intent);
                }
            });
        }
    }

    /**
     * Adapter to display recycler view per instructions
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 5;
        private final String[] mExhibits;
        private final Drawable[] mExhibitImg;

        private ContentAdapter(Context context) {
            Resources rsc = context.getResources();
            mExhibits = rsc.getStringArray(R.array.exhibits);
            TypedArray a = rsc.obtainTypedArray(R.array.exhibit_picture);
            mExhibitImg = new Drawable[a.length()];
                for (int i =0; i < mExhibitImg.length; i++){
                    mExhibitImg[i] = a.getDrawable(i);
                }
                a.recycle();
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.exhibitImg.setImageDrawable(mExhibitImg[position % mExhibitImg.length]);
            holder.exhibitTitle.setText(mExhibits[position % mExhibits.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}
