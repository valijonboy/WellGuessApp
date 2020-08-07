package pop.uz.wellguessapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import pop.uz.wellguessapp.databinding.ActivityGuessQuestionBinding;

import static pop.uz.wellguessapp.Constants.CORRECT_ANSWERS;
import static pop.uz.wellguessapp.Constants.INCORRECT_ANSWERS;
import static pop.uz.wellguessapp.Constants.TOTAL_QUESTIONS;
import static pop.uz.wellguessapp.Constants.USER_NAME;

public class GuessQuestionActivity extends AppCompatActivity {

    RadioButton radio;
    Context context;
    ArrayList<Question> questionsList = new ArrayList<>();
    int position = 0;
    int questionId = -1;
    public String mUserName = null;
    public int correct = 0;
    String answer;
    private int incorrect = 0;

    private ActivityGuessQuestionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuessQuestionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        context = this;

        mUserName = getIntent().getStringExtra(USER_NAME);
        if (getIntent() != null) {
            questionId = 0;
            position = 1;
        }

        setQuestion();
        Question question = questionsList.get(questionId);
        getObject(question);

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio = findViewById(binding.radioGroup.getCheckedRadioButtonId());

                if (radio == null) {
                    Toast.makeText(context, "Please, select your answer!", Toast.LENGTH_SHORT).show();
                } else if (position <= questionsList.size() && radio != null) {

                    if (position == 1) {
                        questionId++;
                        position++;
                    }
                    Question question = questionsList.get(questionId);
                    getObject(question);
                    checkBoxing();
                    checkAnswer(question);

                    binding.radioGroup.clearCheck();
                    questionId++;
                    position++;
                } else {
                    questionsList.size();
                    Intent intent = new Intent(context, ResultActivity.class);
                    intent.putExtra(USER_NAME, mUserName);
                    intent.putExtra(CORRECT_ANSWERS, correct);
                    intent.putExtra(INCORRECT_ANSWERS, incorrect);
                    intent.putExtra(TOTAL_QUESTIONS, questionsList.size());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void setQuestion() {

        Question bear = new Question(0, R.drawable.white_bear,"ANIMAL");
        Question eagle = new Question(1, R.drawable.eagle, "BIRD");
        Question hippopotamus = new Question(2, R.drawable.begemot, "ANIMAL");
        Question straus = new Question(3, R.drawable.straus, "BIRD");

        questionsList.add(bear);
        questionsList.add(eagle);
        questionsList.add(hippopotamus);
        questionsList.add(straus);
    }

    private void getObject(Question question) {

        binding.imageView.setImageResource(question.getImage());
    }

    private void checkBoxing() {
        String yourVote = radio.getText().toString();
        String checkBoxChoices = "";

        if (binding.checkbox1.isChecked()) {
            checkBoxChoices += binding.checkbox1.getText().toString() + "\tYES\n";
        } else {
            checkBoxChoices += binding.checkbox1.getText().toString() + "\tNO\n";
        }
        if (binding.checkbox2.isChecked()) {
            checkBoxChoices += binding.checkbox2.getText().toString() + "\tYES\n";
        } else {
            checkBoxChoices += binding.checkbox2.getText().toString() + "\tNO\n";
        }
        if (binding.checkbox3.isChecked()) {
            checkBoxChoices += binding.checkbox3.getText().toString() + "\tYES\n";
        } else {
            checkBoxChoices += binding.checkbox3.getText().toString() + "\tNO\n";
        }
        Toast.makeText(context, "Selected RadioButton is:" + yourVote + "\n CheckBox Choices: \n " + checkBoxChoices, Toast.LENGTH_LONG).show();
    }

    private void checkAnswer(Question question) {
        answer = radio.getText().toString();
        if (answer.equals(question.getCorrectAnswer())) {
            correct++;
        } else {
            incorrect++;
        }
    }

}
