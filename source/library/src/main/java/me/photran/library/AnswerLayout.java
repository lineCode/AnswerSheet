package me.photran.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Short name for Id/View: answer_layout/AnswerLayout
 * Created by photran on 12/24/16.
 */

public class AnswerLayout extends LinearLayout implements ActionAnswerLayout, AnswerView.AnswerViewListener {

    public interface AnswerLayoutListener {
        void fistAnswerSelected(@NonNull PossibleAnswers answers);
    }

    private static final int NUMBER_ANSWER_OPTION = 4;

    private TextView mTextQuestionIndex;
    private AnswerView[] mAnswerViews = new AnswerView[NUMBER_ANSWER_OPTION];
    private AnswerView answerViewCurrent = null;
    private AnswerLayoutListener mAnswerLayoutListener;

    public AnswerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAnswerItemLayout(context);
    }

    private void initAnswerItemLayout(@NonNull final Context context) {
        inflate(context, R.layout.layout_answer, this);
        mTextQuestionIndex = (TextView) findViewById(R.id.layout_answer_text_question_index);

        int[] idAnswerViews = new int[]{R.id.layout_answer_answer_view_option_a,
                R.id.layout_answer_answer_view_option_b,
                R.id.layout_answer_answer_view_option_c,
                R.id.layout_answer_answer_view_option_d
        };

        for (int i = 0; i < NUMBER_ANSWER_OPTION; i++) {
            int idAnswerView = idAnswerViews[i];
            AnswerView answerView = (AnswerView) findViewById(idAnswerView);
            answerView.setAnswerViewListener(this);

            mAnswerViews[i] = answerView;
        }

    }

    @Override
    public void setQuestionIndex(int index) {
        mTextQuestionIndex.setText(String.valueOf(index));
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
    public void setCorrectAnswer(@NonNull PossibleAnswers correctAnswer) {
        for (int i = 0; i < NUMBER_ANSWER_OPTION; i++) {
            AnswerView answerView = mAnswerViews[i];
            if (answerView.isPossibleAnswers(correctAnswer)) {
                answerView.setCorrectAnswerState();
            } else {
                answerView.disableEvent();
            }
        }
    }

    public void setAnswerLayoutListener(AnswerLayoutListener listener) {
        mAnswerLayoutListener = listener;
    }
}
