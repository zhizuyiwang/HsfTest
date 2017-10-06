package com.hsf.hsftest.design.recycler.referesh.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hsf.hsftest.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Package com.demo.view.recyclerview_pull_refresh.
 * Created by yaoh on 2017/08/21.
 * <p/>
 * Description:
 */
public abstract class BaseRecyPRAdapter<T> extends RecyclerView.Adapter<BaseRecyPRAdapter.BaseViewHolder> {
    private static final String TAG = "BaseRecyPRAdapter";

    public static final int VIEW_ITEM = 0;
    public static final int VIEW_PROG = 1;

    public List<T> mDataList;

    private final Context mContext;
    private final RecyclerView mRecyclerView;
    private int totalItemCount;
    private int lastVisibleItemPosition;
    boolean isShowFootVieW = false;
    private Handler handler = new Handler();

    private static final int STATE_NORMAL = 0;
    private static final int STATE_LOADING = 1;
    private static final int STATE_LOAD_COMPLETE = 2;
    private int mState = STATE_NORMAL;

    public BaseRecyPRAdapter(Context context, RecyclerView recyclerView) {
        mContext = context;
        mRecyclerView = recyclerView;
        if (mRecyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    int extent = recyclerView.computeVerticalScrollExtent();
                    int range = recyclerView.computeVerticalScrollRange();
                    Log.e(TAG, "\n extent = " + extent + "\n range = " + range);

                    if (range > extent) {
                        if(!isShowFootVieW){
                            notifyDataSetChanged();
                        }
                        isShowFootVieW = true;
                    } else {
                        isShowFootVieW = false;
                    }
                    totalItemCount = linearLayoutManager.getItemCount();
                    if (mState == STATE_NORMAL && newState == RecyclerView.SCROLL_STATE_IDLE &&
                            totalItemCount == lastVisibleItemPosition + 1 && range > extent) {
                        mState = STATE_LOADING;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (mLoadMoreDataListener != null) {
                                    mLoadMoreDataListener.loadMoreData();
                                }
                            }
                        });
                    }
                }
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                }
            });
        }
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = null;
        View view = null;
        if (viewType == VIEW_PROG) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false);
        }
        holder = new BaseViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder.getItemViewType() == BaseRecyPRAdapter.VIEW_PROG) {
                ProgressBar progressBar = (ProgressBar) holder.getView(R.id.progressbar);
                TextView text = (TextView) holder.getView(R.id.text);

                if (mState == STATE_LOAD_COMPLETE) {
                    progressBar.setVisibility(View.GONE);
                    text.setText("没有数据了");
                } else {
                progressBar.setVisibility(View.VISIBLE);
                text.setText("正在加载中");
            }
        }
        if (holder.getItemViewType() == BaseRecyPRAdapter.VIEW_ITEM) {
            onBindData(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        if (isShowFootVieW) {
            return mDataList == null ? 0 : mDataList.size() + 1;
        } else {
            return mDataList == null ? 0 : mDataList.size();
        }
    }


    //根据不同的数据返回不同的viewType
    @Override
    public int getItemViewType(int position) {
        if (mDataList == null)
            return VIEW_ITEM;

        if (position == mDataList.size()) {
            return VIEW_PROG;
        } else {
            return VIEW_ITEM;
        }
    }

    /**
     * 根据type 返回不同的布局
     *
     * @param type
     * @return
     */
    public abstract int getLayoutId(int type);

    public abstract void onBindData(BaseViewHolder holder, int position);


    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        private Map<Integer, View> mViewMap;
        public View view;

        public BaseViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            mViewMap = new HashMap<>();
        }

        /**
         * 获取设置的view
         *
         * @param id
         * @return
         */
        public View getView(int id) {
            View view = mViewMap.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                mViewMap.put(id, view);
            }
            return view;
        }
    }

    public void stopLoadMore() {
        mState = STATE_NORMAL;
        notifyDataSetChanged();
    }

    public void loadComplete() {
        mState = STATE_LOAD_COMPLETE;
        notifyItemChanged(getItemCount()-1);
    }

    public void setData(List<T> datas) {
        mDataList = datas;
    }

    private LoadMoreDataListener mLoadMoreDataListener;

    public void setLoadMoreDataListener(LoadMoreDataListener mLoadMoreDataListener) {
        this.mLoadMoreDataListener = mLoadMoreDataListener;
    }

    public interface LoadMoreDataListener {
        void loadMoreData();
    }
}
