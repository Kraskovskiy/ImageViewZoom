package it.sephiroth.android.library.imagezoom.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

public class ImageViewTestActivity extends Activity {

    private static final String LOG_TAG = "image-test";

    Button mButton1;
    Button mButton2;
    CheckBox mCheckBox;
    ImageViewTouchViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        Toast.makeText(this, "ImageViewTouch.VERSION: " + ImageViewTouch.VERSION, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        viewPager = (ImageViewTouchViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MediaAdapter(this, new ArrayList<String>() {{
            add("http://www.wowhdbackgrounds.com/wp-content/uploads/2017/05/Space-HD-Desktop-Wallpapers-3-whb.jpg");
            add("http://www.hdesktops.com/wp-content/uploads/2014/02/space-wall-paper-3.jpg");
            add("http://free4kwallpaper.com/wp-content/uploads/2016/01/Colored-Lights-Space-4K-Wallpaper.jpg");
            add("https://pic.xenomorph.ru/2011-09/1315673265_34.jpg");
        }}, new ImageViewTouchBase.OnSwipeToDismissListener() {
            @Override
            public void onDistanceChanged(float dY) {
                Log.w(ImageViewTestActivity.class.getSimpleName(), "onDistanceChanged: " + dY);
            }

            @Override
            public void onDismiss() {
                Log.w(ImageViewTestActivity.class.getSimpleName(), "onDismiss");
                finish();
            }
        }));

//        // set the default image display type
//        mImage.setDisplayType(DisplayType.FIT_IF_BIGGER);

        mButton1 = (Button) findViewById(R.id.button);
        mButton2 = (Button) findViewById(R.id.button2);
        mCheckBox = (CheckBox) findViewById(R.id.checkbox1);
//
//        mButton1.setOnClickListener(
//            new OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    selectRandomImage(mCheckBox.isChecked());
//                }
//            }
//        );

//        mButton2.setOnClickListener(
//            new OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//
//                    int current = mImage.getDisplayType().ordinal() + 1;
//                    if (current >= DisplayType.values().length) {
//                        current = 0;
//                    }
//
//                    mImage.setDisplayType(DisplayType.values()[current]);
//                }
//            }
//        );
//
//        mImage.setSingleTapListener(
//            new OnImageViewTouchSingleTapListener() {
//
//                @Override
//                public void onSingleTapConfirmed() {
//                    Log.d(LOG_TAG, "onSingleTapConfirmed");
//                }
//            }
//        );
//
//        mImage.setDoubleTapListener(
//            new OnImageViewTouchDoubleTapListener() {
//
//                @Override
//                public void onDoubleTap() {
//                    Log.d(LOG_TAG, "onDoubleTap");
//                }
//            }
//        );
//
//        mImage.setOnDrawableChangedListener(
//            new OnDrawableChangeListener() {
//
//                @Override
//                public void onDrawableChanged(Drawable drawable) {
//                    Log.i(LOG_TAG, "onBitmapChanged: " + drawable);
//                }
//            }
//        );
    }
}
