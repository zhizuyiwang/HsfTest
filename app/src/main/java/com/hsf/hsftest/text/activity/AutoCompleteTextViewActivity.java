package com.hsf.hsftest.text.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.hsf.hsftest.R;

public class AutoCompleteTextViewActivity extends AppCompatActivity {
    private Button searchbtn;
    private ArrayAdapter<String> arr_adapter;
    private AutoCompleteTextView auto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_text_view);

        // 初始化
        auto = (AutoCompleteTextView) findViewById(R.id.auto);
        searchbtn = (Button) findViewById(R.id.search);
        initAutoViewContent();
        auto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                AutoCompleteTextView view = (AutoCompleteTextView) v;
                if (hasFocus) {
                    view.showDropDown();
                }
            }
        });
        auto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = auto.getText().toString().trim();
                if(text.length()==0){
                    initAutoViewContent();
                    auto.showDropDown();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // 设置监听事件，点击搜索写入搜索词
        searchbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = auto.getText().toString();
                if(!TextUtils.isEmpty(text)){
                    save();
                }
            }
        });
    }
    private void initAutoViewContent() {
        SharedPreferences sp = getSharedPreferences("search_history", 0);
        String history = sp.getString("history", "");
        // 用逗号分割内容返回数组
        String[] history_arr = history.split(",");
        // 保留前50条数据
        if(TextUtils.isEmpty(history)){
            history_arr = new String[]{"暂无搜索记录"};
        }else if (history_arr.length > 50) {
            String[] newArrays = new String[50];
            // 实现数组之间的复制
            System.arraycopy(history_arr, 0, newArrays, 0, 50);
            arr_adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_dropdown_item_1line, history_arr);
        }
        // 设置适配器
        // 新建适配器，适配器数据为搜索历史文件内容
        arr_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, history_arr);
        auto.setAdapter(arr_adapter);
        auto.setDropDownHeight(1000);
        auto.setThreshold(1);
        auto.setCompletionHint("最近的50条记录");
    }
    public void save() {
        // 获取搜索框信息
        String text = auto.getText().toString();
        SharedPreferences sp = getSharedPreferences("search_history", 0);
        String old_text = sp.getString("history", "");

        // 利用StringBuilder.append新增内容，逗号便于读取内容时用逗号拆分开
        StringBuilder builder = new StringBuilder(old_text);
        builder.append(text + ",");
        // 判断搜索内容是否已经存在于历史文件，已存在则不重复添加
        if (!old_text.contains(text + ",")) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("history", builder.toString());
            editor.commit();
            Toast.makeText(this, text + "添加成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, text + "已存在", Toast.LENGTH_SHORT).show();
        }
    }

    //清除搜索记录
    public void cleanHistory(View v) {
        SharedPreferences sp = getSharedPreferences("search_history", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
        Toast.makeText(this, "清除成功", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
