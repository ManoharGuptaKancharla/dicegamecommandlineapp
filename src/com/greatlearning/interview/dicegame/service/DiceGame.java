package com.greatlearning.interview.dicegame.service;

import com.greatlearning.interview.dicegame.model.DiceGamePlayer;
import com.greatlearning.interview.dicegame.model.Game;
import com.greatlearning.interview.dicegame.util.ServiceUtil;

import java.util.*;

public class DiceGame extends Game {

    private int noOfPlayers;
    private int pointsRequiredForWinning = 0;
    private DiceGamePlayer[] leaderboard;
    private int currentPlayerId = 0;
    private ArrayList<Integer> playersFinished = new ArrayList<>();
    private ArrayList<Integer> skippedPlayersList = new ArrayList<>();

    private Scanner scanner = new Scanner(System.in);

    public DiceGame (int noOfPlayers, int pointsRequiredForWinning){
        super();
        this.noOfPlayers = noOfPlayers;
        this.pointsRequiredForWinning = pointsRequiredForWinning;
        this.leaderboard = new DiceGamePlayer[noOfPlayers];
        for(int id=1; id<=noOfPlayers; id++){
            DiceGamePlayer diceGamePlayer = new DiceGamePlayer(id);
            this.players.add(diceGamePlayer);
            leaderboard[id-1] = diceGamePlayer;
        }
    }

    private int getPlayerStartingTheGame(int noOfPlayers){
        return ServiceUtil.generateRandomInteger(noOfPlayers);
    }

    private boolean isGameOver(){
        return playersFinished.size() + 1 == noOfPlayers;
    }

    private boolean hasPlayerReachedWinningPoints(int score){
        return score>=pointsRequiredForWinning;
    }

    private boolean isSkipped(int playerId){
        return skippedPlayersList.contains(playerId);
    }

    private void updatePlayerScore(int score, int playerId){
        for(int index=playersFinished.size(); index<noOfPlayers; index++){
            if(leaderboard[index].getId() == playerId){
                leaderboard[index].setScore(leaderboard[index].getScore()+score);
                if(score==1){
                    if(leaderboard[index].isPreviousScoreOne()){
                        skippedPlayersList.add(playerId);
                    }else{
                        leaderboard[index].setPreviousScoreOne(true);
                    }
                }else{
                    leaderboard[index].setPreviousScoreOne(true);
                }
                if(hasPlayerReachedWinningPoints(leaderboard[index].getScore())){
                    playersFinished.add(playerId);
                    System.out.println("You Finished in " + playersFinished.size() + " place.");
                }
                break;
            }
        }
    }

    private void updateLeaderBoard(){
        Arrays.sort(leaderboard, (o1, o2) -> o2.getScore()-o1.getScore());
    }

    private void printLeaderboard(){
        System.out.println("Rank\tPlayerId");
        for (int index = 0; index< playersFinished.size(); index++){
            System.out.println(index+1 + "\t" + playersFinished.get(index));
        }
        for(int index = playersFinished.size(); index<noOfPlayers; index++){
            System.out.println(index+1 + "\t" + leaderboard[index].getId());
        }
    }

    private int getNextTurnPlayerId(int currentPlayerId){
        return currentPlayerId % noOfPlayers + 1;
    }

    @Override
    public void play() {
        currentPlayerId = getPlayerStartingTheGame(noOfPlayers);
        while(!isGameOver()){
            if (!playersFinished.contains(currentPlayerId) && !isSkipped(currentPlayerId)){
                System.out.println("Player - " + currentPlayerId + ", it's your turn. Press 'r' and hit enter to roll the dice.");
                String input = scanner.nextLine();
                //TODO: check if input is 'r' or not.
                int score = -1;
                do{
                    score = ServiceUtil.generateRandomInteger(6);
                    System.out.println("You Scored " + score + " points.");
                    updatePlayerScore(score, currentPlayerId);
                    updateLeaderBoard();
                    printLeaderboard();
                }while (score==6 && !playersFinished.contains(currentPlayerId));
            }
            if(isSkipped(currentPlayerId)){
                skippedPlayersList.remove(currentPlayerId);
                for(int index=playersFinished.size(); index<noOfPlayers; index++) {
                    if (leaderboard[index].getId() == currentPlayerId) {
                        leaderboard[index].setPreviousScoreOne(false);
                    }
                }
                System.out.println("You turn is skipped!!!");
            }
            currentPlayerId = getNextTurnPlayerId(currentPlayerId);
        }
        System.out.println("**********Game is OVER.****************");
        System.out.println("^^^^^^^^^ FINAL LEADERBOARD ^^^^^^^^^^^");
        printLeaderboard();
    }
}
