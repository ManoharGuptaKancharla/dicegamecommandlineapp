package com.greatlearning.interview.dicegame.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DiceGameLeaderboard {

    private PriorityQueue<DiceGamePlayer> leaderBoard = new PriorityQueue<>(
            new Comparator<DiceGamePlayer>() {
                @Override
                public int compare(DiceGamePlayer o1, DiceGamePlayer o2) {
                    return Integer.compare(o1.score, o2.score);
                }
            }
    );

    private ArrayList<DiceGamePlayer> ranks = new ArrayList<>();

    public PriorityQueue<DiceGamePlayer> getLeaderBoard() {
        return leaderBoard;
    }

    public ArrayList<DiceGamePlayer> getRanks() {
        return ranks;
    }

}
