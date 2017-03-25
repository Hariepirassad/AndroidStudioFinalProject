package fr.esilv.s8.project.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.Gson;

import java.io.Serializable;
import java.security.Provider;

import fr.esilv.s8.project.Constants;
import fr.esilv.s8.project.R;
import fr.esilv.s8.project.models.Infos;
import fr.esilv.s8.project.models.Video;

public class PlayVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static final String URL = "http://www.youtube.com/watch?v=";
    private static final String INFOS = "";
    private Infos infos;
    private String videoId;

    private TextView titleView;
    private TextView authorView;

    private static final String videoStatURL = "https://www.googleapis.com/youtube/v3/videos?part=statistics&id=";
    private String nbLikes;
    private String nbDisLikes;
    private String nbViews;
    private TextView likesView;
    private TextView disLikesView;
    private TextView nbViewsView;


    public static void start(Context context, Infos infos) {
        Intent intent = new Intent(context, PlayVideoActivity.class);
        intent.putExtra(INFOS, infos);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        infos = (Infos) getIntent().getSerializableExtra(INFOS);

        videoId = infos.getId().getVideoId();

        getVideoStats();

        titleView = (TextView) findViewById(R.id.titleView);
        titleView.setText(infos.getSnippet().getTitle());
        authorView = (TextView) findViewById(R.id.authorView);
        authorView.setText(infos.getSnippet().getChannelTitle());

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(URL + videoId, this);
    }

    private void getVideoStats() {
        StringRequest itemsRequest = new StringRequest(videoStatURL + videoId + "&key=" + Constants.API_KEY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Video videoInfos = new Gson().fromJson(response, Video.class);
                nbLikes = videoInfos.getVideoItems().get(0).getStatistics().getLikeCount();
                nbDisLikes = videoInfos.getVideoItems().get(0).getStatistics().getDislikeCount();
                nbViews = videoInfos.getVideoItems().get(0).getStatistics().getViewCount();

                likesView = (TextView) findViewById(R.id.likesView);
                likesView.setText("Likes : " + nbLikes);
                disLikesView = (TextView) findViewById(R.id.disLikesView);
                disLikesView.setText("Dislikes : " + nbDisLikes);
                nbViewsView = (TextView) findViewById(R.id.viewsView);
                nbViewsView.setText("Views : " + nbViews);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Videos", "Error");
            }
        });

        Volley.newRequestQueue(this).add(itemsRequest);
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);

        if(!b){
            youTubePlayer.cueVideo(videoId);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Failured to Initilize!",Toast.LENGTH_LONG).show();

    }

}
