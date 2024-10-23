package com.example.ii3510_2425_smallproject_g1_sfct;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LevelSelectionActivity extends AppCompatActivity {

    private Button easyButton, mediumButton, hardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection); // Ensure the layout exists

        easyButton = findViewById(R.id.easy_button);
        mediumButton = findViewById(R.id.medium_button);
        hardButton = findViewById(R.id.hard_button);

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz("easy");
            }
        });

        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz("medium");
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz("hard");
            }
        });
    }

    private void startQuiz(String level) {
        Intent intent = new Intent(LevelSelectionActivity.this, QuizActivity.class);
        intent.putExtra("LEVEL", level);  // Assuming you're passing the selected level
        String userName = getIntent().getStringExtra("USER_NAME");
        intent.putExtra("USER_NAME", userName);  // Pass the user name to QuizActivity
        startActivity(intent);

    }
}
