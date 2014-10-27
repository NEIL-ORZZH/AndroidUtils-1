
package me.angeldevil.androidutils.preference;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import me.angeldevil.androidutils.R;

/**
 * 可以自定义字体大小与颜色的Preference
 * 
 * @author angeldevil
 */
public class CustomTextPreference extends Preference {

    private int titleTextSizeInPixel;
    private ColorStateList titleColorStateList;
    private int summaryTextSizeInPixel;
    private ColorStateList summaryColorStateList;

    public CustomTextPreference(Context context) {
        super(context);
    }

    public CustomTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CustomTextPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        TypedArray custom = context.obtainStyledAttributes(attrs, R.styleable.customTextPreference, defStyle, 0);
        for (int i = 0; i < custom.getIndexCount(); i++) {
            int index = custom.getIndex(i);
			if (index == R.styleable.customTextPreference_titleTextSize) {
				titleTextSizeInPixel = custom.getDimensionPixelSize(index, 0);
			} else if (index == R.styleable.customTextPreference_titleTextColor) {
				titleColorStateList = custom.getColorStateList(index);
			} else if (index == R.styleable.customTextPreference_summaryTextSize) {
				summaryTextSizeInPixel = custom.getDimensionPixelSize(index, 0);
			} else if (index == R.styleable.customTextPreference_summaryTextColor) {
				summaryColorStateList = custom.getColorStateList(index);
			}
        }
        custom.recycle();
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        final TextView titleView = (TextView) view.findViewById(android.R.id.title);
        if (titleView != null) {
            final CharSequence title = getTitle();
            if (!TextUtils.isEmpty(title)) {
                if (titleTextSizeInPixel != 0) {
                    titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSizeInPixel);
                }
                if (titleColorStateList != null) {
                    titleView.setTextColor(titleColorStateList);
                }
            }
        }

        final TextView summaryView = (TextView) view.findViewById(android.R.id.summary);
        if (summaryView != null) {
            final CharSequence summary = getSummary();
            if (!TextUtils.isEmpty(summary)) {
                if (summaryTextSizeInPixel != 0) {
                    summaryView.setTextSize(TypedValue.COMPLEX_UNIT_PX, summaryTextSizeInPixel);
                }
                if (summaryColorStateList != null) {
                    summaryView.setTextColor(summaryColorStateList);
                }
            }
        }

    }
}
