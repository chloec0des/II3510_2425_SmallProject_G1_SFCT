package com.example.ii3510_2425_smallproject_g1_sfct;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView questionText;
    private RadioGroup choicesGroup;
    private Button submitButton;

    private List<QuizQuestion> quizQuestions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        questionText = findViewById(R.id.question_text);
        choicesGroup = findViewById(R.id.choices_group);
        submitButton = findViewById(R.id.submit_button);

        // Initialize the quiz questions
        quizQuestions = new ArrayList<>();
        quizQuestions.add(new QuizQuestion("What is the capital of France?",
                new String[]{"Berlin", "Madrid", "Paris", "Lisbon"},
                2));
        quizQuestions.add(new QuizQuestion("Which planet is known as the Red Planet?",
                new String[]{"Earth", "Mars", "Jupiter", "Saturn"},
                1));
        quizQuestions.add(new QuizQuestion("Who wrote 'Hamlet'?",
                new String[]{"Charles Dickens", "J.K. Rowling", "Mark Twain", "William Shakespeare"},
                3));

        // Set the first question
        setQuestion();

        // Handle submit button click
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected radio button ID
                int selectedRadioButtonId = choicesGroup.getCheckedRadioButtonId();

                // Check if an answer was selected
                if (selectedRadioButtonId != -1) {
                    // Find the index of the selected answer
                    int selectedIndex = choicesGroup.indexOfChild(findViewById(selectedRadioButtonId));

                    // Check if the answer is correct
                    if (quizQuestions.get(currentQuestionIndex).isCorrectAnswer(selectedIndex)) {
                        score++;
                        Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
                    }

                    // Move to the next question
                    currentQuestionIndex++;

                    // Check if we have more questions
                    if (currentQuestionIndex < quizQuestions.size()) {
                        setQuestion();
                    } else {
                        // Quiz finished, show final score
                        Toast.makeText(MainActivity.this, "Quiz finished! Your score: " + score + "/" + quizQuestions.size(), Toast.LENGTH_LONG).show();
                        resetQuiz();
                    }
                } else {
                    // No answer selected
                    Toast.makeText(MainActivity.this, "Please select an answer.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Set the question and answers to the UI
    private void setQuestion() {
        QuizQuestion currentQuestion = quizQuestions.get(currentQuestionIndex);
        questionText.setText(currentQuestion.getQuestion());

        String[] answers = currentQuestion.getAnswers();
        ((RadioButton) choicesGroup.getChildAt(0)).setText(answers[0]);
        ((RadioButton) choicesGroup.getChildAt(1)).setText(answers[1]);
        ((RadioButton) choicesGroup.getChildAt(2)).setText(answers[2]);
        ((RadioButton) choicesGroup.getChildAt(3)).setText(answers[3]);

        // Clear the previous selection
        choicesGroup.clearCheck();
    }

    // Reset the quiz
    private void resetQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        setQuestion();
    }
}
