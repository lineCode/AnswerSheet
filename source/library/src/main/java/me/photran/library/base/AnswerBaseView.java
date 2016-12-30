package me.photran.library.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.TextView;

import me.photran.library.model.AnswerViewProperty;

/**
 * Created by photran on 12/29/16.
 */

public class AnswerBaseView extends TextView {

    protected String mText;

    public AnswerBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);

        int[] textAttr = new int[]{android.R.attr.text};
        TypedArray typedArray = context.obtainStyledAttributes(attrs, textAttr);
        mText = typedArray.getString(0);
        typedArray.recycle();
    }

    protected void viewWithAnswerViewProperty(@NonNull AnswerViewProperty property) {
        setBackgroundResource(property.backgroundId);
        setText(property.text);
    }
}
