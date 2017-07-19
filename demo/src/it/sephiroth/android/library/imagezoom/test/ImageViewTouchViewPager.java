package it.sephiroth.android.library.imagezoom.test;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;

public class ImageViewTouchViewPager extends ViewPager {

    private static final String TAG = "ImageViewTouchViewPager";
    public static final String VIEW_PAGER_OBJECT_TAG = "image#";

    private int previousPosition;

    private OnPageSelectedListener onPageSelectedListener;

    public ImageViewTouchViewPager(Context context) {
        super(context);
        init();
    }

    public ImageViewTouchViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setOnPageSelectedListener(OnPageSelectedListener listener) {
        onPageSelectedListener = listener;
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        boolean canScroll;
        if (v instanceof ImageViewTouch) {
            canScroll = ((ImageViewTouch) v).canScroll(dx);
        } else {
            canScroll = super.canScroll(v, checkV, dx, x, y);
        }
        return canScroll;
    }

    public interface OnPageSelectedListener {
        void onPageSelected(int position);
    }

    private void init() {
        previousPosition = getCurrentItem();

        addOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (onPageSelectedListener != null) onPageSelectedListener.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == SCROLL_STATE_SETTLING && previousPosition != getCurrentItem()) {
                    try {
                        ImageViewTouch imageViewTouch = (ImageViewTouch)
                                findViewWithTag(VIEW_PAGER_OBJECT_TAG + getCurrentItem());
                        if (imageViewTouch != null) imageViewTouch.zoomTo(1f, 300);

                        previousPosition = getCurrentItem();
                    } catch (ClassCastException ex) {
                        Log.e(TAG, "This view pager should have only ImageViewTouch as a children.", ex);
                    }
                }
            }
        });
    }
}