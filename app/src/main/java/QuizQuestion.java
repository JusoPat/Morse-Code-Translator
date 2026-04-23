package com.example.moresgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuizQuestion {
    public String morse;
    public String answer;
    public String text;
    public ArrayList<String> options;

    private static final String[] LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
    private static final String[] WORDS = {
            "HELLO", "WORLD", "MORSE", "CODE", "GAME", "LEARN", "TEST", "SIGNAL",
            "DOT", "DASH", "SOUND", "RADIO", "SHIP", "PILOT", "HELP", "TIME", "SPACE",
            "STAR", "MOON", "SUN", "EARTH", "FIRE", "WATER", "STONE", "SQUARE", "TRIANGLE",
            "PIZZA", "DOUGHNUT", "CAKE", "RACECAR", "BEAR", "CAT", "DOG", "FISH", "RUBY",
            "PEPPERONI", "CHEESE", "BACKFLIP", "SQUID", "CIRCLE", "HEAT", "COLD", "COOL",
            "CAR", "BUS", "BOAT", "PLANE", "TRAIN", "SALAD", "SKY", "DAY", "NIGHT", "PEN",
            "RED", "BLUE", "GREEN", "YELLOW", "BROWN", "WHITE", "BLACK", "PINK", "ORANGE", "GRAY",
            "HAND", "FOOT", "HEAD", "SHOULDERS", "KNEES", "TOES", "CHIN", "NAIL", "TOOTH", "SOUP",
            "LUCKY", "DANCE", "SHOES", "BOOTS", "LION", "TIGER", "CRAB", "PIG", "BALANCE", "HONEY",
            "ISLAND", "APPLE", "BANANA", "GRAPE", "CHERRY", "TUNA", "SHARK", "WILLIAM", "RICHARD",
            "PHIL", "AMERICA", "JAPAN", "RUSSIA", "CHINA", "MEXICO", "ASIA", "AFRICA"
    };
    private static final String[] SENTENCES = {
            "GOOD MORNING UNIVERSE", "MORSE CODE IS FUN", "WHERE DID YOU LEARN TO DRIVE",
            "PRACTICE MAKES PERFECT", "JUST KEEP SWIMMING", "NEVER GIVE UP WITHOUT A FIGHT",
            "YOU CAN DO IT", "EYES ON THE PRIZE", "HELLO POT MEET KETTLE",
            "IF YOU CAN READ THIS GOOD JOB", "REMEMBER TO BRUSH YOUR TEETH",
            "LIKE HAVING YOU CAKE AND EATING IT TOO", "REACH FOR THE SKY",
            "WHAT TIME IS IT", "STAR LIGHT STAR BRIGHT", "I NEVER LOSE", "YUMMY IN MY TUMMY",
            "FIELD TRIP TO THE ZOO", "GET READY TO DANCE", "IM TO TIRED TO THINK",
            "RISE AND SHINE SLEEPY HEAD", "TO INFINITY AND BEYOND", "WE HAVE A WINNER"
    };
    private static final String[][] FILL_BLANKS = {
            {"MORSE CODE USES _____ AND DASHES WHEN WRITING", "DOTS"},
            {"PRACTICE MAKES _____", "PERFECT"},
            {"LEARNING NEW _____ IS FUN", "THINGS"},
            {"DO NOT GIVE _____ ON YOU DREAMS", "UP"},
            {"THE SKY IS _____", "BLUE"},
            {"KEEP _____ AND YOU WILL SUCCEED", "TRYING"},
            {"THE GRASS IS _____ ON THE OTHER SIDE OF THE FENCE", "GREENER"},
            {"BIRDS OF A _____ FLOCK TOGETHER", "FEATHER"},
            {"WHEN THE GOING GETS TOUGH THE _____ GET GOING", "TOUGH"},
            {"ALL WORK AND NO _____ MAKE ME A DULL BOY", "PLAY"},
            {"_____ DROP AND ROLE", "STOP"},
            {"STOP _____ AND ROLE", "DROP"},
            {"STOP DROP AND _____", "ROLE"},
            {"THAT TIGER IS MISSING ITS _____", "STRIPES"},
            {"I WANT TO _____ TO PLAY THE XYLOPHONE", "LEARN"},
            {"WE CAN DO _____ BY WE WORK TOGETHER", "ANYTHING"},
            {"A PATTERN IS A _____ SEQUENCE", "REPEATING"},
            {"IT HAS NOT STOPED RAINING ALL _____", "DAY"},
            {"AIR WATER EARTH _____", "FIRE"},
            {"EARTH FIRE AIR _____", "WATER"},
            {"KEEP UP THE HARD _____", "WORK"},
            {"PEOPLE NEED _____ TO BREATH", "AIR"},
            {"THE FUTURE AND THE _____ ARE CONNECTED", "PAST"},
            {"IF YOU HAPPY AND YOU KNOW IT _____ HANDS", "CLAP"}
    };

    public static QuizQuestion generate(int level) {
        QuizQuestion q = new QuizQuestion();
        Random random = new Random();

        if (level == 1) {
            String letter = LETTERS[random.nextInt(LETTERS.length)];
            q.morse = MorseCodeHelper.textToMorse(letter);
            q.answer = letter;
            q.options = getRandomOptions(letter, LETTERS, 4);
        } else if (level == 2) {
            String word = WORDS[random.nextInt(WORDS.length)];
            q.morse = MorseCodeHelper.textToMorse(word);
            q.answer = word;
            q.options = getRandomOptions(word, WORDS, 4);
        } else if (level == 3) {
            String sentence = SENTENCES[random.nextInt(SENTENCES.length)];
            q.morse = MorseCodeHelper.textToMorse(sentence);
            q.answer = sentence;
            q.options = getRandomOptions(sentence, SENTENCES, 4);
        } else if (level == 4) {
            String[] fillBlank = FILL_BLANKS[random.nextInt(FILL_BLANKS.length)];
            q.text = fillBlank[0];
            q.answer = fillBlank[1];
            q.morse = MorseCodeHelper.textToMorse(fillBlank[1]);
        }

        return q;
    }

    private static ArrayList<String> getRandomOptions(String correct, String[] pool, int count) {
        ArrayList<String> options = new ArrayList<>();
        options.add(correct);

        ArrayList<String> available = new ArrayList<>();
        for (String item : pool) {
            if (!item.equals(correct)) {
                available.add(item);
            }
        }

        Collections.shuffle(available);
        for (int i = 0; i < Math.min(count - 1, available.size()); i++) {
            options.add(available.get(i));
        }

        Collections.shuffle(options);
        return options;
    }
}