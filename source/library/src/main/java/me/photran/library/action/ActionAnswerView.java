package me.photran.library.action;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import me.photran.library.model.AnswerState;
import me.photran.library.model.PossibleAnswers;

/**
 * Created by photran on 12/25/16.
 */

public interface ActionAnswerView {
    void reset();

    PossibleAnswers getAnswer();

    void handleAnswer(@NonNull PossibleAnswers correctAnswer, @Nullable PossibleAnswers answersUserSelected);

    void changeViewByState(@NonNull AnswerState state);
}
