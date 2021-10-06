package com.greatlearning.interview.dicegame.service;

import com.greatlearning.interview.dicegame.model.DiceGameModel;
import com.greatlearning.interview.dicegame.model.DiceGamePlayer;
import com.greatlearning.interview.dicegame.util.ServiceUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DiceGameImpl implements Game {

    private Scanner scanner = new Scanner(System.in);

    public int getPlayerStartingTheGame(DiceGameModel gameModel){
        return ServiceUtil.generateRandomInteger(gameModel.getNoOfPlayers());
    }

    public boolean isGameOver(DiceGameModel gameModel){
        return gameModel.getPlayersFinished().size() + 1 == gameModel.getNoOfPlayers();
    }

    public boolean hasPlayerReachedWinningPoints(int score, DiceGameModel diceGameModel){
        return score>=diceGameModel.getPointsRequiredForWinning();
    }

    public boolean isSkipped(int playerId, DiceGameModel diceGameModel){
        return diceGameModel.getSkippedPlayersList().contains(playerId);
    }

    public void updatePlayerScore(int score, int playerId, DiceGameModel diceGameModel){
        for(int index=diceGameModel.getPlayersFinished().size(); index< diceGameModel.getNoOfPlayers(); index++){
            if(diceGameModel.getLeaderboard()[index].getId() == playerId){
                diceGameModel.getLeaderboard()[index].setScore(diceGameModel.getLeaderboard()[index].getScore()+score);
                if(score==1){
                    if(diceGameModel.getLeaderboard()[index].isPreviousScoreOne()){
                        diceGameModel.getSkippedPlayersList().add(playerId);
                    }else{
                        diceGameModel.getLeaderboard()[index].setPreviousScoreOne(true);
                    }
                }else{
                    diceGameModel.getLeaderboard()[index].setPreviousScoreOne(false);
                }
                if(hasPlayerReachedWinningPoints(diceGameModel.getLeaderboard()[index].getScore(), diceGameModel)){
                    diceGameModel.getPlayersFinished().add(playerId);
                    System.out.println("You Finished in " + diceGameModel.getPlayersFinished().size() + " place.");
                }
                break;
            }
        }
    }

    public void updateLeaderBoard(DiceGameModel diceGameModel){
        Arrays.sort(diceGameModel.getLeaderboard(), (o1, o2) -> o2.getScore()-o1.getScore());
    }

    public void printLeaderboard(DiceGameModel diceGameModel){
        System.out.println("Rank\tPlayerId");
        for (int index = 0; index< diceGameModel.getPlayersFinished().size(); index++){
            System.out.println(index+1 + "\t" + diceGameModel.getPlayersFinished().get(index));
        }
        for(int index = diceGameModel.getPlayersFinished().size(); index<diceGameModel.getNoOfPlayers(); index++){
            System.out.println(index+1 + "\t" + diceGameModel.getLeaderboard()[index].getId());
        }
    }

    @Override
    public void play(int noOfPlayers, int pointsRequiredForWinning) {
        DiceGameModel diceGame = new DiceGameModel(noOfPlayers, pointsRequiredForWinning);
        int currentPlayerId = getPlayerStartingTheGame(diceGame);
        while(!isGameOver(diceGame)){
            if (!diceGame.getPlayersFinished().contains(currentPlayerId) && !isSkipped(currentPlayerId, diceGame)){
                System.out.println("Player - " + currentPlayerId + ", it's your turn. Press 'r' and hit enter to roll the dice.");
                String input = scanner.nextLine();
                //TODO: check if input is 'r' or not.
                if(!input.trim().equals("r")){
                    System.out.println("Unknown Key was pressed. Exiting the Game.......!!!");
                    System.out.println("**********Game is OVER.****************");
                    return;
                }
                int score = -1;
                do{
                    score = ServiceUtil.generateRandomInteger(6);
                    System.out.println("You Scored " + score + " points.");
                    updatePlayerScore(score, currentPlayerId, diceGame);
                    updateLeaderBoard(diceGame);
                    printLeaderboard(diceGame);
                }while (score==6 && !diceGame.getPlayersFinished().contains(currentPlayerId));
            }
            if(isSkipped(currentPlayerId, diceGame)){
                diceGame.getSkippedPlayersList().remove(currentPlayerId);
                for(int index=diceGame.getPlayersFinished().size(); index<noOfPlayers; index++) {
                    if (diceGame.getLeaderboard()[index].getId() == currentPlayerId) {
                        diceGame.getLeaderboard()[index].setPreviousScoreOne(false);
                    }
                }
                System.out.println("You turn is skipped!!!");
            }
            currentPlayerId = diceGame.getNextTurnPlayerId(currentPlayerId);
        }
        System.out.println("**********Game is OVER.****************");
        System.out.println("^^^^^^^^^ FINAL LEADERBOARD ^^^^^^^^^^^");
        printLeaderboard(diceGame);
    }
}
