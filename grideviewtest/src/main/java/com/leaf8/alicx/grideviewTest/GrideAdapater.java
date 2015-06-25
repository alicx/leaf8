package com.leaf8.alicx.grideviewTest;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.leaf8.alicx.ui.RecyclingImageView;

/**
 * @author 紫韶
 * @date 6/2/15
 */
public class GrideAdapater extends BaseAdapter {
    private int length = 8;

    private final Context mContext;
    private int mActionBarHeight = 0;
    private GridView.LayoutParams mImageViewLayoutParams;

    public GrideAdapater(Context context) {
        super();
        mContext = context;
        mImageViewLayoutParams = new GridView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        // Calculate ActionBar height
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(
                android.R.attr.actionBarSize, tv, true)) {
            mActionBarHeight = TypedValue.complexToDimensionPixelSize(
                    tv.data, context.getResources().getDisplayMetrics());
        }
    }

    @Override
    public int getCount() {
        return length;
    }

    @Override
    public Object getItem(int position) {
        return R.drawable.empty_photo;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        if (position < 0) {
            return null;
        }

        ImageView imageView;
        if (convertView == null) {
            imageView = new RecyclingImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(mImageViewLayoutParams);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.empty_photo));
        // Check the height matches our calculated column width
//        if (imageView.getLayoutParams().height != mItemHeight) {
//            imageView.setLayoutParams(mImageViewLayoutParams);
//        }

        return imageView;
    }
}
