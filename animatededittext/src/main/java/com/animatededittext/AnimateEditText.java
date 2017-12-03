package com.animatededittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;

import static android.R.attr.inputType;
import static android.R.attr.textColor;
import static java.security.AccessController.getContext;

/**
 * Created by akash on 02-12-2017.
 */
public class AnimateEditText extends LinearLayout {
    private static final String TAG = "AnimateEditText";
    private EditText mEditText;
    private View mSecondView;
    private Animation mAnimation;
    private String hint;
    private int lineColor;
    private int maxLines,textColor;
    private boolean animationFromStart;
    private int type;

    public AnimateEditText(Context context) {
        super(context);
        init(context);

    }

    public AnimateEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        getAttributeSet(context, attrs);
    }

    public AnimateEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        getAttributeSet(context, attrs);
    }

    private void getAttributeSet(Context context, AttributeSet attrs) {
        TypedArray typedArray;
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.AnimateEditText);
        hint = typedArray.getString(R.styleable.AnimateEditText_hint);
        lineColor = typedArray.getColor(R.styleable.AnimateEditText_lineColor, ContextCompat.getColor(context, R.color.colorAccent));
        maxLines = typedArray.getInteger(R.styleable.AnimateEditText_maxLines, 1);
        animationFromStart = typedArray.getBoolean(R.styleable.AnimateEditText_animationFromStart, false);
        textColor = typedArray.getColor(R.styleable.AnimateEditText_textColor, ContextCompat.getColor(context,android.R.color.black));
        type = typedArray.getInteger(R.styleable.AnimateEditText_android_inputType,1);
        setHint(hint);
        setLineColor(lineColor);
        setMaxLines(maxLines);
        animateLine();
        setTextColor(textColor);
        setInputType(type);
        Log.d(TAG, "init: hint:"+hint);
        Log.d(TAG, "init: lineColor:"+lineColor);
        Log.d(TAG, "init: maxLines:"+maxLines);
        Log.d(TAG, "init: animateFromStart:"+animationFromStart);
    }

    public void setInputType(int type) {
        mEditText.setInputType(type);
    }

    public void setTextColor(int textColor) {
        mEditText.setTextColor(textColor);
    }

    private void init(Context context) {
        View view = inflate(context, R.layout.edittext_layout, this);
        mEditText = (EditText) view.findViewById(R.id.editText);
        mSecondView = (View) view.findViewById(R.id.view_second);
    }

    private void animateLine(){
        if(animationFromStart){
            mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.scale_from_start);
        }else {
            mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.scale);
        }
        mEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    if (mSecondView.getVisibility() == INVISIBLE){
                        mSecondView.setVisibility(VISIBLE);
                    }
                    mSecondView.startAnimation(mAnimation);
                }
            }
        });
    }

    public String getText() {
        return mEditText.getText().toString();
    }

    public void setText(String text) {
        mEditText.setText(text);
    }

    public void setHint(String hint) {
        mEditText.setHint(hint);
    }

    public void setMaxLines(int maxLines){
        mEditText.setMaxLines(maxLines);
    }

    public void setLineColor(int lineColor) {
        mSecondView.setBackgroundColor(lineColor);
    }

    public void setOnTextChangeListner(final Watcher watcher) {
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                watcher.beforeTextChanged(charSequence, start, count, after);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                watcher.onTextChanged(charSequence, start, before, count);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                watcher.afterTextChanged(editable);
            }
        });
    }




}
