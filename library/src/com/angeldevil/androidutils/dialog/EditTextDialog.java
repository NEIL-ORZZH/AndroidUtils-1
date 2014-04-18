
package com.angeldevil.androidutils.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputFilter;
import android.text.TextUtils;
import android.widget.EditText;

/**
 * 只包含一个EditText的Dialog，子类必须实现{link {@link #onInputFinished(String)}
 * 获取当点击确定按钮时输入的内容，通过{@link #setButtons(String, String)}或
 * {@link #setButtons(int, int)}
 * 设置按钮，确定按钮必须设置，否则show时会抛异常，如果取消按钮文字为空则不显示取消按钮，<b>不应使用原生的setButton等函数设置按钮</b>
 */
public abstract class EditTextDialog extends AlertDialog implements DialogInterface, DialogInterface.OnClickListener,
        DialogInterface.OnCancelListener {

    private boolean hasButtonsSet;

    private EditText input;
    private CharSequence hint;
    private CharSequence defaultText;
    private boolean selectTextByDefault;
    private int viewSpacing = 20;
    private boolean singleLine;
    private int maxLength;

    public EditTextDialog(Context context) {
        super(context);
    }

    public EditTextDialog(Context context, int theme) {
        super(context, theme);
    }

    public void setHint(CharSequence hint) {
        this.hint = hint;
    }

    public void setHintResourceId(int resId) {
        hint = getContext().getString(resId);
    }

    /** 设置默认输入内容 */
    public void setDefaultText(String text) {
        defaultText = text;
    }

    /** 设置对话框弹出时是否默认选中所有内容 */
    public void setSelectTextByDefault(boolean select) {
        selectTextByDefault = select;
    }

    /** 设置输入框距离对话框的边距，默认20px */
    public void setViewSpacing(int spacing) {
        viewSpacing = spacing;
    }

    /** 设置确定与取消按钮显示的文字， 空字符串表示不显示，<b>确定按钮必须设置</b> */
    public void setButtons(String positive, String negative) {
        if (!TextUtils.isEmpty(positive)) {
            setButton(BUTTON_POSITIVE, positive, this);
            hasButtonsSet = true;
        }
        if (!TextUtils.isEmpty(negative)) {
            setButton(BUTTON_NEGATIVE, negative, this);
        }
    }

    /** 设置确定与取消按钮显示的文字， 0表示不显示，<b>确定按钮必须设置</b> */
    public void setButtons(int positiveResId, int negativeResId) {
        String positive = positiveResId == 0 ? null : getContext().getString(positiveResId);
        String negative = negativeResId == 0 ? null : getContext().getString(negativeResId);
        setButtons(positive, negative);
    }

    public void setSingleLine(boolean singleLine) {
        this.singleLine = singleLine;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void show() {
        if (!hasButtonsSet) {
            throw new IllegalStateException("At least one button must be set!");
        }
        if (input == null) {
            input = new EditText(getContext());
            setView(input, viewSpacing, viewSpacing, viewSpacing, viewSpacing);

            input.setSingleLine(singleLine);

            if (maxLength != 0) {
                input.setFilters(new InputFilter[] { new InputFilter.LengthFilter(maxLength) });
            }

            if (!TextUtils.isEmpty(hint)) {
                input.setHint(hint);
            }

            if (!TextUtils.isEmpty(defaultText)) {
                input.setText(defaultText);
                input.setSelection(input.getText().length());
            }
        }
        if (selectTextByDefault) {
            input.setSelection(0, input.getText().length());
        }
        setOnCancelListener(this);
        super.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (BUTTON_POSITIVE == which) {
            onInputFinished(input.getText().toString());
        } else {
            onCancelInput();
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        onCancelInput();
    }

    protected abstract void onInputFinished(String input);

    /** 取消输入，可能是按Back键或点取消按钮 */
    protected abstract void onCancelInput();
}
