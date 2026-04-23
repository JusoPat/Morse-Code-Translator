package com.example.moresgame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TranslationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translations);

        // Hide action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Button backButton = findViewById(R.id.backButton);
        LinearLayout translationsContainer = findViewById(R.id.translationsContainer);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Add all translations to the container
        addTranslations(translationsContainer);
    }

    private void addTranslations(LinearLayout container) {
        // Letters
        addSectionHeader(container, "LETTERS");
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        for (String letter : letters) {
            String morse = MorseCodeHelper.textToMorse(letter);
            addTranslationRow(container, letter, morse);
        }

        // Numbers
        addSectionHeader(container, "NUMBERS");
        String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        for (String number : numbers) {
            String morse = MorseCodeHelper.textToMorse(number);
            addTranslationRow(container, number, morse);
        }

        // Punctuation
        addSectionHeader(container, "PUNCTUATION");
        String[][] punctuation = {
                {".", "Period"},
                {",", "Comma"},
                {"?", "Question Mark"},
                {"!", "Exclamation"},
                {"'", "Apostrophe"},
                {"\"", "Quotation"},
                {":", "Colon"},
                {";", "Semicolon"},
                {"/", "Slash"},
                {"-", "Hyphen"},
                {"(", "Open Paren"},
                {")", "Close Paren"},
                {"=", "Equal"},
                {"+", "Plus"},
                {"@", "At Sign"}
        };

        for (String[] punct : punctuation) {
            String morse = MorseCodeHelper.textToMorse(punct[0]);
            addTranslationRow(container, punct[0] + " (" + punct[1] + ")", morse);
        }
    }

    private void addSectionHeader(LinearLayout container, String title) {
        TextView header = new TextView(this);
        header.setText(title);
        header.setTextSize(20);
        header.setTextColor(getResources().getColor(android.R.color.black));
        header.setTypeface(null, android.graphics.Typeface.BOLD);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, dpToPx(20), 0, dpToPx(10));
        header.setLayoutParams(params);
        header.setPadding(0, dpToPx(10), 0, dpToPx(10));

        container.addView(header);
    }

    private void addTranslationRow(LinearLayout container, String character, String morse) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        rowParams.setMargins(0, dpToPx(5), 0, dpToPx(5));
        row.setLayoutParams(rowParams);
        row.setPadding(dpToPx(15), dpToPx(12), dpToPx(15), dpToPx(12));
        row.setBackgroundColor(getResources().getColor(android.R.color.white));

        // Character text
        TextView charText = new TextView(this);
        charText.setText(character);
        charText.setTextSize(18);
        charText.setTextColor(getResources().getColor(android.R.color.black));

        LinearLayout.LayoutParams charParams = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1
        );
        charText.setLayoutParams(charParams);

        // Equals sign
        TextView equalsText = new TextView(this);
        equalsText.setText("=");
        equalsText.setTextSize(18);
        equalsText.setTextColor(getResources().getColor(android.R.color.darker_gray));

        LinearLayout.LayoutParams equalsParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        equalsParams.setMargins(dpToPx(10), 0, dpToPx(10), 0);
        equalsText.setLayoutParams(equalsParams);

        // Morse code text
        TextView morseText = new TextView(this);
        morseText.setText(morse);
        morseText.setTextSize(18);
        morseText.setTextColor(getResources().getColor(android.R.color.black));

        LinearLayout.LayoutParams morseParams = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                2
        );
        morseText.setLayoutParams(morseParams);

        row.addView(charText);
        row.addView(equalsText);
        row.addView(morseText);

        container.addView(row);
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}