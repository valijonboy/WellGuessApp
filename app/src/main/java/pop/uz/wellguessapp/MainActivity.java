package pop.uz.wellguessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import pop.uz.wellguessapp.databinding.ActivityMainBinding;

import static pop.uz.wellguessapp.Constants.USER_NAME;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        showSystemUI();

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.etName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.enter_name), Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), GuessQuestionActivity.class);
                    intent.putExtra(USER_NAME, binding.etName.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}