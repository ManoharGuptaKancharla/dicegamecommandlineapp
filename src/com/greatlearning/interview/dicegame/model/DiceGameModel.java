package com.greatlearning.interview.dicegame.model;

import java.util.ArrayList;

public class DiceGameModel {

    private int noOfPlayers;
    private int pointsRequiredForWinning = 0;
    private DiceGamePlayer[] leaderboard;
    private int currentPlayerId = 0;
    private ArrayList<Integer> playersFinished = new ArrayList<>();
    private ArrayList<Integer> skippedPlayersList = new ArrayList<>();

    public DiceGameModel(int noOfPlayers, int pointsRequiredForWinning){
        this.noOfPlayers = noOfPlayers;
        this.pointsRequiredForWinning = pointsRequiredForWinning;
        this.leaderboard = new DiceGamePlayer[noOfPlayers];
        for(int id=1; id<=noOfPlayers; id++){
            DiceGamePlayer diceGamePlayer = new DiceGamePlayer(id);
            leaderboard[id-1] = diceGamePlayer;
        }
    }

    public int getNextTurnPlayerId(int currentPlayerId){
        return currentPlayerId % noOfPlayers + 1;
    }

    public int getNoOfPlayers() {
        return noOfPlayers;
    }

    public void setNoOfPlayers(int noOfPlayers) {
        this.noOfPlayers = noOfPlayers;
    }

    public int getPointsRequiredForWinning() {
        return pointsRequiredForWinning;
    }

    public void setPointsRequiredForWinning(int pointsRequiredForWinning) {
        this.pointsRequiredForWinning = pointsRequiredForWinning;
    }

    public DiceGamePlayer[] getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(DiceGamePlayer[] leaderboard) {
        this.leaderboard = leaderboard;
    }

    public int getCurrentPlayerId() {
        return currentPlayerId;
    }

    public void setCurrentPlayerId(int currentPlayerId) {
        this.currentPlayerId = currentPlayerId;
    }

    public ArrayList<Integer> getPlayersFinished() {
        return playersFinished;
    }

    public void setPlayersFinished(ArrayList<Integer> playersFinished) {
        this.playersFinished = playersFinished;
    }

    public ArrayList<Integer> getSkippedPlayersList() {
        return skippedPlayersList;
    }

    public void setSkippedPlayersList(ArrayList<Integer> skippedPlayersList) {
        this.skippedPlayersList = skippedPlayersList;
    }
}
