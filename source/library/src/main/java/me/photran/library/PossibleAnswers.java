package me.photran.library;

/**
 * Created by photran on 12/24/16.
 */

public enum PossibleAnswers {
    A("A"), B("B"), C("C"), D("D");

    private String mAnswer;

    PossibleAnswers(String answer) {
        mAnswer = answer;
    }

    String getAnswer() {
        return mAnswer;
    }
}
