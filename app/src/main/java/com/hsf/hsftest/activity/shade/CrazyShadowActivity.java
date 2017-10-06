package com.hsf.hsftest.activity.shade;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hitomi.cslibrary.CrazyShadow;
import com.hitomi.cslibrary.base.CrazyShadowDirection;
import com.hsf.hsftest.R;
/**
 *@作者 hsf
 *
 *@创建日期 2017/8/23 11:49
 * 第三方的阴影效果
 * https://github.com/Hitomis/CrazyShadow
 */

public class CrazyShadowActivity extends AppCompatActivity implements View.OnClickListener {


    private View titleView;

    private View drawView0, drawView1;

    private View floatView0, floatView1;

    private View wrapView0, wrapView1;

    private View shadowView0, shadowView1;

    private CrazyShadow titleCrazyShadow;

    private CrazyShadow drawCrazyShadow0, drawCrazyShadow1;

    private CrazyShadow floatCrazyShadow0, floatCrazyShadow1;

    private CrazyShadow wrapCrazyShadow0, wrapCrazyShadow1;

    private CrazyShadow shadowCrazyShadow0, shadowCrazyShadow1;

    private boolean titleFlag = true, draw0Flag = true, draw1Flag = true, float0Flag = true, float1Flag = true;

    private boolean wrap0Flag = true, wrap1Flag = true, shadow0Flag = true, shadow1Flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crazy_shadow);
        initView();
        initShadow();
        initListener();
    }

    private void initListener() {
        titleView.setOnClickListener(this);
        drawView0.setOnClickListener(this);
        drawView1.setOnClickListener(this);
        floatView0.setOnClickListener(this);
        floatView1.setOnClickListener(this);
        wrapView0.setOnClickListener(this);
        wrapView1.setOnClickListener(this);
        shadowView0.setOnClickListener(this);
        shadowView1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relay_title:
                if (titleFlag) {
                    titleCrazyShadow.hide();
                } else {
                    titleCrazyShadow.show();
                }
                titleFlag = !titleFlag;
                break;
            case R.id.relay_draw0:
                if (draw0Flag) {
                    drawCrazyShadow0.hide();
                } else {
                    drawCrazyShadow0.show();
                }
                draw0Flag = !draw0Flag;
                break;
            case R.id.relay_draw1:
                if (draw1Flag) {
                    drawCrazyShadow1.hide();
                } else {
                    drawCrazyShadow1.show();
                }
                draw1Flag = !draw1Flag;
                break;
            case R.id.relay_float0:
                if (float0Flag) {
                    floatCrazyShadow0.hide();
                } else {
                    floatCrazyShadow0.show();
                }
                float0Flag = !float0Flag;
                break;
            case R.id.relay_float1:
                if (float1Flag) {
                    floatCrazyShadow1.hide();
                } else {
                    floatCrazyShadow1.show();
                }
                float1Flag = !float1Flag;
                break;
            case R.id.relay_wrap0:
                if (wrap0Flag) {
                    wrapCrazyShadow0.hide();
                } else {
                    wrapCrazyShadow0.show();
                }
                wrap0Flag = !wrap0Flag;
                break;
            case R.id.relay_wrap1:
                if (wrap1Flag) {
                    wrapCrazyShadow1.hide();
                } else {
                    wrapCrazyShadow1.show();
                }
                wrap1Flag = !wrap1Flag;
                break;
            case R.id.relay_shadow0:
                if (shadow0Flag) {
                    shadowCrazyShadow0.hide();
                } else {
                    shadowCrazyShadow0.show();
                }
                shadow0Flag = !shadow0Flag;
                break;
            case R.id.relay_shadow1:
                if (shadow1Flag) {
                    shadowCrazyShadow1.hide();
                } else {
                    shadowCrazyShadow1.show();
                }
                shadow1Flag = !shadow1Flag;
                break;
        }
    }

    /** Attribute
     * 1、impl	以何种方式添加阴影，支持 wrap、float、drawable 三种方式
     * 2、background	修改 View 的背景色，如果使用 drawable 方式添加阴影，那么该属性必须添加
     * 3、baseShadowColor	阴影的基本颜色，即最深的颜色，与 colors 表示为同一个作用， 如果baseShadowColor 与 colors 都不设置，阴影会使用默认颜色
     * 4、colors	绘制阴影时需要的一个颜色由深到浅且长度为3的数组, 该属性与 baseShadowColor 起同一个作用，如果单单只设置 baseShadowColor 也会自动转换成为 colors
     * 5、corner	阴影顶点的内侧弧度。以适配被设置的 View 是圆角的情况， 对使用 drawable 方式设置阴影时，该属性表示为圆角矩形背景的圆角角度
     * 6、shadowRadius	阴影大小
     * 7、direction	设定阴影在 View 上显示的方位
     *
     *  Method
     * make	为 View 添加阴影效果， 使用 Builder 的 action 方法时会自动调用
     * remove	移除阴影
     * hide	隐藏阴影，与 remove 不同的是，hide 只是隐藏了 View 周围的阴影效果，并没有移除
     * show	如果调用了 hide ，可以再使用 show 将阴影效果显示出来
     */
    private void initShadow() {
        titleCrazyShadow = new CrazyShadow.Builder()
                .setContext(this)
                .setDirection(CrazyShadowDirection.BOTTOM)
                .setShadowRadius(dip2Px(5))
                .setImpl(CrazyShadow.IMPL_WRAP)
                .action(titleView);

        drawCrazyShadow0 = new CrazyShadow.Builder()
                .setContext(this)
                .setDirection(CrazyShadowDirection.ALL)
                .setShadowRadius(dip2Px(3))
                .setBaseShadowColor(Color.RED)
                .setImpl(CrazyShadow.IMPL_DRAW)
                .action(drawView0);

        drawCrazyShadow1 = new CrazyShadow.Builder()
                .setContext(this)
                .setDirection(CrazyShadowDirection.ALL)
                .setShadowRadius(dip2Px(3))
                .setCorner(dip2Px(5))
                .setBackground(Color.parseColor("#96a993"))
                .setImpl(CrazyShadow.IMPL_DRAW)
                .action(drawView1);

        floatCrazyShadow0 = new CrazyShadow.Builder()
                .setContext(this)
                .setDirection(CrazyShadowDirection.ALL)
                .setShadowRadius(dip2Px(3))
                .setImpl(CrazyShadow.IMPL_FLOAT)
                .action(floatView0);

        floatCrazyShadow1 = new CrazyShadow.Builder()
                .setContext(this)
                .setDirection(CrazyShadowDirection.ALL)
                .setShadowRadius(dip2Px(3))
                .setCorner(dip2Px(4))
                .setImpl(CrazyShadow.IMPL_FLOAT)
                .action(floatView1);

        wrapCrazyShadow0 = new CrazyShadow.Builder()
                .setContext(this)
                .setDirection(CrazyShadowDirection.ALL)
                .setShadowRadius(dip2Px(3))
                .setImpl(CrazyShadow.IMPL_WRAP)
                .action(wrapView0);

        wrapCrazyShadow1 = new CrazyShadow.Builder()
                .setContext(this)
                .setDirection(CrazyShadowDirection.ALL)
                .setShadowRadius(dip2Px(5))
                .setCorner(dip2Px(8))
                .setImpl(CrazyShadow.IMPL_WRAP)
                .action(wrapView1);

        shadowCrazyShadow0 = new CrazyShadow.Builder()
                .setContext(this)
                .setDirection(CrazyShadowDirection.ALL)
                .setShadowRadius(dip2Px(3))
                .setBackground(Color.parseColor("#a0a0a0"))
                .setBaseShadowColor(Color.parseColor("#a0a0a0"))
                .setImpl(CrazyShadow.IMPL_WRAP)
                .action(findViewById(R.id.relay_shadow0));

        shadowCrazyShadow1 = new CrazyShadow.Builder()
                .setContext(this)
                .setDirection(CrazyShadowDirection.TOP)
                .setShadowRadius(dip2Px(5))
                .setImpl(CrazyShadow.IMPL_WRAP)
                .action(findViewById(R.id.relay_shadow1));

    }

    private void initView() {
        titleView = findViewById(R.id.relay_title);
        drawView0 = findViewById(R.id.relay_draw0);
        drawView1 = findViewById(R.id.relay_draw1);
        floatView0 = findViewById(R.id.relay_float0);
        floatView1 = findViewById(R.id.relay_float1);
        wrapView0 = findViewById(R.id.relay_wrap0);
        wrapView1 = findViewById(R.id.relay_wrap1);
        shadowView0 = findViewById(R.id.relay_shadow0);
        shadowView1 = findViewById(R.id.relay_shadow1);
    }


    public int dip2Px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
