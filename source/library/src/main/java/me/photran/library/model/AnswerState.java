package me.photran.library.model;

/**
 * Created by photran on 12/24/16.
 */

public enum AnswerState {
    DEFAULT(0), USER_CHECKED(1), CORRECT_ANSWER(2);

    private int mState;

    AnswerState(int state) {
        mState = state;
    }

    int getState() {
        return mState;
    }
}
