package pop.uz.wellguessapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GuessQuestionActivity extends AppCompatActivity  {

    private ArrayList<Question> questionsList =  new ArrayList<>();
    int questionNumber = 0;
    int questionId = -1;
    int correct = 1;
    private String mUserName = null;
    ProgressBar progressBar;
    TextView tv_progress, tv_question;
    RadioButton rb_option_one, rb_option_two, rb_option_three, rb_option_four, radio;
    ImageView iv_image;
    Button btn_submit;
    Context context;

    public static final String USER_NAME = "user_name";
    public static final String TOTAL_QUESTIONS = "total_questions";
    public static String CORRECT_ANSWERS = "correct_answer";

    private static final String QUESTION_TEXT = "Well, guess what color it is?";
    private static final String RED = "RED";
    private static final String BLUE = "BLUE";
    private static final String BLACK = "BLACK";
    private static final String WHITE = "WHITE";
    private static final String GREEN = "GREEN";
    private static final String GREY = "GREY";
    private static final String YELLOW = "YELLOW";
    private static final String ORANGE = "ORANGE";
    private static final String PURPLE = "PURPLE";

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_question);

        context = this;
        progressBar = findViewById(R.id.progressBar);
        tv_progress = findViewById(R.id.tv_progress);
        tv_question = findViewById(R.id.tv_question);
        iv_image = findViewById(R.id.iv_image);
        rb_option_one = findViewById(R.id.rb_option_one);
        rb_option_two = findViewById(R.id.rb_option_two);
        rb_option_three = findViewById(R.id.rb_option_three);
        rb_option_four = findViewById(R.id.rb_option_four);
        btn_submit = findViewById(R.id.btn_submit);

        mUserName = getIntent().getStringExtra(USER_NAME);
        setQuestion();

        if (getIntent() != null){
            questionId = 0;
            questionNumber = 1;
        }

        Question question = questionsList.get(questionId);
        getObject(question);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup = findViewById(R.id.radio_group);
                radio = findViewById(radioGroup.getCheckedRadioButtonId());

                if (radio ==  null){
                    Toast.makeText(context, "Please, select your answer!", Toast.LENGTH_SHORT).show();
                }
                else if (questionNumber <= questionsList.size() && radio != null){

                    if (questionNumber ==1){
                        questionId++;
                        questionNumber++;
                    }

                    Question question = questionsList.get(questionId);
                    getObject(question);
                    checkAnswer(question);

                    radioGroup.clearCheck();
                    questionId++;
                    questionNumber++;
                    correct++;

                    if (questionNumber == questionsList.size()){
                        btn_submit.setText(R.string.finish);
                    }else {
                        btn_submit.setText(R.string.go_next);
                    }

                }
                else if (questionNumber > questionsList.size()){
                    Intent intent = new Intent(context, ResultActivity.class);
                    intent.putExtra(USER_NAME, mUserName);
                    intent.putExtra(CORRECT_ANSWERS, correct);
                    intent.putExtra(TOTAL_QUESTIONS, questionsList.size());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void setQuestion(){
        Question question1 = new Question(1, QUESTION_TEXT, R.drawable.red_color, RED, BLACK, GREEN, YELLOW, 1);
        Question question2 = new Question(2, QUESTION_TEXT, R.drawable.black_color, BLUE, BLACK, ORANGE, YELLOW, 2);
        Question question3 = new Question(3, QUESTION_TEXT, R.drawable.yellow_color, ORANGE, WHITE, BLACK, YELLOW, 4);
        Question question4 = new Question(4, QUESTION_TEXT, R.drawable.white_color, RED, YELLOW, WHITE, GREY, 3);
        Question question5 = new Question(5, QUESTION_TEXT, R.drawable.blue_color, WHITE, BLACK, PURPLE, BLUE, 4);
        Question question6 = new Question(6, QUESTION_TEXT, R.drawable.purple_color, WHITE, YELLOW, ORANGE, PURPLE, 4);
        Question question7 = new Question(7, QUESTION_TEXT, R.drawable.green_color, GREEN, BLUE, YELLOW, RED, 1);
        Question question8 = new Question(8, QUESTION_TEXT, R.drawable.grey_color, PURPLE, GREEN, GREY, YELLOW, 3);
        Question question9 = new Question(9, QUESTION_TEXT, R.drawable.orange_color, RED, WHITE, GREEN, ORANGE, 4);

        questionsList.add(question1);
        questionsList.add(question2);
        questionsList.add(question3);
        questionsList.add(question4);
        questionsList.add(question5);
        questionsList.add(question6);
        questionsList.add(question7);
        questionsList.add(question8);
        questionsList.add(question9);
    }

    @SuppressLint("DefaultLocale")
    private void getObject(Question question){

        progressBar.setMax(questionsList.size());

        if (questionNumber == questionsList.size()){
            btn_submit.setText(R.string.finish);
        }else {
            btn_submit.setText(R.string.submit);
        }

        progressBar.setProgress(questionNumber);
        tv_progress.setText(String.format(" %d/%d", questionNumber, progressBar.getMax()));
        tv_question.setText(QUESTION_TEXT);
        iv_image.setImageResource(question.getImage());

        rb_option_one.setText(question.getOptionOne());
        rb_option_two.setText(question.getOptionTwo());
        rb_option_three.setText(question.getOptionThree());
        rb_option_four.setText(question.getOptionFour());
    }

    private void checkAnswer( Question question){

        CORRECT_ANSWERS = radio.getText().toString();
        if (CORRECT_ANSWERS.equals(String.valueOf(question.getCorrectAnswer()))){
            correct++;
        }
    }
}
