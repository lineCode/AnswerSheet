package me.photran.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Short name for Id/View: anv
 * <p>
 * Created by photran on 12/24/16.
 */

public class AnswerView extends TextView implements View.OnClickListener, ActionAnswerView {

    public interface AnswerViewListener {
        void onAnswerViewStateChanged(AnswerView answerView, AnswerState answerStateOld, AnswerState answerStateNew);
    }

    private class AnswerViewProperty {
        public int backgroundId = R.drawable.bg_answer_view_correct;
        public String text = "";
    }

    private static final String EMPTY_STRING = "";

    private AnswerState mAnswerState = AnswerState.DEFAULT;
    private String mText = EMPTY_STRING;
    private AnswerViewListener mAnswerViewListener;

    public AnswerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAnswerView();
    }

    private void initAnswerView() {
        setOnClickListener(this);
        mText = getText().toString();
    }

    @Override
    public void onClick(View v) {
        if (mAnswerState == AnswerState.CORRECT_ANSWER) {
            return;
        }

        AnswerState newAnswerState = mAnswerState == AnswerState.DEFAULT ?
                AnswerState.USER_CHECKED :
                AnswerState.DEFAULT;
        changeViewByState(newAnswerState);
    }

    @Override
    public void reset() {
        if (mAnswerState == AnswerState.DEFAULT) {
            return;
        }
        changeViewByState(AnswerState.DEFAULT);
    }

    @Override
    public boolean isChecked() {
        return mAnswerState == AnswerState.USER_CHECKED;
    }

    @Override
    public void setCorrectAnswerState() {
        changeViewByState(AnswerState.CORRECT_ANSWER);
    }

    private void changeViewByState(AnswerState state) {
        if (mAnswerState == state) {
            return;
        }

        if (mAnswerViewListener != null) {
            mAnswerViewListener.onAnswerViewStateChanged(this, mAnswerState, state);
        }

        mAnswerState = state;
        AnswerViewProperty property = new AnswerViewProperty();

        switch (mAnswerState) {
            case DEFAULT:
                property.backgroundId = R.drawable.bg_answer_view_unchecked;
                property.text = mText;
                break;
            case USER_CHECKED:
                property.backgroundId = R.drawable.bg_answer_view_checked;
                property.text = EMPTY_STRING;
                break;
            case CORRECT_ANSWER:
                property.backgroundId = R.drawable.bg_answer_view_correct;
                property.text = EMPTY_STRING;
                break;
        }

        viewWithAnswerViewProperty(property);
    }

    private void viewWithAnswerViewProperty(@NonNull AnswerViewProperty property) {
        setBackgroundResource(property.backgroundId);
        setText(property.text);
    }

    public void setAnswerViewListener(AnswerViewListener mAnswerViewListener) {
        this.mAnswerViewListener = mAnswerViewListener;
    }
}
