package com.example.chat_with_buddy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.airbnb.lottie.LottieAnimationView;
import com.example.chat_with_buddy.R;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;

    int animations[]={
            R.raw.onboard1,
            R.raw.onboard2,
            R.raw.onboard3,
            R.raw.onboard4
    };

    int headings[]={
            R.string.heading_one,
            R.string.heading_two,
            R.string.heading_three,
            R.string.heading_fourth
    };

    int description[]={
            R.string.desc_one,
            R.string.desc_two,
            R.string.desc_three,
            R.string.desc_fourth
    };

    public ViewPagerAdapter(Context context){
        this.context = context;
    }







    @Override
    public int getCount() {
        return headings.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_activity,container,false);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        LottieAnimationView slidetitleimg=(LottieAnimationView) view.findViewById(R.id.lottie);
        slidetitleimg.setAnimation(R.raw.onboard1); // Assuming your animation resource is in the "res/raw" folder.
        slidetitleimg.playAnimation();
        TextView slideHeading=(TextView) view.findViewById(R.id.title);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView slideDesc=(TextView) view.findViewById(R.id.textdesc);

        slidetitleimg.setAnimation(animations[position]);
        slideHeading.setText(headings[position]);
        slideDesc.setText(description[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view== (LinearLayout)object;
    }

}
