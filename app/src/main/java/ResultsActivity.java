package com.example.moresgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        int score = getIntent().getIntExtra("SCORE", 0);
        int total = getIntent().getIntExtra("TOTAL", 10);
        final int level = getIntent().getIntExtra("LEVEL", 1);

        TextView scoreText = findViewById(R.id.scoreText);
        TextView messageText = findViewById(R.id.messageText);
        Button tryAgainButton = findViewById(R.id.tryAgainButton);
        Button changeLevelButton = findViewById(R.id.changeLevelButton);
        Button homeButton = findViewById(R.id.homeButton);

        scoreText.setText(score + "/" + total);

        String message;
        if (score == total) {
            message = "Perfect Score!";
        } else if (score >= total * 0.7) {
            message = "Great Job!";
        } else {
            message = "Keep Practicing!";
        }
        messageText.setText(message);

        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this, QuizActivity.class);
                intent.putExtra("LEVEL", level);
                startActivity(intent);
                finish();
            }
        });

        changeLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this, LevelSelectActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}