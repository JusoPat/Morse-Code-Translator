package com.example.moresgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LevelSelectActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Button backButton = findViewById(R.id.backButton);
        Button level1Button = findViewById(R.id.level1Button);
        Button level2Button = findViewById(R.id.level2Button);
        Button level3Button = findViewById(R.id.level3Button);
        Button level4Button = findViewById(R.id.level4Button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        level1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz(1);
            }
        });

        level2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz(2);
            }
        });

        level3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz(3);
            }
        });

        level4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz(4);
            }
        });
    }

    private void startQuiz(int level) {
        Intent intent = new Intent(LevelSelectActivity.this, QuizActivity.class);
        intent.putExtra("LEVEL", level);
        startActivity(intent);
    }
}