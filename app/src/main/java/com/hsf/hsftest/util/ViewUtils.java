package com.hsf.hsftest.util;

import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

/**
 * @作者 hsf
 * @创建日期 2017/8/14 10:47
 * <p>
 * <p>
 * 比较Android中dip, dp, px, sp之间的区别：
 * dip: device independent pixels(设备独立像素). 不同设备有不同的显示效果,这个和设备硬件有关，一般我们为了支持WVGA、HVGA和QVGA 推荐使用这个，
 * 不依赖像素。
 * px: pixels(像素). 不同设备显示效果相同，一般我们HVGA代表320x480像素，这个用的比较多。
 * pt: point，是一个标准的长度单位，1pt＝1/72英寸，用于印刷业，非常简单易用；
 * sp: scaled pixels(放大像素). 主要用于字体显示best for textsize，根据 google 的建议，TextView 的字号最好使用 sp 做单位。
 * 过去，程序员通常以像素为单位设计计算机用户界面。例如，定义一个宽度为300像素的表单字段，列之间的间距为5个像素，图标大小为16×16像素 等。
 * 这样处理的问题在于，如果在一个每英寸点数（dpi）更高的新显示器上运行该程序，则用户界面会显得很小。在有些情况下，用户界面可能会小到难以
 * 看清 内容。
 * <p>
 * <p>
 * 与分辨率无关的度量单位可以解决这一问题，android支持下列所有单位：
 * px（像素）：屏幕上的点。
 * in（英寸）：长度单位。
 * mm（毫米）：长度单位。
 * pt（磅）：1/72英寸。
 * dp（与密度无关的像素）：一种基于屏幕密度的抽象单位。在每英寸160点的显示器上，1dp = 1px。
 * dip：与dp相同，多用于android/ophone示例中。
 * sp（与刻度无关的像素）：与dp类似，但是可以根据用户的字体大小首选项进行缩放。
 */

public class ViewUtils {
    /**
     * 在代码中设置View的margin值
     *
     * @param v
     * @param l
     * @param t
     * @param r
     * @param b
     */
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕宽高方法1
     *
     * @param context
     * @return
     */
    public static int[] getScreenWH1(Context context) {
        // 方法1,获取屏幕的默认分辨率
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int[] screen = new int[2];
        Display display = manager.getDefaultDisplay(); // getWindowManager().getDefaultDisplay();
        int screenWidth = display.getWidth(); // 屏幕宽（像素，如：480px）
        screen[0] = screenWidth;
        int screenHeight = display.getHeight(); // 屏幕高（像素，如：800px）
        screen[1] = screenHeight;
        return screen;
    }

    /**
     * 获取屏幕宽高方法2
     *
     * @param context
     * @return
     */
    public static int[] getScreenWH2(Context context) {
        // 方法2,通过WindowManager获取
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int[] screen = new int[2];
        DisplayMetrics dm = new DisplayMetrics();
        float density = dm.density;//屏幕密度（像素比例：./././.）
        int densityDPI = dm.densityDpi;// 屏幕密度（每寸像素：///）
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;
        manager.getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels; // 屏幕宽（px，如：480px）
        screen[0] = screenWidth;
        int screenHeight = dm.heightPixels; // 屏幕高（px，如：800px）
        screen[1] = screenHeight;
        return screen;
    }

    /**
     * 获取屏幕宽高方法3
     *
     * @param context
     * @return
     */
    public static int[] getScreenWH3(Context context) {
        // 方法3,通过Resources获取
        int[] screen = new int[2];
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels; // 屏幕宽（像素，如：480px）

        screen[0] = screenWidth;
        int screenHeight = dm.heightPixels; // 屏幕高（像素，如：800px）
        screen[1] = screenHeight;
        return screen;
    }

    /**
     * 获取控件的宽高 根据view绘制流程，在onCreate中控件其实还并没有画好，
     * 换句话说，等onCreate方法执行完了，我们定义的控件才会被度量(measure)，
     * 所以一般来说在onCreate方法里面通过view.getHeight()获取控件的高度或者宽度全是0，
     * 采用下面的方法，可以得到真实的宽高。
     * 方法一
     */
    public static int[] getViewWH1(Context context, View view) {
        // 方法一,此方法会加载onMeasure三次
        int[] viewSize = new int[2];
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        view.measure(w, h);
        int width = view.getMeasuredWidth();
        viewSize[0] = width;
        int height = view.getMeasuredHeight();
        viewSize[1] = height;
        return viewSize;
    }

    /**
     * 在代码中获取控件宽高的方法2
     *
     * @param context
     * @param view
     * @return
     */
    public static int[] getViewWH2(Context context, final View view) {
        // 方法二,需要注册一个ViewTreeObserver的监听回调，这个监听回调，就是专门监听绘图的，
        // 此方法会加载onMeasure二次，但是回调函数会回调很多次,所以在每次监听前判断hasMeasured，避免重复监听。
        // (如果是设置item的控件，则不需要hasMeasured)
        final int[] viewSize = new int[2];
        ViewTreeObserver vto = view.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            boolean hasMeasured = false;

            public boolean onPreDraw() {
                if (hasMeasured == false) {
                    int width = view.getMeasuredWidth();
                    viewSize[0] = width;
                    int height = view.getMeasuredHeight();
                    viewSize[1] = height;
                    hasMeasured = true;
                }
                return true;
            }
        });
        return viewSize;
    }

    /**
     * 在代码中获取控件宽高的方法3
     *
     * @param context
     * @param view
     * @return
     */
    public static int[] getViewWH3(Context context, final View view) {
        // 方法三,此方法会加载onMeasure二次，但是回调函数只回调一次
        // 与方法二基本相同，但他是全局的布局改变监听器，所以是最推荐使用。
        final int[] viewSize = new int[2];
        ViewTreeObserver vto = view.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                viewSize[0] = view.getWidth();
                viewSize[1] = view.getHeight();
            }
        });
        return viewSize;
    }
    /**
     * 监听软键盘状态,并改变相应控件的位置
     * @param main
     * @param scroll
     */
    public static void addLayoutListener(final View main, final View scroll) {
        main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                //获取llContent在窗体的可视区域
                main.getWindowVisibleDisplayFrame(rect);
                //获取llContent在窗体的不可视区域，在键盘没有弹起时
                int llContentInVisibleHeight = main.getRootView().getHeight() - rect.bottom;
                Log.e("TAG", "不可见区域的高度===" + llContentInVisibleHeight);
                //不可见区域大于300px，说明键盘弹起了
                if (llContentInVisibleHeight > 300) {
                    int[] location = new int[2];
                    //获取loginRegisterNext的窗体坐标，算出llContent需要滚动的高度
                    scroll.getLocationInWindow(location);
                    int scrollHeight = location[1] + scroll.getHeight() - rect.bottom;
                    //让界面整体上移的高度
                    main.scrollTo(0, scrollHeight);

                } else {
                    main.scrollTo(0, 0);
                }
            }
        });
    }


}
