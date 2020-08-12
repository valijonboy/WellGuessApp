package pop.uz.wellguessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pop.uz.wellguessapp.databinding.ActivityEntryTextBinding;
import pop.uz.wellguessapp.questions.QuestionEntryText;

import static pop.uz.wellguessapp.Constants.ENTRYTEXT_ANSWERS;
import static pop.uz.wellguessapp.Constants.ENTRYTOTAL_QUESTIONS;
import static pop.uz.wellguessapp.Constants.USER_NAME;

public class EntryTextActivity extends AppCompatActivity {

    private ActivityEntryTextBinding binding;

    ArrayList<QuestionEntryText> entryQuestionList;
    String answer;
    public String mUserName = null;
    int correctAnswer = 0;
    int questionId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEntryTextBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mUserName = getIntent().getStringExtra(USER_NAME);
        entryQuestionList = new ArrayList<>();

        setQuestions();
        QuestionEntryText question = entryQuestionList.get(questionId);
        getObject(question);

        binding.buttonEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.etFruit.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), getText(R.string.enter_your_answer), Toast.LENGTH_SHORT).show();
                } else if (questionId < entryQuestionList.size()) {
                    entryTextAnswer(questionId);

                    binding.etFruit.getText().clear();
                    questionId++;

                    if (questionId < entryQuestionList.size()) {
                        QuestionEntryText question1 = entryQuestionList.get(questionId);
                        getObject(question1);


                    } else if (questionId == entryQuestionList.size()) {
                        Intent intent = new Intent(getApplicationContext(), GuessQuestionActivity.class);
                        intent.putExtra("user_name", mUserName);
                        intent.putExtra("entrytext_answers", correctAnswer);
                        intent.putExtra("entrytotal_question", entryQuestionList.size());
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    private void setQuestions() {
        QuestionEntryText ananas = new QuestionEntryText(0, R.drawable.ananas, "ananas");
        QuestionEntryText apple = new QuestionEntryText(1, R.drawable.apple, "apple");
        QuestionEntryText cherry = new QuestionEntryText(2, R.drawable.cherry, "cherry");
        QuestionEntryText mango = new QuestionEntryText(3, R.drawable.mango, "mango");
        QuestionEntryText strawberry = new QuestionEntryText(4, R.drawable.strawberry, "strawberry");

        entryQuestionList.add(ananas);
        entryQuestionList.add(apple);
        entryQuestionList.add(cherry);
        entryQuestionList.add(mango);
        entryQuestionList.add(strawberry);
    }

    private void getObject(QuestionEntryText question) {

        binding.imgFruit.setImageResource(question.getImage());
    }

    private void entryTextAnswer(int questionId) {
        answer = binding.etFruit.getText().toString().toLowerCase();
        switch (questionId) {
            case 0:
                if (answer.equals(entryQuestionList.get(0).getCorrectAnswer())) {
                    correctAnswer++;
                    happyToast();

                } else {
                    sadnlyToast();
                }
                break;
            case 1:
                if (answer.equals(entryQuestionList.get(1).getCorrectAnswer())) {
                    correctAnswer++;
                    happyToast();

                } else {
                    sadnlyToast();
                }
                break;
            case 2:
                if (answer.equals(entryQuestionList.get(2).getCorrectAnswer())) {
                    correctAnswer++;
                    happyToast();

                } else {
                    sadnlyToast();
                }
                break;
            case 3:
                if (answer.equals(entryQuestionList.get(3).getCorrectAnswer())) {
                    correctAnswer++;
                    happyToast();

                } else {
                    sadnlyToast();
                }
                break;
            case 4:
                if (answer.equals(entryQuestionList.get(4).getCorrectAnswer())) {
                    correctAnswer++;
                    happyToast();

                } else {
                    sadnlyToast();
                }
                break;

        }
    }

    @SuppressLint("SetTextI18n")
    private void happyToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = Objects.requireNonNull(inflater).inflate(R.layout.activity_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));
        layout.setBackgroundColor(getResources().getColor(R.color.green));

        TextView text = layout.findViewById(R.id.text);
        text.setText("Wow, your answer is correct!");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 200);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    public void sadnlyToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = Objects.requireNonNull(inflater).inflate(R.layout.activity_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));
        layout.setBackgroundColor(getResources().getColor(R.color.red));

        TextView text = layout.findViewById(R.id.text);
        text.setText("Oops.. Your answer is incorrect!");


        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 200);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}