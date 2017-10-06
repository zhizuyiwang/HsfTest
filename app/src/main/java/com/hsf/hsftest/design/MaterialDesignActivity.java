package com.hsf.hsftest.design;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hsf.hsftest.R;
import com.hsf.hsftest.design.recycler.RecyclerViewControlActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MaterialDesignActivity extends AppCompatActivity {


    @BindView(R.id.tv_recycler_view)
    TextView tvRecyclerView;
    @BindView(R.id.tv_recycler1_view)
    TextView tvRecycler1View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meterial_design);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.tv_recycler_view, R.id.tv_recycler1_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_recycler_view:
                startActivity(new Intent(this, RecyclerViewControlActivity.class));
                break;
            case R.id.tv_recycler1_view:
                break;
        }
    }
}
