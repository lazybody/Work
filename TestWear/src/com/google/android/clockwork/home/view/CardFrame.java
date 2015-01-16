package com.google.android.clockwork.home.view;

import android.content.Context;
import android.view.ViewGroup;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

public class CardFrame  extends ViewGroup
implements ValueAnimator.AnimatorUpdateListener{

	public CardFrame(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationUpdate(ValueAnimator animation) {
		// TODO Auto-generated method stub
		
	}
	
	public static abstract interface CardContent
	  {
	    public abstract void showFullContent();
	  }


}
