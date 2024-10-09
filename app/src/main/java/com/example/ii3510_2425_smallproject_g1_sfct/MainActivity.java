package com.example.ii3510_2425_smallproject_g1_sfct;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView questionText;
    private RadioGroup choicesGroup;
    private Button submitButton;
    private QuizQuestion currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        questionText = findViewById(R.id.question_text);
        choicesGroup = findViewById(R.id.choices_group);
        submitButton = findViewById(R.id.submit_button);

        // Initialize the quiz question
        currentQuestion = new QuizQuestion("What is the capital of France?",
                new String[]{"Berlin", "Madrid", "Paris", "Lisbon"},
                2);

        // Set the question text
        questionText.setText(currentQuestion.getQuestion());

        // Set the options in the RadioGroup
        String[] answers = currentQuestion.getAnswers();
        ((RadioButton) choicesGroup.getChildAt(0)).setText(answers[0]);
        ((RadioButton) choicesGroup.getChildAt(1)).setText(answers[1]);
        ((RadioButton) choicesGroup.getChildAt(2)).setText(answers[2]);
        ((RadioButton) choicesGroup.getChildAt(3)).setText(answers[3]);

        // Handle submit button click
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected radio button ID
                int selectedRadioButtonId = choicesGroup.getCheckedRadioButtonId();

                // Check which RadioButton was selected
                if (selectedRadioButtonId != -1) {
                    // Find the index of the selected answer
                    int selectedIndex = choicesGroup.indexOfChild(findViewById(selectedRadioButtonId));

                    // Check if the answer is correct
                    if (currentQuestion.isCorrectAnswer(selectedIndex)) {
                        Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // No answer selected
                    Toast.makeText(MainActivity.this, "Please select an answer.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
