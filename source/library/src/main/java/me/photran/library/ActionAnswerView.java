package me.photran.library;

import android.support.annotation.NonNull;

/**
 * Created by photran on 12/25/16.
 */

public interface ActionAnswerView {
    void reset();

    void setCorrectAnswerState();

    PossibleAnswers getAnswer();

    boolean isPossibleAnswers(@NonNull PossibleAnswers answers);

    void disableEvent();
}
