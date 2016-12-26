package photran.me.answersheet;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import me.photran.library.AnswerLayout;
import me.photran.library.PossibleAnswers;

public class HomeActivity extends AppCompatActivity implements AnswerLayout.AnswerLayoutListener {

    private AnswerLayout answerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        answerLayout = (AnswerLayout) findViewById(R.id.activity_home_answer_layout);
        answerLayout.setQuestionIndex(8);
        answerLayout.setAnswerLayoutListener(this);
    }

    public void onResultClicked(View view) {
        PossibleAnswers possibleAnswers = answerLayout.getPossibleAnswers();
        if (possibleAnswers == null) {
            Toast.makeText(this, "User not answer", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "User answer: " + possibleAnswers.name(), Toast.LENGTH_LONG).show();
        }
    }

    public void onSetAnswerClicked(View view) {
        answerLayout.fillAnswer(PossibleAnswers.C,
                null);
    }

    @Override
    public void fistAnswerSelected(@NonNull PossibleAnswers answers) {
        Toast.makeText(this, "fist answer user selected " + answers.name(), Toast.LENGTH_LONG).show();
    }
}
