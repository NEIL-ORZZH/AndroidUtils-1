
package com.angeldevil.androidutils.preference;

import android.content.Context;
import android.preference.PreferenceCategory;
import android.util.AttributeSet;

import com.angeldevil.androidutils.R;

/**
 * 用于在Preference项之间画一条线，不显示任何图像和文字
 * 
 * @author angeldevil
 */
public class LinePreferenceCategory extends PreferenceCategory {

    public LinePreferenceCategory(Context context) {
        this(context, null);
    }

    public LinePreferenceCategory(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinePreferenceCategory(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setLayoutResource(R.layout.pref_line);
        setSelectable(false);
    }

}
