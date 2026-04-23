package com.example.moresgame;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText inputText;
    private TextView outputText;
    private ImageButton toggleButton;
    private Button startQuizButton;
    private boolean isTextToMorse = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();

        inputText = findViewById(R.id.inputText);
        outputText = findViewById(R.id.outputText);
    }
        toggleButton = findViewById(R.id.toggleButton);
        startQuizButton = findViewById(R.id.startQuizButton);

        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                translate(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMode();
            }
        });

        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LevelSelectActivity.class);
                startActivity(intent);
            }
        });
        Button translationsButton = findViewById(R.id.translationsButton);
        translationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TranslationsActivity.class);
                startActivity(intent);
            }
        });
    }
    private void translate(String input) {
        if (input.isEmpty()) {
            outputText.setText("Translation will appear here...");
            return;
        }

        String result;
        if (isTextToMorse) {
            result = MorseCodeHelper.textToMorse(input);
        } else {
            result = MorseCodeHelper.morseToText(input);
        }
        outputText.setText(result);
    }

    private void toggleMode() {
        isTextToMorse = !isTextToMorse;
        inputText.setText("");
        outputText.setText("Translation will appear here...");

        if (isTextToMorse) {
            inputText.setHint("Type your text here...");
        } else {
            inputText.setHint("Type morse code here (use spaces)...");
        }
    }
}