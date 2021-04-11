package com.radoslawzerek;

/**
 * Author: Radosław Żerek
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Game {

    private String name;
    private int totalRounds;
    private int currentRound;
    private int roundsWon;
    private int roundsLost;
    private int winStreak;
    private BaseMoves player;
    private List<BaseMoves> computerMoves;

    public enum GameType {
        DEFAULT,
        ENHANCED
    }

    private enum RoundState {
        LOST,
        DRAW,
        WON

    }

    private GameType gameType;
    private RoundState roundState;
    private final ChoiceManager choice = new ChoiceManager();
    private boolean end = false;

    public void start() {
        introduce();
        System.out.println("Hello " + name + ", welcome to my RPS-game");
        computerMoves = setPossibleMoves();
        while (!end) {
            printStartMenu();
            char key = choice.getChoiceChar();
            switch (key) {
                case '1':
                    setTotalRounds();
                    restart();
                    startGame(GameType.DEFAULT);
                    break;
                case '2':
                    setTotalRounds();
                    restart();
                    startGame(GameType.ENHANCED);
                    break;
                case '3':
                    if (gameType != null) {
                        restart();
                        startGame(gameType);
                    }
                    break;
                case '4':
                    if (gameType != null && endGame) startGame(gameType);
                case 'r':
                    printRules();
                    break;
                case 'x':
                    end = true;
                    break;
            }
        }
    }

    private boolean endGame;
    private boolean optionChosen;

    public void startGame(GameType gameType) {
        endGame = false;
        this.gameType = gameType;
        while (!end && currentRound <= totalRounds && !endGame) {
            System.out.println();
            printGameBar();
            printGameMenu();
            optionChosen = false;
            char key = choice.getChoiceChar();
            switch (key) {
                case '1':
                    playerMove(new Rock());
                    break;
                case '2':
                    playerMove(new Paper());
                    break;
                case '3':
                    playerMove(new Scissors());
                    break;
                case '4':
                    if (gameType == GameType.ENHANCED) playerMove(new Spock());
                    break;
                case '5':
                    if (gameType == GameType.ENHANCED) playerMove(new Lizard());
                    break;
                case 'n':
                    System.out.println("Are you sure you want to restart the current game?   1. Yes   Any. No");
                    key = choice.getChoiceChar();
                    if (key == '1') restart();
                    optionChosen = true;
                    break;
                case 'm':
                    System.out.println("Are you sure you want to exit to main menu?   1. Yes   Any. No");
                    key = choice.getChoiceChar();
                    if (key == '1') endGame = true;
                    optionChosen = true;
                    break;
                case 'x':
                    System.out.println("Are you sure you want to exit the current game?   1. Yes   Any. No");
                    key = choice.getChoiceChar();
                    if (key == '1') end = true;
                    optionChosen = true;
                    break;
            }

            if (!optionChosen && player != null) {
                botMoveFor(player);
                determineWinner(roundState);
                currentRound++;
            }
        }

        if (currentRound >= totalRounds) {
            printEndGame();
        }
    }

    public void introduce() {
        System.out.println("Please introduce yourself :)");
        name = choice.getChoiceStringWithSpaces();
    }

    private void setTotalRounds() {
        System.out.println("Please enter number of rounds you'll have to score in other to win");
        totalRounds = choice.getChoiceInt();
    }

    private void restart() {
        currentRound = 1;
        roundsWon = 0;
        roundsLost = 0;
        winStreak = 0;
    }

    private void printStartMenu() {
        System.out.println("1. Play Default - Easy-Mode for beginners.");
        System.out.println("2. Play Enhanced - Hard-Mode, if You think You can win ;)");
        if (gameType != null) System.out.println("3. Play again! - the same mode and the same number of rounds");
        if (endGame && gameType != null) System.out.println("4. Resume previous game!");
        System.out.println("R. Rules");
        System.out.println("X. Exit");
    }

    private void printGameBar() {
        System.out.println(name + ' ' + roundsWon + ':' + roundsLost + " Bot   |   Round: " + currentRound + '/' + totalRounds);
    }

    private void printGameMenu() {
        System.out.print("1. Rock   2. Paper   3. Scissors   ");
        if (gameType == GameType.ENHANCED) System.out.print("4. Spock   5. Lizard   ");
        System.out.println("N. Restart Game   M. Exit to menu   X. Exit");
    }

    private void printRules() {
        System.out.println("Are you serious? Everyone knows the rules");
    }

    private void move(String username, BaseMoves baseMoves) {
        System.out.println(username + " -> " + baseMoves.getName());
    }

    private void playerMove(BaseMoves baseMove) {
        player = baseMove;
        move(name, baseMove);
    }

    private List<BaseMoves> setPossibleMoves() {
        List<BaseMoves> possibleMoves = new ArrayList<>();
        BaseMoves[] moves = new BaseMoves[]{new Rock(), new Paper(), new Scissors()};
        IntStream.iterate(0, n -> n++)
                .limit(moves.length)
                .forEach(m -> possibleMoves.add(moves[m]));
        return possibleMoves;
    }

    private void botMoveFor(BaseMoves player) {
        Random r = new Random();
        List<BaseMoves> moves = new ArrayList<>();
        BaseMoves bot;
        //Win for player
        switch (gameType) {
            case DEFAULT: {
                bot = computerMoves.get(r.nextInt(3));
                move("Bot", bot);
                if (player.getWinsWith().contains(bot)) {
                    roundState = RoundState.WON;
                } else if (player.getLosesAgainst().contains(bot)) {
                    roundState = RoundState.LOST;
                } else roundState = RoundState.DRAW;
            }
            case ENHANCED: {
                int percent = r.nextInt(100);
                int index = r.nextInt(2);
                if (percent < 25) {
                    moves.addAll(player.getWinsWith());
                    bot = moves.get(index);
                    move("Bot", bot);
                    roundState = RoundState.WON;
                }

                //Draw
                else if (percent < 50) {
                    bot = player;
                    move("Bot", bot);
                    roundState = RoundState.DRAW;
                }

                //Lose for player
                else {
                    moves.addAll(player.getLosesAgainst());
                    bot = moves.get(index);
                    move("Bot", bot);
                    roundState = RoundState.LOST;
                }
            }
        }
    }

    private void determineWinner(RoundState rs) {
        switch (rs) {
            case LOST: lostRound();
            case DRAW: drawRound();
            case WON: wonRound();
        }
    }

    private void wonRound() {
        winStreak++;
        roundsWon++;
        if (winStreak == 3) {
            roundsWon++;
            winStreak = 0;
            System.out.println(name + " is on fire! 1 more point was awarded");
        }
        System.out.println(name + " wins this round");
    }

    private void drawRound() {
        System.out.println("Draw!");
    }

    private void lostRound() {
        if (winStreak > 0) winStreak--;
        roundsLost++;
        System.out.println("Bot wins this round");
    }

    private void printEndGame() {
        String winnerStatement;
        if (roundsWon > roundsLost) winnerStatement = name + " won!";
        else if (roundsWon < roundsLost) winnerStatement = "Computer won!";
        else winnerStatement = "It was a draw!";
        System.out.println("\n:::Game ended! " + winnerStatement + ":::\n:::" + roundsWon
                + "-" + roundsLost + " total rounds: " + totalRounds + ":::\n");
    }
}