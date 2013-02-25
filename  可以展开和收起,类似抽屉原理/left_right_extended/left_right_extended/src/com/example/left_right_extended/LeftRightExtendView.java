package com.example.left_right_extended;


import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class LeftRightExtendView extends ViewGroup {

	private Scroller mScroller;
	
	Context ctx;
	LinearLayout leftLayout;
	LinearLayout centerLayout;
	LinearLayout rightLayout;
	
	boolean isLeftExtended;
	boolean isRightExtended;
	boolean isInited = false;
	
	private int leftRightWidth = 280;
	
	public LeftRightExtendView(Context context) {
		super(context);
		ctx = context;
		init();
	}
	
	private void init() {
		mScroller = new Scroller(ctx);
		
		leftLayout = new LinearLayout(ctx);
		addView(leftLayout);
		
		centerLayout = new LinearLayout(ctx);
		
		centerLayout.setOrientation(LinearLayout.VERTICAL);
		addView(centerLayout);
		
		rightLayout = new LinearLayout(ctx);
		addView(rightLayout);
		
	}
	
	public void setLeftRightWidth(int widthdip)
	{
		leftRightWidth = widthdip;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int childLeft = 0;

		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			final View child = getChildAt(i);
			if (child.getVisibility() != View.GONE) {
				final int childWidth = child.getMeasuredWidth();
				child.layout(childLeft, 0, childLeft + childWidth,
						child.getMeasuredHeight());
				childLeft += childWidth;
			}
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		if (widthMode != MeasureSpec.EXACTLY) {
			throw new IllegalStateException("error mode.");
		}

		final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		if (heightMode != MeasureSpec.EXACTLY) {
			throw new IllegalStateException("error mode.");
		}
		
		int measureSpec = MeasureSpec.makeMeasureSpec(dip2px(leftRightWidth), MeasureSpec.EXACTLY);
		
		leftLayout.measure(measureSpec, heightMeasureSpec);
		centerLayout.measure(widthMeasureSpec, heightMeasureSpec);
		rightLayout.measure(measureSpec, heightMeasureSpec);
		
		if (isInited == false)
		{
			scrollTo(leftLayout.getMeasuredWidth(), 0);
			isInited = true;
		}
	}

	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			int x = mScroller.getCurrX();
			scrollTo(x, 0);
			postInvalidate();
		}
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt)
	{
		super.onScrollChanged(l, t, oldl, oldt);
	}
	
	public void addViewLeft(View view)
	{
		if (leftLayout.getChildCount() > 0)
		{
			leftLayout.removeAllViews();
		}
		leftLayout.addView(view, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
	}
	
	public void addViewCenter(View view)
	{
		if (centerLayout.getChildCount() > 0)
		{
			centerLayout.removeAllViews();
		}
		centerLayout.addView(view, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
	}
	
	public void addViewRight(View view) {
		if (rightLayout.getChildCount() > 0)
		{
			rightLayout.removeAllViews();
		}
		rightLayout.addView(view, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
	}
	
	public void showLeft()
	{
		if (isLeftExtended == false && isRightExtended == false && leftLayout.getChildCount() > 0)
		{
			int delta = 0 - leftLayout.getWidth();
			//scrollTo(0, 0);
			mScroller.startScroll(getScrollX(), 0, delta, 0, 500);
			invalidate();
			isLeftExtended = true;
		}
	}
	public void showRight()
	{
		if (isLeftExtended == false && isRightExtended == false && rightLayout.getChildCount() > 0)
		{
			int delta = rightLayout.getWidth();
			mScroller.startScroll(getScrollX(), 0, delta, 0, 500);
			invalidate();
			isRightExtended = true;
		}
	}
	
	public boolean showCenter()
	{
		if (!mScroller.isFinished())
			return true;
		if (isLeftExtended)
		{
			int delta = leftLayout.getWidth();
			mScroller.startScroll(getScrollX(), 0, delta, 0, 500);
			invalidate();
			isLeftExtended = false;
			return true;
		}
		else if (isRightExtended)
		{
			int delta = 0 - rightLayout.getWidth();
			mScroller.startScroll(getScrollX(), 0, delta, 0, 500);
			invalidate();
			isRightExtended = false;
			return true;
		}
		return false;
	}
	
	public boolean isLeftExtended()
	{
		return isLeftExtended;
	}
	public boolean isRightExtended()
	{
		return isRightExtended;
	}
	
	public int dip2px(float dipValue)
	{
		float scale = getResources().getDisplayMetrics().density;
		return (int)(dipValue * scale + 0.5f);
	}
}
