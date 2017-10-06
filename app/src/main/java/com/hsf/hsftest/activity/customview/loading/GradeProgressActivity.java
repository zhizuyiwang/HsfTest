package com.hsf.hsftest.activity.customview.loading;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hsf.hsftest.R;

public class GradeProgressActivity extends AppCompatActivity {
    private GradeProgressView gradeProgressView;
    private EditText mEditText;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_progress);
        gradeProgressView = (GradeProgressView) findViewById(R.id.gradeProgressView);
        mEditText = (EditText) findViewById(R.id.et);
        mButton = (Button) findViewById(R.id.bt);

        gradeProgressView.setProgressWidthAnimation(100);   // with animation
        gradeProgressView.setBackgroundColor(Color.BLACK); //set background color
        gradeProgressView.setProgressColor(Color.RED);    //set progress and pointer color
        gradeProgressView.setLineWidth(60);
        gradeProgressView.setGapWidth(30);
        gradeProgressView.setOutLineWidth(5);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int progress = 0;
                try {
                    progress = Integer.parseInt(mEditText.getText().toString());
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
                gradeProgressView.setProgressWidthAnimation(progress);
            }
        });
    }
}
