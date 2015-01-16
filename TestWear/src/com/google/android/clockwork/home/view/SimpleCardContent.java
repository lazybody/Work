package com.google.android.clockwork.home.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.testwear.R;
import com.google.android.clockwork.home.util.ViewUtils;

public class SimpleCardContent extends LinearLayout implements CardFrame.CardContent
{
  private CharSequence mContentText;
  private final TextView mContentTextView;
  private boolean mExpanded;
  private final TextView mTitleTextView;

  public SimpleCardContent(Context paramContext)
  {
    this(paramContext, null, 0);
  }

  public SimpleCardContent(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mExpanded = false;
    setOrientation(1);
    LayoutInflater.from(paramContext).inflate(R.layout.simple_card_content, this);
    this.mTitleTextView = ((TextView)findViewById(R.id.title));
    this.mContentTextView = ((TextView)findViewById(R.id.text));
  }

  public void setText(CharSequence paramCharSequence1, CharSequence paramCharSequence2)
  {
    this.mContentText = paramCharSequence2;
    ViewUtils.setOrHideTextView(this.mTitleTextView, paramCharSequence1);
    if ((!(this.mExpanded)) && (paramCharSequence2 != null))
      paramCharSequence2 = paramCharSequence2.subSequence(0, Math.min(450, paramCharSequence2.length()));
    ViewUtils.setOrHideTextView(this.mContentTextView, paramCharSequence2);
  }

  public void showFullContent()
  {
    if (this.mExpanded)
      return;
    this.mExpanded = true;
    ViewUtils.setOrHideTextView(this.mContentTextView, this.mContentText);
  }
  
  
}
