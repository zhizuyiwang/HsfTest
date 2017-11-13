package com.hsf.hsftest.image.scrollview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hsf.hsftest.R;
import com.hsf.hsftest.image.scrollview.customscrollview.FlexibleListView;

public class ZoomInScrollViewActivity7 extends AppCompatActivity {
    private FlexibleListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_in_scroll_view7);
        initGlobalParams();
    }

    private void initGlobalParams() {
        mListView = (FlexibleListView) findViewById(R.id.flexible_list_view);
        View mFlexibleHeaderView = LayoutInflater.from(this).inflate(R.layout.flexible_header_layout, mListView, false);
        AbsListView.LayoutParams params = (AbsListView.LayoutParams)mFlexibleHeaderView.getLayoutParams();
        if(null == params) {
            params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);
        }
        params.height = getResources().getDimensionPixelSize(R.dimen.header_height);
        mFlexibleHeaderView.setLayoutParams(params);

        final View actionBar = findViewById(R.id.custom_action_bar);

        mListView.bindActionBar(actionBar);
        mListView.addHeaderView(mFlexibleHeaderView);

        mListView.setAdapter(new Adapter());

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    static class Adapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 80;
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
            TextView textView = new TextView(parent.getContext());
            textView.setPadding(50, 50, 50, 50);
            textView.setText(position + 10 + "");
            return textView;
        }
    }
}
