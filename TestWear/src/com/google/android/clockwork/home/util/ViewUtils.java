package com.google.android.clockwork.home.util;

import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.widget.TextView;

public class ViewUtils
{
  public static long getAnimationDuration(AnimationDrawable paramAnimationDrawable)
  {
    int i = paramAnimationDrawable.getNumberOfFrames();
    long l = 0L;
    for (int j = 0; j < i; ++j)
      l += paramAnimationDrawable.getDuration(j);
    return l;
  }

  public static void setOrHideTextView(TextView paramTextView, CharSequence paramCharSequence)
  {
    if (TextUtils.isEmpty(paramCharSequence))
      paramTextView.setVisibility(8);
    while (true)
    {
      paramTextView.setText(paramCharSequence);
      return;
    }
  }
}