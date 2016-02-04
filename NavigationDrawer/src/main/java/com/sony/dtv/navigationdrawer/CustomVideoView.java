package com.sony.dtv.navigationdrawer;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.VideoView;



public class CustomVideoView extends VideoView{
    
    private static final String TAG = CustomVideoView.class.getSimpleName();
    
    private int mMaxWidth = -1;
    private int mMaxHeight = -1;

    public CustomVideoView(Context context, AttributeSet attrs,
                           int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        autoAdjustMaxSize(context);
    }
    
    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        autoAdjustMaxSize(context);
    }
    
    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        autoAdjustMaxSize(context);
    }
    
    public CustomVideoView(Context context) {
        super(context);
        autoAdjustMaxSize(context);
    }

    public void setMaxSize(int maxWidth, int maxHeight) {
        mMaxWidth = maxWidth;
        mMaxHeight = maxHeight;
    }

    public void autoAdjustMaxSize(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        
        mMaxWidth = dm.widthPixels;
        mMaxHeight = dm.heightPixels;
        Log.i(TAG, "autoAdjustMaxSize  mMaxWidth:" + mMaxWidth + " mMaxHeight:" + mMaxHeight);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        Log.i(TAG, measuredHeight + "---");
        Log.i(TAG,measuredWidth+"---");
        if (measuredWidth == 0 || measuredHeight == 0) {
            return;
        }
        if (measuredWidth == mMaxWidth || measuredHeight == mMaxHeight) {
            return;
        }
        float videoRatio = (float)measuredWidth / (float)measuredHeight;
        if (mMaxHeight * videoRatio >= mMaxWidth) {
            int targetHeight = (int) (mMaxWidth / videoRatio);
            setMeasuredDimension(mMaxWidth, targetHeight);
            Log.i(TAG, "setMeasuredDimension(" + mMaxWidth + ", " + targetHeight + ")");
        } else {
            int targetWidth = (int) (mMaxHeight * videoRatio);
            setMeasuredDimension(targetWidth, mMaxHeight);
            Log.i(TAG, "setMeasuredDimension(" + targetWidth + ", " + mMaxHeight + ")");
        }
    }

}
