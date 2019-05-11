package com.example.tangr.quiz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomSwipeAdapter extends PagerAdapter {

    private int[] image_resources = {R.drawable.checklist,R.drawable.mcq,R.drawable.tfive,R.drawable.marks,R.drawable.calm};
    private Context ctx;
    private LayoutInflater layoutInflater;



    public CustomSwipeAdapter(Context ctx){
        this.ctx = ctx;
    }
    @Override
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {

        return (view==(LinearLayout)o);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView = (ImageView) item.findViewById(R.id.im1);
        TextView textView =(TextView) item.findViewById(R.id.imcount);
        imageView.setImageResource(image_resources[position]);
        switch (position){
            case 0:
                textView.setText("All Questions are Compulsary !");
                break;
            case 1:
                textView.setText("All questions are MCQ Type\n4 options given and only 1 is correct !");
                break;
            case 2:
                textView.setText("There are 25 Questions in this quiz.");
                break;
            case 3:
                textView.setText("Each correct answer gives you 0.5 marks.");
                break;
//            case 4:
//                textView.setText("Each question will be given 30 seconds only.");
//                break;
            case 4:
                textView.setText("The questions will test your basics , so don't worry about the difficulty level.");
                break;
        }
        container.addView(item);

        return item;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);

    }
}
