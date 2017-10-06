package com.hsf.hsftest.comment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/4 20:11
 *  Android之ViewPager+Fragment实现懒加载
 *
 *  http://blog.csdn.net/my_rabbit/article/details/76456593
 */


public abstract class BaseFragment extends Fragment {
    protected View mRootView;
    protected boolean isVisible = false; //是否可见
    private boolean isPrepared = false;//是否初始化完成
    private boolean isFirst = true;//是否第一次加载
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mRootView == null) {
            return;
        }
        if (getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
            onInvisible();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = initView(inflater, container, savedInstanceState);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }
    /**
     * 懒加载
     */
    private void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirst) {
            return;
        }
        initData();
        isFirst = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            setUserVisibleHint(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * 如果重用mRootView的话，一定要记得在onDestroyView()里面将mRootView先给移除掉，因为已经有过父布局的View
     * 是不能再次添加到另一个新的父布局上面的
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup) mRootView.getParent()).removeView(mRootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isVisible = false;
        isPrepared = false;
        isFirst = true;
        mRootView = null;
    }

    /**
     * 不可见时调用
     */
    protected abstract void onInvisible();
    /**
     * 获取数据
     */
    protected abstract void initData();
    /**
     * 初始化布局
     */
    protected abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

}
