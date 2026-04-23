package com.example.moresgame;

import java.util.HashMap;
import java.util.Map;

public class MorseCodeHelper {
    private static final Map<Character, String> textToMorseMap = new HashMap<>();
    private static final Map<String, Character> morseToTextMap = new HashMap<>();

    static {

        textToMorseMap.put('A', ".-");
        textToMorseMap.put('B', "-...");
        textToMorseMap.put('C', "-.-.");
        textToMorseMap.put('D', "-..");
        textToMorseMap.put('E', ".");
        textToMorseMap.put('F', "..-.");
        textToMorseMap.put('G', "--.");
        textToMorseMap.put('H', "....");
        textToMorseMap.put('I', "..");
        textToMorseMap.put('J', ".---");
        textToMorseMap.put('K', "-.-");
        textToMorseMap.put('L', ".-..");
        textToMorseMap.put('M', "--");
        textToMorseMap.put('N', "-.");
        textToMorseMap.put('O', "---");
        textToMorseMap.put('P', ".--.");
        textToMorseMap.put('Q', "--.-");
        textToMorseMap.put('R', ".-.");
        textToMorseMap.put('S', "...");
        textToMorseMap.put('T', "-");
        textToMorseMap.put('U', "..-");
        textToMorseMap.put('V', "...-");
        textToMorseMap.put('W', ".--");
        textToMorseMap.put('X', "-..-");
        textToMorseMap.put('Y', "-.--");
        textToMorseMap.put('Z', "--..");


        textToMorseMap.put('0', "-----");
        textToMorseMap.put('1', ".----");
        textToMorseMap.put('2', "..---");
        textToMorseMap.put('3', "...--");
        textToMorseMap.put('4', "....-");
        textToMorseMap.put('5', ".....");
        textToMorseMap.put('6', "-....");
        textToMorseMap.put('7', "--...");
        textToMorseMap.put('8', "---..");
        textToMorseMap.put('9', "----.");


        textToMorseMap.put('.', ".-.-.-");
        textToMorseMap.put(',', "--..--");
        textToMorseMap.put('?', "..--..");
        textToMorseMap.put('!', "-.-.--");
        textToMorseMap.put('\'', ".----.");
        textToMorseMap.put('"', ".-..-.");
        textToMorseMap.put(':', "---...");
        textToMorseMap.put(';', "-.-.-.");
        textToMorseMap.put('/', "-..-.");
        textToMorseMap.put('-', "-....-");
        textToMorseMap.put('(', "-.--.");
        textToMorseMap.put(')', "-.--.-");
        textToMorseMap.put('=', "-...-");
        textToMorseMap.put('+', ".-.-.");
        textToMorseMap.put('@', ".--.-.");

        // Space
        textToMorseMap.put(' ', "/");

        // Build reverse map
        for (Map.Entry<Character, String> entry : textToMorseMap.entrySet()) {
            morseToTextMap.put(entry.getValue(), entry.getKey());
        }
    }

    public static String textToMorse(String text) {
        StringBuilder morse = new StringBuilder();
        text = text.toUpperCase();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            String morseChar = textToMorseMap.get(c);
            if (morseChar != null) {
                morse.append(morseChar);
                if (i < text.length() - 1) {
                    morse.append(" ");
                }
            }
        }

        return morse.toString();
    }

    public static String morseToText(String morse) {
        StringBuilder text = new StringBuilder();
        String[] codes = morse.split(" ");

        for (String code : codes) {
            Character c = morseToTextMap.get(code);
            if (c != null) {
                text.append(c);
            }
        }

        return text.toString();
    }
}