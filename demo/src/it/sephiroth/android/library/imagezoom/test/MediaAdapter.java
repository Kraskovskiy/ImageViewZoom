package it.sephiroth.android.library.imagezoom.test;


import android.content.Context;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

public class MediaAdapter extends PagerAdapter {

    private final Context context;
    private final List<String> mediaContents;
    private final ImageViewTouchBase.OnSwipeToDismissListener listener;

    public MediaAdapter(Context context, List<String> mediaContents, ImageViewTouchBase.OnSwipeToDismissListener listener) {
        this.context = context;
        this.listener = listener;
        this.mediaContents = mediaContents;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        String mediaContent = mediaContents.get(position);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.item, collection, false);
        ImageViewTouch imageView = (ImageViewTouch) item.findViewById(R.id.image);
        imageView.setSwipeToDismissListener(listener);
        Picasso.with(context)
                .load(Uri.parse(mediaContent))
                .into(imageView);
        collection.addView(item);
        return item;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return mediaContents.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void finishUpdate(ViewGroup arg0) {
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(ViewGroup arg0) {
    }
}
