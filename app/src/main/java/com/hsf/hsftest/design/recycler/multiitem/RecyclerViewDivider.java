package com.hsf.hsftest.design.recycler.multiitem;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *@作者 hsf
 *
 *@创建日期 2017/9/20 15:26
 */


public class RecyclerViewDivider extends RecyclerView.ItemDecoration {

    private Context mContext;
    private int mOrientation = LinearLayoutManager.HORIZONTAL;
    private int mBeforeHeaderDividerWidth;
    private int mAfterFooterDividerWidth;
    private int mItemsDividerWidth;
    private Drawable mDividerDrawable;

    public RecyclerViewDivider(Context context) {
        this.mContext = context;
    }

    /**
     * @param orientation {@link LinearLayoutManager#HORIZONTAL} <br/> {@link LinearLayoutManager#VERTICAL}
     * @return
     */
    public RecyclerViewDivider orientation(int orientation) {
        mOrientation = orientation;
        return this;
    }

    public RecyclerViewDivider beforeFirstViewDividerWidth(int w) {
        this.mBeforeHeaderDividerWidth = w;
        return this;
    }

    public RecyclerViewDivider afterLastViewDividerWidth(int w) {
        mAfterFooterDividerWidth = w;
        return this;
    }

    public RecyclerViewDivider itemsDividerWidth(int w) {
        mItemsDividerWidth = w;
        return this;
    }

    public RecyclerViewDivider dividerDrawable(int resId) {
        mDividerDrawable = mContext.getResources().getDrawable(resId);
        return this;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.Adapter<?> adapter = parent.getAdapter();
        if (adapter == null) {
            return;
        }
        int count = adapter.getItemCount();

        int position = parent.getChildAdapterPosition(view);

        if (mOrientation == LinearLayoutManager.VERTICAL) {
            vertical(outRect, count, position);
        } else {
            horizontal(outRect, count, position);
        }

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            drawHorizontal(c, parent);
        } else {
            drawVertical(c, parent);
        }
    }


    private void vertical(Rect outRect, int count, int position) {
        if (position == 0) {
            outRect.top = mBeforeHeaderDividerWidth;
        }
        if (position == count - 1) {
            outRect.bottom = mAfterFooterDividerWidth;
        }

        if (position > 0) {
            outRect.top = mItemsDividerWidth;
        }
    }

    private void horizontal(Rect outRect, int count, int position) {
        if (position == 0) {
            outRect.left = mBeforeHeaderDividerWidth;
        }
        if (position == count - 1) {
            outRect.right = mAfterFooterDividerWidth;
        }

        if (position > 0) {
            outRect.left = mItemsDividerWidth;
        }
    }


    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        if (mDividerDrawable == null) {
            return;
        }
        drawHorizontalDividerBeforeFirstView(canvas, parent);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + layoutParams.bottomMargin;
            int bottom;

            if (i == childSize - 1) {
                bottom = top + mAfterFooterDividerWidth;
            } else {
                bottom = top + mItemsDividerWidth;
            }

            mDividerDrawable.setBounds(left, top, right, bottom);
            mDividerDrawable.draw(canvas);

        }
    }


    private void drawVertical(Canvas canvas, RecyclerView parent) {
        if (mDividerDrawable == null) {
            return;
        }
        drawVerticalDividerBeforeFirstView(canvas, parent);
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();

        int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + layoutParams.rightMargin;
            int right;
            if (i == childSize - 1) {
                right = left + mAfterFooterDividerWidth;
            } else {
                right = left + mItemsDividerWidth;
            }
            mDividerDrawable.setBounds(left, top, right, bottom);
            mDividerDrawable.draw(canvas);
        }

    }

    private void drawVerticalDividerBeforeFirstView(Canvas canvas, RecyclerView parent) {
        View child = parent.getChildAt(0);
        if (child == null) {
            return;
        }
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();

        int right;
        int left;

        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();

        right = child.getLeft() + layoutParams.leftMargin;
        int position = parent.getChildAdapterPosition(child);
        if (position == 0) {
            left = right - mBeforeHeaderDividerWidth;
        } else {
            left = right - mItemsDividerWidth;
        }

        mDividerDrawable.setBounds(left, top, right, bottom);
        mDividerDrawable.draw(canvas);


    }

    private void drawHorizontalDividerBeforeFirstView(Canvas canvas, RecyclerView parent) {
        View child = parent.getChildAt(0);
        if (child == null) {
            return;
        }

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int top;
        int bottom;

        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();

        bottom = child.getTop() - layoutParams.topMargin;
        int position = parent.getChildAdapterPosition(child);
        if (position == 0) {
            top = bottom - mBeforeHeaderDividerWidth;
        } else {
            top = bottom - mItemsDividerWidth;
        }

        mDividerDrawable.setBounds(left, top, right, bottom);
        mDividerDrawable.draw(canvas);

    }

}
