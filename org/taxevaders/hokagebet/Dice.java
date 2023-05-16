package org.taxevaders.hokagebet;

import java.util.Random;

public class Dice extends CasinoGame {
    public Dice(User user) {
        super(user);
    }

    @Override
    public void playGame() {
        int playerDiceTotal = rollTwoDice();
        int dealerDiceTotal = rollTwoDice();

        System.out.println("Player's dice total: " + playerDiceTotal);
        System.out.println("Dealer's dice total: " + dealerDiceTotal);

        if (playerDiceTotal > dealerDiceTotal) {
            System.out.println("You won!");
        } else if (playerDiceTotal < dealerDiceTotal) {
            System.out.println("You lost.");
        } else {
            System.out.println("It's a draw!");
        }
    }

    private int rollTwoDice() {
        Random random = new Random();
        int die1 = random.nextInt(6) + 1;
        int die2 = random.nextInt(6) + 1;
        return die1 + die2;
    }
}

