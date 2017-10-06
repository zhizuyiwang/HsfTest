package com.hsf.hsftest.activity.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hsf.hsftest.R;
import com.hsf.hsftest.weight.PayPsdInputView;

/**
 *@作者 hsf
 *
 *@创建日期 2017/8/28 15:41
 * http://www.jianshu.com/p/4e9d1a9b60d7
 */

public class CustomPassWordActivity extends AppCompatActivity {
    private PayPsdInputView passwordInputView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_pass_word);
        passwordInputView = (PayPsdInputView) findViewById(R.id.password);
        passwordInputView.setComparePassword("123456", new PayPsdInputView.onPasswordListener() {
            @Override
            public void onDifference() {
                // TODO:   和上次输入的密码不一致  做相应的业务逻辑处理
                Toast.makeText(CustomPassWordActivity.this, "两次密码输入不同", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEqual(String psd) {
                // TODO:0 两次输入密码相同，那就去进行支付
                Toast.makeText(CustomPassWordActivity.this, "密码相同" + psd, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
