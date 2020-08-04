package pop.uz.wellguessapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GuessQuestionActivity extends AppCompatActivity  {


    public static final String USER_NAME = "";
    public static final String TOTAL_QUESTIONS = "";
    public static final String CORRECT_ANSWERS = "";

    RadioButton rb_animal, rb_bird, rb_insect, radio;
    RadioGroup radioGroup;
    CheckBox checkBox1, checkBox2, checkBox3;
    Button button_submit;
    ImageView imageView;
    Context context;
    ArrayList<Question> questionsList = new ArrayList<>();
    int position = 0;
    int questionId = -1;
    public String mUserName = null;
    public int correct = 0;
    String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_question);

        context = this;
        rb_animal = findViewById(R.id.rb_animal);
        rb_bird = findViewById(R.id.rb_bird);
        rb_insect = findViewById(R.id.rb_insect);
        radioGroup = findViewById(R.id.radioGroup);
        checkBox1 = findViewById(R.id.checkbox1);
        checkBox2 = findViewById(R.id.checkbox2);
        checkBox3 = findViewById(R.id.checkbox3);
        imageView = findViewById(R.id.image_view);
        button_submit = findViewById(R.id.button_submit);

        mUserName = getIntent().getStringExtra(USER_NAME);
        if (getIntent() != null){
            questionId = 0;
            position = 1;
        }

        setQuestion();
        Question question = questionsList.get(questionId);
        getObject(question);

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup = findViewById(R.id.radioGroup);
                radio = findViewById(radioGroup.getCheckedRadioButtonId());

                if (radio == null){
                    Toast.makeText(context, "Please, select your answer!", Toast.LENGTH_SHORT).show();
                }
                else if (position <= questionsList.size() && radio  != null){

                    if (position == 1){
                        questionId++;
                        position++;
                    }
                    Question question = questionsList.get(questionId);
                    getObject(question);
                    checkBoxing();
                    checkAnswer(question);

                    radioGroup.clearCheck();
                    questionId++;
                    position++;
                }
                else {
                    questionsList.size();
                    Intent intent = new Intent(context, ResultActivity.class);
                   intent.putExtra(USER_NAME, mUserName );
                   intent.putExtra(CORRECT_ANSWERS, correct);
                   intent.putExtra(TOTAL_QUESTIONS, questionsList.size());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void setQuestion(){

        Question bear = new Question(0, R.drawable.white_bear, "ANIMAL");
        Question eagle = new Question(1, R.drawable.eagle, "BIRD");
        Question hippopotamus = new Question(2, R.drawable.begemot, "ANIMAL");
        Question straus = new Question(3, R.drawable.straus, "BIRD");

        questionsList.add(bear);
        questionsList.add(eagle);
        questionsList.add(hippopotamus);
        questionsList.add(straus);
    }

    private void getObject(Question question){

        imageView.setImageResource(question.getImage());
    }

    private void checkBoxing(){
        String yourVote = radio.getText().toString();
        String checkBoxChoices = "";

        if (checkBox1.isChecked()){
            checkBoxChoices += checkBox1.getText().toString() + "\tYES\n";
        }
        else {
            checkBoxChoices += checkBox1.getText().toString() + "\tNO\n";
        }
        if (checkBox2.isChecked()){
            checkBoxChoices += checkBox2.getText().toString() + "\tYES\n";
        }
        else {
            checkBoxChoices += checkBox2.getText().toString() + "\tNO\n";
        }
        if (checkBox3.isChecked()){
            checkBoxChoices += checkBox3.getText().toString() + "\tYES\n";
        }
        else {
            checkBoxChoices += checkBox3.getText().toString() + "\tNO\n";
        }
        Toast.makeText(context, "Selected RadioButton is:" + yourVote + "\n CheckBox Choices: \n " + checkBoxChoices, Toast.LENGTH_LONG).show();
    }

    private void checkAnswer(Question question){
        answer = radio.getText().toString();
        if (answer.equals(question.getCorrectAnswer())){
            correct++;
        }
    }
}
