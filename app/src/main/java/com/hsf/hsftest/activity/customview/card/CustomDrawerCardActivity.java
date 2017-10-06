package com.hsf.hsftest.activity.customview.card;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hsf.hsftest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomDrawerCardActivity extends AppCompatActivity {

    @BindView(R.id.tv_card1)
    TextView tvCard1;
    @BindView(R.id.tv_card2)
    TextView tvCard2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_drawer_card);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_card1, R.id.tv_card2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_card1:
                startActivity(new Intent(this,CustomDrawerCardActivity1.class));
                break;
            case R.id.tv_card2:
                startActivity(new Intent(this,CustomDrawerCardActivity2.class));
                break;
        }
    }
}
