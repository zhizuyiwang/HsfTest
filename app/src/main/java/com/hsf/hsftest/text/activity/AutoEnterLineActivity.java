package com.hsf.hsftest.text.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsf.hsftest.R;

import java.util.ArrayList;

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/28 18:38
 * http://blog.csdn.net/anyfive/article/details/50507007
 *
    基本思路
    1、最外层为竖直线性布局(我们称为父布局)
    2、新建水平线性布局(我们称为行布局)
    3、计算待放入的view的宽度和行布局的剩下宽度
    4、判断是否可以放入
    (1). 若view的宽度小于等于剩余宽度，放入，到第三步；
    (2). 若view的宽度大于剩余宽度，添加行布局到父布局，到第二步。

    这里要注意几点：
    1. 子view的宽度要加上间隔；
    2. 若是子view的宽度大于行布局的宽度，不考虑对子view进入换行，直接放入；
 */

public class AutoEnterLineActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_enter_line);
        initData();
        ll_parent = (LinearLayout) findViewById(R.id.ll_parent);
        initAutoLL();
    }
    //    数据
    ArrayList<String> datas = new ArrayList<>();

    //    初始化数据
    private void initData() {
        datas.add("作 家 还 有 其 他 七 七 八 八 的 我 就 不 说 了还 有 其 他 七 七 八 八 的 我 就 不 说 了");
        datas.add("段 子 手");
        datas.add("软 文 作 者");
        datas.add("摄 影 爱 好 者");
        datas.add("画 家");
        datas.add("哦 我还很喜欢音乐");
        datas.add("还 有 其 他 七 七 八 八 的 我 就 不 说 了");
        datas.add("老 师");
    }

    //    最外层的竖直线性布局
    private LinearLayout ll_parent;

    //    绘制自动换行的线性布局
    private void initAutoLL() {
//        每一行的布局，初始化第一行布局
        LinearLayout rowLL = new LinearLayout(this);
        LinearLayout.LayoutParams rowLP =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        float rowMargin = dipToPx(10);
        rowLP.setMargins(0, (int) rowMargin, 0, 0);
        rowLL.setLayoutParams(rowLP);
        boolean isNewLayout = false;
        float maxWidth = getScreenWidth() - dipToPx(30);
//        剩下的宽度
        float elseWidth = maxWidth;
        LinearLayout.LayoutParams textViewLP =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        textViewLP.setMargins((int) dipToPx(8), 0, 0, 0);
        for (int i = 0; i < datas.size(); i++) {
//            若当前为新起的一行，先添加旧的那行
//            然后重新创建布局对象，设置参数，将isNewLayout判断重置为false
            if (isNewLayout) {
                ll_parent.addView(rowLL);
                rowLL = new LinearLayout(this);
                rowLL.setLayoutParams(rowLP);
                isNewLayout = false;
            }
//            计算是否需要换行
            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.textview, null);
            textView.setText(datas.get(i));
            textView.measure(0, 0);
//            若是一整行都放不下这个文本框，添加旧的那行，新起一行添加这个文本框
            if (maxWidth < textView.getMeasuredWidth()) {
                ll_parent.addView(rowLL);
                rowLL = new LinearLayout(this);
                rowLL.setLayoutParams(rowLP);
                rowLL.addView(textView);
                isNewLayout = true;
                continue;
            }
//            若是剩下的宽度小于文本框的宽度（放不下了）
//            添加旧的那行，新起一行，但是i要-1，因为当前的文本框还未添加
            if (elseWidth < textView.getMeasuredWidth()) {
                isNewLayout = true;
                i--;
//                重置剩余宽度
                elseWidth = maxWidth;
                continue;
            } else {
//                剩余宽度减去文本框的宽度+间隔=新的剩余宽度
                elseWidth -= textView.getMeasuredWidth() + dipToPx(8);
                if (rowLL.getChildCount() == 0) {
                    rowLL.addView(textView);
                } else {
                    textView.setLayoutParams(textViewLP);
                    rowLL.addView(textView);
                }
            }
        }
//        添加最后一行，但要防止重复添加
        ll_parent.removeView(rowLL);
        ll_parent.addView(rowLL);
    }

    //    dp转px
    private float dipToPx(int dipValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dipValue,
                this.getResources().getDisplayMetrics());
    }

    //  获得评论宽度
    private float getScreenWidth() {
        return this.getResources().getDisplayMetrics().widthPixels;
    }

}