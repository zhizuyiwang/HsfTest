package com.hsf.hsftest.image.imagezoom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hsf.hsftest.R;
import com.hsf.hsftest.image.imagezoom.util.ImageZoom;

import java.util.ArrayList;
import java.util.List;

public class ZoomImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image);
        final String[] urls = new String[] {
                "http://imgsrc.baidu.com/forum/w%3d580/sign=95035e68d70735fa91f04eb1ae510f9f/8af78a13632762d0c91858cca3ec08fa513dc6be.jpg",
                "http://h.hiphotos.baidu.com/zhidao/pic/item/0823dd54564e9258469ab81a9e82d158cdbf4e93.jpg",
                "http://img.25pp.com/uploadfile/bizhi/ipad3/2015/0120/20150120090811863_ipadm.jpg",
                "http://b.zol-img.com.cn/sjbizhi/images/2/320x510/1352891767829.jpg",
                "http://image.tianjimedia.com/uploadImages/2012/289/71X94T2PF22Z.jpg",
                "http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1306/30/c1/22775100_1372595982522_320x480.jpg",
                "http://b.zol-img.com.cn/sjbizhi/images/5/320x510/1372924333667.jpg",
                "http://5.66825.com/download/pic/000/330/7ab6bda65d3355ac6b53eab8eb3210c1.jpg",
                "http://image.tianjimedia.com/uploadImages/2014/069/XXQR67MY1RAR.jpg",
                "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1479174352&di=5e01ae3f2a6e7af7572ca993a96ce5cd&src=http://f.hiphotos.baidu.com/image/pic/item/a8014c086e061d9507500dd67ff40ad163d9cacd.jpg",
                "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1479174352&di=e97384269732cac637000365f47581d7&src=http://d.hiphotos.baidu.com/image/pic/item/562c11dfa9ec8a13f075f10cf303918fa1ecc0eb.jpg",
                "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1479174352&di=a5a9dc249dcfc67f6b2a6cd21a42329e&src=http://g.hiphotos.baidu.com/image/pic/item/c2cec3fdfc03924578c6cfe18394a4c27c1e25e8.jpg",
                "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1479174352&di=b4ec34b54effa54f3c5be76e94381b44&src=http://a.hiphotos.baidu.com/image/pic/item/f9dcd100baa1cd11daf25f19bc12c8fcc3ce2d46.jpg",
                "http://c.hiphotos.baidu.com/image/pic/item/d043ad4bd11373f0d892d5d9a10f4bfbfaed04c2.jpg"  };

        final List<String> list = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            list.add(urls[i]);
        }

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageZoom.show(ZoomImageActivity.this, urls[1], list);
            }
        });

    }
}
