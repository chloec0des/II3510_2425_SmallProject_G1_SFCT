package com.example.ii3510_2425_smallproject_g1_sfct;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView questionText;
    private RadioGroup choicesGroup;
    private Button submitButton, quitButton;
    private List<QuizQuestion> quizQuestions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        // Retrieve the selected level
        level = getIntent().getStringExtra("LEVEL");

        questionText = findViewById(R.id.question_text);
        choicesGroup = findViewById(R.id.choices_group);
        submitButton = findViewById(R.id.submit_button);
        quitButton = findViewById(R.id.quit_button);

        quizQuestions = generateQuestions(level); // Generate questions based on level
        setQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedRadioButtonId = choicesGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    int selectedIndex = choicesGroup.indexOfChild(findViewById(selectedRadioButtonId));
                    if (quizQuestions.get(currentQuestionIndex).isCorrectAnswer(selectedIndex)) {
                        score++;
                    }
                    currentQuestionIndex++;
                    if (currentQuestionIndex < quizQuestions.size()) {
                        setQuestion();
                    } else {
                        showScore();
                    }
                }
            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the homepage (MainActivity)
                Intent intent = new Intent(QuizActivity.this, MainActivity.class);
                startActivity(intent);
                finish();  // Finish current activity so user cannot return to it
            }
        });

    }

    private List<QuizQuestion> generateQuestions(String level) {
        List<QuizQuestion> questions = new ArrayList<>();
        if (level == null) {
            level = "easy";  // Default to "easy" or handle as needed
        }
        if (level.equals("easy")) {
            questions.add(new QuizQuestion("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Lisbon"}, 2));
            questions.add(new QuizQuestion("Which of these is a fruit?", new String[]{"Carrot", "Avocado", "Broccoli", "Cucumber"}, 1));
            questions.add(new QuizQuestion("Which animal is known as man's best friend?", new String[]{"Dog", "Cat", "Rabbit", "Hamster"}, 0));
            questions.add(new QuizQuestion("How many legs does a spider have?", new String[]{"6", "8", "10", "12"}, 1));
            questions.add(new QuizQuestion("What color is the sky on a clear day?", new String[]{"White", "Grey", "Blue", "Yellow"}, 2)); // Added easy question
        } else if (level.equals("medium")) {
            questions.add(new QuizQuestion("Who wrote '1984'?", new String[]{"Orwell", "Shakespeare", "Hemingway", "Austen"}, 0));
            questions.add(new QuizQuestion("What is the speed of light?", new String[]{"300,000 km/s", "150,000 km/s", "100,000 km/s", "200,000 km/s"}, 0));
            questions.add(new QuizQuestion("What is the chemical symbol for water?", new String[]{"H2O", "CO2", "NaCl", "O2"}, 0)); // Added medium question
            questions.add(new QuizQuestion("Which country hosted the 2016 Summer Olympics?", new String[]{"Brazil", "China", "Russia", "Germany"}, 0)); // Added medium question
            questions.add(new QuizQuestion("How many continents are there on Earth?", new String[]{"5", "6", "7", "8"}, 2)); // Added medium question
        } else if (level.equals("hard")) {
            questions.add(new QuizQuestion("Who discovered penicillin?", new String[]{"Curie", "Fleming", "Einstein", "Newton"}, 1));
            questions.add(new QuizQuestion("What is the chemical symbol for gold?", new String[]{"Gd", "Au", "Ag", "Pt"}, 1));
            questions.add(new QuizQuestion("Which element has the atomic number 1?", new String[]{"Hydrogen", "Helium", "Oxygen", "Nitrogen"}, 0)); // Added hard question
            questions.add(new QuizQuestion("Who developed the theory of relativity?", new String[]{"Isaac Newton", "Albert Einstein", "Galileo Galilei", "Nikola Tesla"}, 1)); // Added hard question
            questions.add(new QuizQuestion("In what year did World War II end?", new String[]{"1939", "1942", "1945", "1948"}, 2)); // Added hard question
        }
        return questions;
    }

    private void setQuestion() {
        QuizQuestion currentQuestion = quizQuestions.get(currentQuestionIndex);
        questionText.setText(currentQuestion.getQuestion());
        String[] answers = currentQuestion.getAnswers();
        ((RadioButton) choicesGroup.getChildAt(0)).setText(answers[0]);
        ((RadioButton) choicesGroup.getChildAt(1)).setText(answers[1]);
        ((RadioButton) choicesGroup.getChildAt(2)).setText(answers[2]);
        ((RadioButton) choicesGroup.getChildAt(3)).setText(answers[3]);

        // Clear any previous selections
        choicesGroup.clearCheck();
    }

    // Show the score in the ScoreActivity
    private void showScore() {
        String userName = getIntent().getStringExtra("USER_NAME");

        Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
        intent.putExtra("SCORE", score);  // Pass the score
        intent.putExtra("TOTAL_QUESTIONS", quizQuestions.size());  // Pass the total number of questions
        intent.putExtra("USER_NAME", userName);  // Ensure USER_NAME is passed
        startActivity(intent);
        finish();

    }
}
