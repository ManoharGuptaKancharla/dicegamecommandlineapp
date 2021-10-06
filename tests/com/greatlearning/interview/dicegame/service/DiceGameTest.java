package com.greatlearning.interview.dicegame.service;

import com.greatlearning.interview.dicegame.model.DiceGameModel;
import com.greatlearning.interview.dicegame.model.DiceGamePlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DiceGameTest {

    DiceGameImpl diceGame = new DiceGameImpl();

    @Test
    public void isPlayerStartingTheGameValid() throws Exception{
        DiceGameModel diceGameModel = new DiceGameModel(3, 10);
        Assertions.assertTrue(diceGame.getPlayerStartingTheGame(diceGameModel) <= 3);
    }

    @Test
    public void shouldBeFalseForIsGameOver() throws Exception{
        DiceGameModel diceGameModel = new DiceGameModel(3, 10);
        Assertions.assertFalse(diceGame.isGameOver(diceGameModel));
    }

    @Test
    public void shouldBeTrueForPlayerCompletingGame() throws Exception{
        DiceGameModel diceGameModel = new DiceGameModel(3, 10);
        Assertions.assertTrue(diceGame.hasPlayerReachedWinningPoints(12, diceGameModel));
    }

    @Test
    public void shouldBeFalseForPlayerCompletingGame() throws Exception{
        DiceGameModel diceGameModel = new DiceGameModel(3, 10);
        Assertions.assertFalse(diceGame.hasPlayerReachedWinningPoints(2, diceGameModel));
    }

    @Test
    public void testIfPlayerIsSkipped() throws Exception{
        DiceGameModel diceGameModel = new DiceGameModel(3, 10);
        Assertions.assertFalse(diceGame.isSkipped(1, diceGameModel));
    }

    @Test
    public void testUpdatePlayerScore() throws Exception{
        DiceGameModel diceGameModel = new DiceGameModel(3, 10);
        diceGameModel.getPlayersFinished().add(1);
        DiceGamePlayer localDicegamePlayer = new DiceGamePlayer(2);
        localDicegamePlayer.setPreviousScoreOne(true);
        localDicegamePlayer.setScore(8);
        diceGameModel.getLeaderboard()[0] = new DiceGamePlayer(1);
        diceGameModel.getLeaderboard()[1] = localDicegamePlayer;
        diceGameModel.getLeaderboard()[2] = new DiceGamePlayer(3);

        diceGame.updatePlayerScore(3, 2, diceGameModel);
        Assertions.assertTrue(diceGameModel.getLeaderboard()[1].getScore() == 11);
    }

    @Test
    public void testUpdatePlayerScoreForRollingOneForFirstTime() throws Exception{
        DiceGameModel diceGameModel = new DiceGameModel(3, 10);
        diceGameModel.getPlayersFinished().add(1);
        DiceGamePlayer localDicegamePlayer = new DiceGamePlayer(2);
        localDicegamePlayer.setPreviousScoreOne(false);
        localDicegamePlayer.setScore(2);
        diceGameModel.getLeaderboard()[0] = new DiceGamePlayer(1);
        diceGameModel.getLeaderboard()[1] = localDicegamePlayer;
        diceGameModel.getLeaderboard()[2] = new DiceGamePlayer(3);

        diceGame.updatePlayerScore(1, 2, diceGameModel);
        Assertions.assertTrue(diceGameModel.getLeaderboard()[1].getScore() == 3);
        Assertions.assertTrue(diceGameModel.getLeaderboard()[1].isPreviousScoreOne());
    }

    @Test
    public void testUpdatePlayerScoreForRollingOneForSecondConsecutiveTime() throws Exception{
        DiceGameModel diceGameModel = new DiceGameModel(3, 10);
        diceGameModel.getPlayersFinished().add(1);
        DiceGamePlayer localDicegamePlayer = new DiceGamePlayer(2);
        localDicegamePlayer.setPreviousScoreOne(true);
        localDicegamePlayer.setScore(2);
        diceGameModel.getLeaderboard()[0] = new DiceGamePlayer(1);
        diceGameModel.getLeaderboard()[1] = localDicegamePlayer;
        diceGameModel.getLeaderboard()[2] = new DiceGamePlayer(3);

        diceGame.updatePlayerScore(1, 2, diceGameModel);
        Assertions.assertTrue(diceGameModel.getLeaderboard()[1].getScore() == 3);
        Assertions.assertTrue(diceGameModel.getLeaderboard()[1].isPreviousScoreOne());
        Assertions.assertTrue(diceGameModel.getSkippedPlayersList().contains(2));
    }

    @Test
    public void testUpdateLeaderboard() throws Exception{
        DiceGameModel diceGameModel = new DiceGameModel(3, 10);
        DiceGamePlayer localDicegamePlayer = new DiceGamePlayer(1);
        localDicegamePlayer.setScore(1);
        diceGameModel.getLeaderboard()[0] = localDicegamePlayer;

        localDicegamePlayer = new DiceGamePlayer(2);
        localDicegamePlayer.setScore(2);
        diceGameModel.getLeaderboard()[1] = localDicegamePlayer;

        localDicegamePlayer = new DiceGamePlayer(3);
        localDicegamePlayer.setScore(3);
        diceGameModel.getLeaderboard()[2] = localDicegamePlayer;

        diceGame.updateLeaderBoard(diceGameModel);
        Assertions.assertTrue(diceGameModel.getLeaderboard()[0].getId() == 3);
        Assertions.assertTrue(diceGameModel.getLeaderboard()[1].getId() == 2);
        Assertions.assertTrue(diceGameModel.getLeaderboard()[2].getId() == 1);
    }

    @Test
    public void testPrintLeaderBoard() throws Exception{
        PrintStream standardOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        DiceGameModel diceGameModel = new DiceGameModel(3, 10);
        diceGameModel.getPlayersFinished().add(1);

        DiceGamePlayer localDicegamePlayer = new DiceGamePlayer(1);
        localDicegamePlayer.setScore(1);
        diceGameModel.getLeaderboard()[0] = localDicegamePlayer;

        localDicegamePlayer = new DiceGamePlayer(2);
        localDicegamePlayer.setScore(2);
        diceGameModel.getLeaderboard()[1] = localDicegamePlayer;

        localDicegamePlayer = new DiceGamePlayer(3);
        localDicegamePlayer.setScore(3);
        diceGameModel.getLeaderboard()[2] = localDicegamePlayer;

        diceGame.printLeaderboard(diceGameModel);

        Assertions.assertTrue(outputStreamCaptor.toString().trim().contains("Rank"));
        Assertions.assertTrue(outputStreamCaptor.toString().trim().contains("PlayerId"));
        System.setOut(standardOut);
    }
}
