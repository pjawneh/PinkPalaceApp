package io.groupone.pinkpalaceapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;


public class FeedActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize Twitter API
        TwitterConfig config = new TwitterConfig.Builder(this)
                .twitterAuthConfig(new TwitterAuthConfig(getString(R.string.CONSUMER_KEY), getString(R.string.CONSUMER_SECRET)))
                .build();
        Twitter.initialize(config);

        //Create Feed Layout
        setContentView(R.layout.tweet_layout);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView listView = findViewById(R.id.tweet_list);
        UserTimeline timeline = new UserTimeline.Builder()
                .screenName(getString(R.string.twitter_username))
                .build();
        TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(timeline)
                .build();
        listView.setAdapter(adapter);
    }
}