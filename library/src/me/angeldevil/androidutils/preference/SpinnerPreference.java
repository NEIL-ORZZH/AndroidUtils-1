
package com.angeldevil.androidutils.preference;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.angeldevil.androidutils.R;

/**
 * Preference不可点击，但是Summary可点击，Summery点击后弹出定义的菜单，
 * 此Preference指定的layout必须包含id为summary_contanier的View
 * ，android.R.id.summary作为summary_container的子控件
 * 
 * @author angeldevil
 */
public class SpinnerPreference extends CustomTextPreference {

    private int menuID = 0;
    private OnMenuItemClickListener listener;
    private TextView summaryView;
    private View summaryContainer;

    private OnClickListener onSummaryClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            if (menuID == 0) {
                return;
            }
            PopupMenu pm = new PopupMenu(getContext(), v);
            pm.inflate(menuID);
            pm.setOnMenuItemClickListener(new OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if (listener != null) {
                        return listener.onMenuItemClick(menuItem);
                    }
                    return false;
                }
            });
            pm.show();
        }
    };

    public SpinnerPreference(Context context) {
        super(context);
    }

    public SpinnerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutResource(R.layout.pref_spinner);
    }

    public SpinnerPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        summaryView = (TextView) view.findViewById(android.R.id.summary);
        summaryContainer = view.findViewById(R.id.summary_container);
        if (summaryView != null && summaryContainer != null) {
            summaryContainer.setVisibility(View.VISIBLE);
            summaryContainer.setClickable(true);
            summaryContainer.setBackgroundResource(R.drawable.abc_item_background_holo_light);
            summaryContainer.setOnClickListener(onSummaryClickListener);
        }

        if (TextUtils.isEmpty(getSummary()) && summaryContainer != null) {
            summaryContainer.setVisibility(View.GONE);
        }
    }

    @Override
    public void setSummary(CharSequence summary) {
        super.setSummary(summary);
        if (!TextUtils.isEmpty(summary) && summaryContainer != null) {
            summaryContainer.setVisibility(View.VISIBLE);
        }
    }

    public OnMenuItemClickListener getMenuListener() {
        return listener;
    }

    public void setMenu(int menuID, OnMenuItemClickListener listener) {
        this.menuID = menuID;
        this.listener = listener;
    }
}
