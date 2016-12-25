package me.photran.library;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by photran on 12/25/16.
 */

public interface ActionAnswerLayout {
    void setQuestionIndex(int index);

    /**
     * null - mean is no answer user selected
     *
     * @return possible answers
     */
    @Nullable
    PossibleAnswers getPossibleAnswers();

    void setCorrectAnswer(@NonNull PossibleAnswers correctAnswer);
}
