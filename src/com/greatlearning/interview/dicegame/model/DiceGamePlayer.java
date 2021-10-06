package com.greatlearning.interview.dicegame.model;

public class DiceGamePlayer {

    int id;
    int score = 0;
    boolean isPreviousScoreOne = false;


    public boolean isPreviousScoreOne() {
        return isPreviousScoreOne;
    }

    public void setPreviousScoreOne(boolean previousScoreOne) {
        isPreviousScoreOne = previousScoreOne;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public DiceGamePlayer(int id){
        super();
        this.id = id;
    }

    @Override
    public boolean equals(Object player){
        return this.id == ((DiceGamePlayer)player).id;
    }
}
