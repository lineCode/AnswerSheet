package me.photran.library.layout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import me.photran.library.action.ActionAnswerLayout;
import me.photran.library.base.AnswerBaseLayout;
import me.photran.library.model.AnswerState;
import me.photran.library.model.PossibleAnswers;

/**
 * Short name for Id/View: answer_layout/AnswerLayout
 * Created by photran on 12/24/16.
 */

public class AnswerLayout extends AnswerBaseLayout implements ActionAnswerLayout, AnswerView.AnswerViewListener {

    public interface AnswerLayoutListener {
        void fistAnswerSelected(@NonNull PossibleAnswers answers);
    }


    private AnswerView answerViewCurrent = null;
    private AnswerLayoutListener mAnswerLayoutListener;

    public AnswerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setQuestionIndex(int index) {
        String strQuestionIndex = String.valueOf(index);
        if (index < 10) {
            strQuestionIndex = "0" + strQuestionIndex;
        }
        mTextQuestionIndex.setText(strQuestionIndex);
    }

    @Nullable
    @Override
    public PossibleAnswers getPossibleAnswers() {
        if (answerViewCurrent != null) {
            return answerViewCurrent.getAnswer();
        }

        return null;
    }

    @Override
    public void onAnswerViewStateChanged(AnswerView answerView,
                                         AnswerState answerStateOld,
                                         AnswerState answerStateNew) {

        if (answerViewCurrent != null && answerViewCurrent != answerView) {
            answerViewCurrent.reset();
        }

        if (answerViewCurrent == null && mAnswerLayoutListener != null) {
            mAnswerLayoutListener.fistAnswerSelected(answerView.getAnswer());
        }

        answerViewCurrent = answerView;
    }

    @Override
    public void fillAnswer(@NonNull PossibleAnswers correctAnswer,
                           @Nullable PossibleAnswers answersUserSelected) {

        for (int i = 0; i < NUMBER_ANSWER_OPTION; i++) {
            AnswerView answerView = mAnswerViews[i];
            answerView.handleAnswer(correctAnswer, answersUserSelected);
        }
    }

    public void setAnswerLayoutListener(AnswerLayoutListener listener) {
        mAnswerLayoutListener = listener;
    }
}
