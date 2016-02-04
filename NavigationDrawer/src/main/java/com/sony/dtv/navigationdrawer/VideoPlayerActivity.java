package com.sony.dtv.navigationdrawer;


import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;


public class VideoPlayerActivity extends Activity {
    private static final String TAG = VideoPlayerActivity.class.getSimpleName();

    private CustomVideoView mVideoPlayer = null;

    private int mPositionWhenPaused = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "VideoPlayerActivity is Create-------------");
        setContentView(R.layout.activity_wechat_videoplayer);
        String url = getIntent().getStringExtra("url");
         url = "http://hlsglsb.wasu.tv/1453176651463_322079.m3u8?action=hls&Contentid=CP23010020160118112906";


        mVideoPlayer = (CustomVideoView) findViewById(R.id.videoview);
        MediaController controller = new MediaController(this);
        controller.setVisibility(View.INVISIBLE);
        mVideoPlayer.setMediaController(controller);
            initVideoPlayer(url);
    }

    private void initVideoPlayer(String url) {
        if (mVideoPlayer == null) {
            return;
        }
        setVideoPlayerListener();
        mVideoPlayer.setVideoURI(Uri.parse(url));
    }

    private void setVideoPlayerListener() {
        mVideoPlayer.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.i(TAG, "onPrepared");
            }
        });
        mVideoPlayer.setOnErrorListener(new OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.i(TAG, "onError"+what+"----"+extra);
                Toast.makeText(getApplicationContext(), "Video From Network load failure!",
                        Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "VideoPlayerActivity is resume-------------");
        super.onResume();
        if (mVideoPlayer != null) {
            if (mPositionWhenPaused >= 0) {
                mVideoPlayer.seekTo(mPositionWhenPaused);
            }
            mVideoPlayer.start();
        }
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "VideoPlayerActivity is pause-------------");
        super.onPause();
        if (mVideoPlayer != null) {
            mPositionWhenPaused = mVideoPlayer.getCurrentPosition();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                Log.i(TAG, "press the back button----");
                finish();
                return true;
            } else if((keyCode==KeyEvent.KEYCODE_DPAD_RIGHT)||(keyCode==KeyEvent.KEYCODE_DPAD_LEFT)||
                    (keyCode==KeyEvent.KEYCODE_DPAD_DOWN)||(keyCode==KeyEvent.KEYCODE_DPAD_UP)||(keyCode==KeyEvent.KEYCODE_DPAD_CENTER)){
                Log.i(TAG, event.getKeyCode() + "---------- this is keyCode");
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }


}
