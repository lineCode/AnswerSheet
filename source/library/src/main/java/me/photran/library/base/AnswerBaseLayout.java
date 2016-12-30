package me.photran.library.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.photran.library.model.AnswerState;
import me.photran.library.layout.AnswerView;
import me.photran.library.R;

/**
 * Created by photran on 12/30/16.
 */

public class AnswerBaseLayout extends LinearLayout implements AnswerView.AnswerViewListener {
    protected static final int NUMBER_ANSWER_OPTION = 4;
    protected TextView mTextQuestionIndex;
    protected AnswerView[] mAnswerViews = new AnswerView[NUMBER_ANSWER_OPTION];

    public AnswerBaseLayout(Context context, AttributeSet attrs) {
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
    public void onAnswerViewStateChanged(AnswerView answerView, AnswerState answerStateOld, AnswerState answerStateNew) {

    }
}
