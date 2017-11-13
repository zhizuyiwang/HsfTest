package com.hsf.hsftest.design.recycler.stickheader;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hsf.hsftest.R;
import com.hsf.hsftest.design.recycler.stickheader.decoration.DividerItemDecoration2;

import java.util.ArrayList;
import java.util.List;

/**
 *@作者 hsf
 *
 *@创建日期 2017/11/10 10:50
 * http://blog.csdn.net/binbinqq86/article/details/53102441
 */

public class StickyHeaderActivity5 extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private List<String> str = new ArrayList<>();
    private MyAdapter adapter;
    private Button btHas,btNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_header5);
        recyclerView= (RecyclerView) findViewById(R.id.rv);

        btHas= (Button) findViewById(R.id.has);
        btNo= (Button) findViewById(R.id.no);

        for (int i = 0; i < 12; i++) {
            str.add(i + "个");
        }

        //设置adapter
        adapter=new MyAdapter(str,this);
        final DividerItemDecoration2 dividerItemDecoration=new DividerItemDecoration2(this);
        dividerItemDecoration.setDrawBorderTopAndBottom(true);
        dividerItemDecoration.setDrawBorderLeftAndRight(true);
//        dividerItemDecoration.setOnlySetItemOffsetsButNoDraw(true);
        recyclerView.addItemDecoration(dividerItemDecoration);

        //设置布局管理器
//        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(5,1);
        GridLayoutManager layoutManager=new GridLayoutManager(this,4);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(1);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
//                return (3 - position % 3);
                return 1;
            }
        });
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        btHas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.removeItemDecoration(dividerItemDecoration);
//                dividerItemDecoration.setDrawBorderLine(true);
                recyclerView.addItemDecoration(dividerItemDecoration);
            }
        });
        btNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.removeItemDecoration(dividerItemDecoration);
//                dividerItemDecoration.setDrawBorderLine(false);
                recyclerView.addItemDecoration(dividerItemDecoration);
            }
        });
    }
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private List<String> datas;
        private Context mContext;
        public MyAdapter(List<String> datas,Context mContext){
            this.datas=datas;
            this.mContext=mContext;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_decoration, parent,
                    false));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(datas.get(holder.getAdapterPosition()));
//            if(holder.getAdapterPosition()==0){
//                holder.tv.setBackgroundColor(Color.BLUE);
//            }else if(holder.getAdapterPosition()==1){
//                holder.tv.setBackgroundColor(Color.MAGENTA);
//            }else if(holder.getAdapterPosition()==2){
//                holder.tv.setBackgroundColor(Color.GREEN);
//            }else{
//                holder.tv.setBackgroundColor(Color.WHITE);
//            }
            //手动更改高度，不同位置的高度有所不同
//            holder.tv.setHeight(100 + (position % 3) * 30);
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.tv);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
