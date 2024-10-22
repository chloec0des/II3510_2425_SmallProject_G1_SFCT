package com.example.ii3510_2425_smallproject_g1_sfct;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText userNameInput;
    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameInput = findViewById(R.id.userName);  // Reference to the EditText field
        playButton = findViewById(R.id.play_button);

        // Play button starts the quiz
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameInput.getText().toString();  // Capture the entered name

                // Ensure userName is not empty
                if (!userName.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    intent.putExtra("USER_NAME", userName);  // Pass the user name to QuizActivity
                    startActivity(intent);
                } else {
                    userNameInput.setError("Please enter your name");
                }
            }
        });
    }
}
