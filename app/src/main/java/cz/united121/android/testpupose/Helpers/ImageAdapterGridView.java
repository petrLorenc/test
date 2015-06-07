package cz.united121.android.testpupose.Helpers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import cz.united121.android.testpupose.R;

/**
 * Created by United121 on 27.5.2015.
 */
public class ImageAdapterGridView extends BaseAdapter {

    private Context m_context;
    private final int m_animation_ID;

    public  ImageAdapterGridView(Context context, final int animation_ID){
        this.m_context = context;
        m_animation_ID = animation_ID;
    }

    @Override
    public int getCount() {
        return m_images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(m_context);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setPadding(8,8,8,8);
        }else{
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(m_images[position]);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                v.startAnimation(AnimationUtils.loadAnimation(m_context,m_animation_ID));
//            }
//        });
        return imageView;
    }

    private Integer [] m_images = {
            R.drawable.ic_launcher,R.drawable.ic_launcher,
            R.drawable.ic_launcher,R.drawable.ic_launcher,
            R.drawable.ic_launcher,R.drawable.ic_launcher
    };
}
