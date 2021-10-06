package com.greatlearning.interview.dicegame;

import com.greatlearning.interview.dicegame.service.DiceGame;

public class Main {

    public static void main(String[] args) {
        DiceGame game = new DiceGame(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        game.play();
    }
}
