package io.groupone.pinkpalaceapp;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class ExhibitDetailView extends AppCompatActivity {
    
    public static final String EXTRA_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exhibit_detail_view);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toolbar toolbar = findViewById(R.id.toolbar);


        int position = getIntent().getIntExtra(EXTRA_POSITION,0);
        Resources resources = getResources();
        String[] exhibits = resources.getStringArray(R.array.exhibits);
        toolbar.setTitle(exhibits[position % exhibits.length]);

        TypedArray mExhibitImg = resources.obtainTypedArray(R.array.exhibit_picture);
        ImageView exhibitPicture = findViewById(R.id.detail_img);
        exhibitPicture.setImageDrawable(mExhibitImg.getDrawable(position % mExhibitImg.length()));

        String[] exhibitDetails = resources.getStringArray(R.array.exhibit_details);
        TextView exhibitDetail = findViewById(R.id.detail_desc);
        exhibitDetail.setText(exhibitDetails[position % exhibitDetails.length]);

        mExhibitImg.recycle();
    }
}
