package pop.uz.wellguessapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import pop.uz.wellguessapp.databinding.ActivityResultBinding;

import static pop.uz.wellguessapp.Constants.CHECKCORRECT_ANSWERS;
import static pop.uz.wellguessapp.Constants.CORRECT_ANSWERS;
import static pop.uz.wellguessapp.Constants.TOTAL_QUESTIONS;
import static pop.uz.wellguessapp.Constants.USER_NAME;

public class ResultActivity extends AppCompatActivity {

    private ActivityResultBinding binding;
    String userName;
    int totalQuestions, correctAnswers, checkcorrectAnswers;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        showSystemUI();

        userName = getIntent().getStringExtra(USER_NAME);
        binding.tvName.setText(userName);

        totalQuestions = getIntent().getIntExtra(TOTAL_QUESTIONS, 0);
        correctAnswers = getIntent().getIntExtra(CORRECT_ANSWERS, 0);
        checkcorrectAnswers = getIntent().getIntExtra(CHECKCORRECT_ANSWERS, 0);


        binding.tvScore.setText(String.format("Your score is %d out of %d", correctAnswers, totalQuestions));
        binding.tvCheckScore.setText(String.format("Your checkscore is %d out of %d", checkcorrectAnswers, totalQuestions));

        binding.buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}