package me.photran.library.layout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import me.photran.library.R;
import me.photran.library.action.ActionAnswerView;
import me.photran.library.base.AnswerBaseView;
import me.photran.library.model.AnswerState;
import me.photran.library.model.AnswerViewProperty;
import me.photran.library.model.PossibleAnswers;

/**
 * Short name for Id/View: anv
 * <p>
 * Created by photran on 12/24/16.
 */

public class AnswerView extends AnswerBaseView implements View.OnClickListener, ActionAnswerView {

    public interface AnswerViewListener {
        void onAnswerViewStateChanged(AnswerView answerView, AnswerState answerStateOld, AnswerState answerStateNew);
    }


    private AnswerState mAnswerState = AnswerState.DEFAULT;
    private AnswerViewListener mAnswerViewListener;

    public AnswerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAnswerView();
    }

    private void initAnswerView() {
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mAnswerState == AnswerState.CORRECT_ANSWER) {
            return;
        }

        AnswerState newAnswerState = (mAnswerState == AnswerState.DEFAULT) ?
                AnswerState.USER_CHECKED :
                AnswerState.DEFAULT;

        changeViewByState(newAnswerState);

        if (mAnswerViewListener != null) {
            mAnswerViewListener.onAnswerViewStateChanged(this, mAnswerState, newAnswerState);
        }
    }

    @Override
    public void reset() {
        if (isChecked()) {
            changeViewByState(AnswerState.DEFAULT);
        }
    }

    @Override
    public PossibleAnswers getAnswer() {
        if (!isChecked()) {
            return null;
        }
        return PossibleAnswers.valueOf(mText);
    }

    @Override
    public void handleAnswer(@NonNull PossibleAnswers correctAnswer, @Nullable PossibleAnswers answersUserSelected) {
        if (answersUserSelected != null && isPossibleAnswers(answersUserSelected)) {
            changeViewByState(AnswerState.USER_CHECKED);
        } else if (isPossibleAnswers(correctAnswer)) {
            changeViewByState(AnswerState.CORRECT_ANSWER);
        } else {
            disableEvent();
        }
    }

    public void setAnswerViewListener(AnswerViewListener mAnswerViewListener) {
        this.mAnswerViewListener = mAnswerViewListener;
    }

    @Override
    public void changeViewByState(@NonNull AnswerState state) {
        if (mAnswerState == state) {
            return;
        }
        mAnswerState = state;
        AnswerViewProperty property = new AnswerViewProperty();

        switch (mAnswerState) {
            case DEFAULT:
                property.backgroundId = R.drawable.selector_answer_view_unchecked;
                property.text = mText;
                break;
            case USER_CHECKED:
                property.backgroundId = R.drawable.selector_answer_view_checked;
                property.text = AnswerViewProperty.EMPTY_STRING;
                break;
            case CORRECT_ANSWER:
                property.backgroundId = R.drawable.bg_answer_view_correct;
                property.text = AnswerViewProperty.EMPTY_STRING;
                break;
        }

        viewWithAnswerViewProperty(property);
    }

    private boolean isChecked() {
        return mAnswerState == AnswerState.USER_CHECKED;
    }

    private boolean isPossibleAnswers(@NonNull PossibleAnswers answers) {
        return TextUtils.equals(mText, answers.name());
    }

    private void disableEvent() {
        mAnswerState = AnswerState.CORRECT_ANSWER;
    }
}
