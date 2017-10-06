/**
 * @author deadline
 * 
 * @time 2015/05/28
 */
package com.hsf.hsftest.image.scalelayout.scaleViewPager;
import java.util.List;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyPagerAdapter extends PagerAdapter {

	private List<? extends View> views;
	
	public MyPagerAdapter(List<? extends View> views)
	{
		this.views = views;
	}

	@Override
	public int getCount() {
		
		return (views == null) ? 0 : views.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		
		return view == object;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) 
	{
		container.removeView(views.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) 
	{
		container.addView(views.get(position));
		return views.get(position);
	}
}
