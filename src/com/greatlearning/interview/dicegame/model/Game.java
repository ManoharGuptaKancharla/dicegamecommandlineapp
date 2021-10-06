package com.greatlearning.interview.dicegame.model;

import java.util.ArrayList;

public abstract class Game {

    protected ArrayList<DiceGamePlayer> players = new ArrayList<>();

    protected abstract void play();
}
