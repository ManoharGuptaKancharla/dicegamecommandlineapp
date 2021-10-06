package com.greatlearning.interview.dicegame;

import com.greatlearning.interview.dicegame.service.DiceGameImpl;

public class Main {

    public static void main(String[] args) {
        DiceGameImpl game = new DiceGameImpl();
        game.play(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    }
}
