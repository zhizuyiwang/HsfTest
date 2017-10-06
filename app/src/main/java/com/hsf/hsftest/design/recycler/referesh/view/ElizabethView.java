package com.hsf.hsftest.design.recycler.referesh.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hsf.hsftest.R;


/**
 * Created by dafuShao on 2016/9/9 0009.
 *
 */
public class ElizabethView extends FrameLayout {

    private ImageView imageView;
    private  AnimationDrawable animationDrawable;

    public ElizabethView(Context context) {
        super(context);
        initView(context);
    }
    public ElizabethView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }
    public ElizabethView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    private void initView(Context context){
        View view= LayoutInflater.from(context).inflate(R.layout.elizabeth_item,null);
        imageView=(ImageView) view.findViewById(R.id.elizabeth_im);
        animationDrawable= (AnimationDrawable) imageView.getBackground();
        addView(view);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    //开始动画
    public void startAnim(){
        animationDrawable.start();
    }
    //停止动画
    public void stopAnim(){
        animationDrawable.stop();
    }
}
