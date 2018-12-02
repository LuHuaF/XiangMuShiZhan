package com.bwie.week1lianxi.weight;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.week1lianxi.R;

import java.util.ArrayList;

/**
 * 文件描述：
 * 作者：鲁华丰
 * 创建时间：2018/11/30
 */
public class MyFloatLayout extends LinearLayout {
    private int mScreenWidth;

    public MyFloatLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        mScreenWidth = metrics.widthPixels;
        setOrientation(VERTICAL);
    }

    public void removeChildView() {
        removeAllViews();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    public void setData(ArrayList<String> data) {
        LinearLayout linearLayout = getLin();
        for (int i = 0; i < data.size(); i++) {
            final String tmp = data.get(i);
            int numWidth = 0;
            int childCount = linearLayout.getChildCount();
            for (int j = 0; j < childCount; j++) {
                TextView tv = (TextView) linearLayout.getChildAt(j);
                LayoutParams params = (LayoutParams) tv.getLayoutParams();
                int leftMargin = params.leftMargin;
                tv.measure(getMeasuredWidth(), getMeasuredHeight());
                numWidth += tv.getMeasuredWidth() + leftMargin + tv.getPaddingLeft() + getPaddingRight();
            }
            TextView dataText = getText();
            dataText.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), tmp, Toast.LENGTH_SHORT).show();
                }
            });
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin = 15;
            params.topMargin = 10;
            dataText.setLayoutParams(params);
            dataText.setText(tmp);
            dataText.measure(getMeasuredWidth(), getMeasuredHeight());
            int dataTextWidth = dataText.getMeasuredWidth() + dataText.getPaddingLeft() + dataText.getPaddingRight();
            if (mScreenWidth >= numWidth + dataTextWidth) {
                linearLayout.addView(dataText);
            } else {

                linearLayout = getLin();
                linearLayout.addView(dataText);
            }

        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private LinearLayout getLin() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        this.addView(linearLayout);
        return linearLayout;
    }

    private TextView getText() {
        TextView textView = new TextView(getContext());
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        textView.setBackgroundResource(R.drawable.text_style);
        textView.setPadding(10, 10, 10, 10);
        return textView;
    }
}
