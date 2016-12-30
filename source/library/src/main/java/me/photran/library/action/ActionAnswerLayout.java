package me.photran.library.action;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import me.photran.library.model.PossibleAnswers;

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

    void fillAnswer(@NonNull PossibleAnswers correctAnswer, @Nullable PossibleAnswers answersUserSelected);
}
