package pop.uz.wellguessapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


import pop.uz.wellguessapp.databinding.ActivityGuessQuestionBinding;

import static pop.uz.wellguessapp.Constants.USER_NAME;

public class GuessQuestionActivity extends AppCompatActivity {

    RadioButton radio;
    Context context;
    ArrayList<Question> questionsList = new ArrayList<>();
    int questionId = 0;
    public String mUserName = null;
    public int correct = 0;
    int checkCorrect = 0;
    String answer;

    private ActivityGuessQuestionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuessQuestionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        context = this;

        mUserName = getIntent().getStringExtra(USER_NAME);

        setQuestion();
        final Question question = questionsList.get(questionId);
        getObject(question);

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radio = findViewById(binding.radioGroup.getCheckedRadioButtonId());

                if (radio == null) {
                    Toast.makeText(context, R.string.select_answer, Toast.LENGTH_SHORT).show();
                } else if (questionId < questionsList.size() && radio != null) {

                    checkAnswer(questionId);
                    checkBoxAnswer(questionId);
                    checkBoxing();

                    binding.radioGroup.clearCheck();
                    questionId++;

                    if (questionId < questionsList.size()) {
                        Question question1 = questionsList.get(questionId);
                        getObject(question1);

                    } else {

                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        intent.putExtra("user_name", mUserName);
                        intent.putExtra("checkcorrect_answers", checkCorrect);
                        intent.putExtra("correct_answer", correct);
                        intent.putExtra("total_question", questionsList.size());
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    private void setQuestion() {

        Question bear = new Question(0, R.drawable.white_bear, "ANIMAL");
        Question eagle = new Question(1, R.drawable.duck, "BIRD");
        Question hippopotamus = new Question(2, R.drawable.begemot, "ANIMAL");
        Question straus = new Question(3, R.drawable.cricket_img, "INSECT");

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
            binding.checkbox1.setChecked(false);
        } else {
            checkBoxChoices += binding.checkbox1.getText().toString() + "\tNO\n";
        }
        if (binding.checkbox2.isChecked()) {
            checkBoxChoices += binding.checkbox2.getText().toString() + "\tYES\n";
            binding.checkbox2.setChecked(false);
        } else {
            checkBoxChoices += binding.checkbox2.getText().toString() + "\tNO\n";
        }
        if (binding.checkbox3.isChecked()) {
            checkBoxChoices += binding.checkbox3.getText().toString() + "\tYES\n";
            binding.checkbox3.setChecked(false);
        } else {
            checkBoxChoices += binding.checkbox3.getText().toString() + "\tNO\n";
        }
        Toast.makeText(context, getString(R.string.selec_radio) + yourVote + getString(R.string.checkbox_chois) + checkBoxChoices, Toast.LENGTH_LONG).show();
    }

    private void checkAnswer(int questionId) {

        answer = radio.getText().toString();

        if (questionsList.get(0).getCorrectAnswer().equals(answer) && questionId == 0)
            correct++;

        if (answer.equals(questionsList.get(1).getCorrectAnswer()) && questionId == 1)
            correct++;

        if (answer.equals(questionsList.get(2).getCorrectAnswer()) && questionId == 2)
            correct++;

        if (answer.equals(questionsList.get(3).getCorrectAnswer()) && questionId == 3)
            correct++;


    }

    private void checkBoxAnswer(int questionId) {
        switch (questionId) {
            case 0:
            case 2:
                if (!binding.checkbox1.isChecked() && binding.checkbox2.isChecked() && binding.checkbox3.isChecked())
                    checkCorrect++;
                break;
            case 1:
            case 3:
                if (binding.checkbox1.isChecked() && binding.checkbox2.isChecked() && binding.checkbox3.isChecked())
                    checkCorrect++;
                break;
        }
    }
}
