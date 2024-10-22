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

        userNameInput = findViewById(R.id.userName);  // Assuming this is the ID of the EditText field for entering the user's name
        playButton = findViewById(R.id.play_button);  // Play button ID

        // Set up the click listener for the Play button
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the user name from the input field
                String userName = userNameInput.getText().toString();

                // Create an intent to navigate to LevelSelectionActivity
                Intent intent = new Intent(MainActivity.this, LevelSelectionActivity.class);

                // Pass the user's name to the next activity
                intent.putExtra("USER_NAME", userName);

                // Start the LevelSelectionActivity
                startActivity(intent);
            }
        });
    }
}
