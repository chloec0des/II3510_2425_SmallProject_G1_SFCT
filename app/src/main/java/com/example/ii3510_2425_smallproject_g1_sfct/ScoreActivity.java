package com.example.ii3510_2425_smallproject_g1_sfct;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    private TextView scoreText, resultMessage;
    private Button playAgainButton, exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreText = findViewById(R.id.score_text);
        resultMessage = findViewById(R.id.result_message);  // Add result message TextView
        playAgainButton = findViewById(R.id.play_again_button);
        exitButton = findViewById(R.id.exit_button);

        // Get the score and user name from the Intent
        int score = getIntent().getIntExtra("SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);
        String userName = getIntent().getStringExtra("USER_NAME");

        // Set the score text using String.format for better handling
        scoreText.setText(String.format("%s, your score: %d / %d", userName, score, totalQuestions));

        // Determine the message based on score
        if (score >= 4) {
            resultMessage.setText(String.format("Congrats %s! You did great!", userName));
        } else {
            resultMessage.setText(String.format("%s, you should try again to get better!", userName));
        }

        // Play again button restarts the quiz
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, MainActivity.class);  // Correct activity reference
                intent.putExtra("USER_NAME", userName);  // Pass the user’s name
                startActivity(intent);
                finish();
            }
        });

        // Exit button closes the app
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Close the activity and exit the app
            }
        });
    }
}
