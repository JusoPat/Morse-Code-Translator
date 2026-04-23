package com.example.moresgame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {
    private int level;
    private int currentQuestion = 0;
    private int score = 0;
    private final int TOTAL_QUESTIONS = 10;
    private QuizQuestion question;
    private String userAnswer = "";
    private HashSet<String> usedQuestions = new HashSet<>();

    private TextView questionCountText;
    private TextView scoreText;
    private TextView morseText;
    private TextView sentenceText;
    private TextView instructionText;
    private LinearLayout optionsLayout;
    private EditText morseInput;
    private Button submitButton;
    private TextView feedbackText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
         
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        level = getIntent().getIntExtra("LEVEL", 1);

        questionCountText = findViewById(R.id.questionCountText);
        scoreText = findViewById(R.id.scoreText);
        morseText = findViewById(R.id.morseText);
        sentenceText = findViewById(R.id.sentenceText);
        instructionText = findViewById(R.id.instructionText);
        optionsLayout = findViewById(R.id.optionsLayout);
        morseInput = findViewById(R.id.morseInput);
        submitButton = findViewById(R.id.submitButton);
        feedbackText = findViewById(R.id.feedbackText);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        loadQuestion();
    }

    private void loadQuestion() {
        int attempts = 0;
        do {
            question = QuizQuestion.generate(level);
            attempts++;
        } while (usedQuestions.contains(question.answer) && attempts < 50);

        usedQuestions.add(question.answer);

        updateUI();
        feedbackText.setVisibility(View.GONE);
        userAnswer = "";
    }

    private void updateUI() {
        questionCountText.setText("Question " + (currentQuestion + 1) + "/" + TOTAL_QUESTIONS);
        scoreText.setText("Score: " + score);

        if (level == 4) {
            morseText.setVisibility(View.VISIBLE);
            sentenceText.setVisibility(View.GONE);
            instructionText.setVisibility(View.VISIBLE);
            optionsLayout.setVisibility(View.GONE);
            morseInput.setVisibility(View.VISIBLE);


            String[] parts = question.text.split("_____");

            StringBuilder morseQuestion = new StringBuilder();

            if (parts.length > 0) {
                morseQuestion.append(MorseCodeHelper.textToMorse(parts[0].trim()));
                morseQuestion.append(" ");
            }

            morseQuestion.append("____________");

            if (parts.length > 1) {
                // Add second part in morse
                morseQuestion.append(" ");
                morseQuestion.append(MorseCodeHelper.textToMorse(parts[1].trim()));
            }

            String displayText = morseQuestion.toString();
            SpannableString spannableString = new SpannableString(displayText);
            int startIndex = displayText.indexOf("____________");
            if (startIndex != -1) {
                spannableString.setSpan(new UnderlineSpan(), startIndex, startIndex + 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            morseText.setText(spannableString);
            instructionText.setText("Fill in the blank in Morse code:");
            morseInput.setText("");
            morseInput.setHint("Type morse code here...");
        } else {
            // Levels 1-3: Show Morse code with multiple choice
            morseText.setVisibility(View.VISIBLE);
            sentenceText.setVisibility(View.GONE);
            instructionText.setVisibility(View.GONE);
            optionsLayout.setVisibility(View.VISIBLE);
            morseInput.setVisibility(View.GONE);

            morseText.setText(question.morse);

            optionsLayout.removeAllViews();
            for (final String option : question.options) {
                Button optionButton = new Button(this);
                optionButton.setText(option);
                optionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userAnswer = option;
                        highlightSelectedOption(optionButton);
                    }
                });
                optionsLayout.addView(optionButton);
            }
        }
    }

    private void highlightSelectedOption(Button selectedButton) {
        for (int i = 0; i < optionsLayout.getChildCount(); i++) {
            Button btn = (Button) optionsLayout.getChildAt(i);
            btn.setBackgroundColor(getResources().getColor(android.R.color.white));
        }
        selectedButton.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
    }

    private void checkAnswer() {
        if (level == 4) {
            userAnswer = morseInput.getText().toString().trim();
        }

        if (userAnswer.isEmpty()) return;

        boolean correct;
        if (level == 4) {
            // For level 4, compare the morse code directly
            correct = userAnswer.equals(question.morse);
        } else {
            correct = userAnswer.equalsIgnoreCase(question.answer);
        }

        if (correct) {
            score++;
            feedbackText.setText("Correct!");
            feedbackText.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
        } else {
            feedbackText.setText("Wrong! The answer was: " + question.morse + " (" + question.answer + ")");
            feedbackText.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        }

        feedbackText.setVisibility(View.VISIBLE);
        submitButton.setEnabled(false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentQuestion + 1 < TOTAL_QUESTIONS) {
                    currentQuestion++;
                    loadQuestion();
                    submitButton.setEnabled(true);
                } else {
                    showResults();
                }
            }
        }, 2000);
    }
    private void showResults() {
        Intent intent = new Intent(QuizActivity.this, ResultsActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL", TOTAL_QUESTIONS);
        intent.putExtra("LEVEL", level);
        startActivity(intent);
        finish();
    }
}