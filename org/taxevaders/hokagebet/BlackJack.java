package org.taxevaders.hokagebet;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack extends CasinoGame {
    private ArrayList<Card> dealerHand;
    private ArrayList<Card> playerHand;

    public BlackJack(User user) {
        super(user);
        dealerHand = new ArrayList<>();
        playerHand = new ArrayList<>();
    }

    @Override
    public void playGame() {
        // Place a bet before the game starts
        Scanner betInput = new Scanner(System.in);
        System.out.println("Enter your bet amount:");
        setBet(betInput.nextDouble());

        Deck deck = new Deck();
        deck.shuffle();

        // Initial deal
        playerHand.add(deck.draw());
        playerHand.add(deck.draw());
        dealerHand.add(deck.draw());
        dealerHand.add(deck.draw());

        Scanner input = new Scanner(System.in);
        boolean playerTurn = true;
        while (playerTurn) {
            System.out.println("Player hand: " + handString(playerHand) + " | Total: " + handTotal(playerHand));
            System.out.println("Dealer hand: " + dealerHand.get(0).toString() + ", [HIDDEN]");
            System.out.println("1. Hit");
            System.out.println("2. Stand");
            int choice = input.nextInt();

            if (choice == 1) {
                playerHand.add(deck.draw());
                if (handTotal(playerHand) > 21) {
                    System.out.println("Player hand: " + handString(playerHand) + " | Total: " + handTotal(playerHand));
                    System.out.println("Busted! You lost.");
                    user.setBalance(user.getBalance() - getBet());
                    return;
                }
            } else {
                playerTurn = false;
            }
        }

        while (handTotal(dealerHand) < 17) {
            dealerHand.add(deck.draw());
        }

        System.out.println("Player hand: " + handString(playerHand) + " | Total: " + handTotal(playerHand));
        System.out.println("Dealer hand: " + handString(dealerHand) + " | Total: " + handTotal(dealerHand));

        if (handTotal(dealerHand) > 21 || handTotal(playerHand) > handTotal(dealerHand)) {
            System.out.println("You won!");
            user.setBalance(user.getBalance() + getBet());
        } else if (handTotal(playerHand) == handTotal(dealerHand)) {
            System.out.println("It's a draw!");
            // Balance remains the same in case of a draw
        } else {
            System.out.println("You lost.");
            user.setBalance(user.getBalance() - getBet());
        }

        System.out.println("Your new balance is: " + user.getBalance());
    }

    private int handTotal(ArrayList<Card> hand) {
        int total = 0;
        int aces = 0;

        for (Card card : hand) {
            int value = card.getRank().getValue();
            if (value == 1) {
                aces++;
            }
            total += value;
        }

        for (int i = 0; i < aces; i++) {
            if (total <= 11) {
                total += 10;
            }
        }

        return total;
    }

    private String handString(ArrayList<Card> hand) {
        StringBuilder sb = new StringBuilder();
        for (Card card : hand) {
            sb.append(card.toString()).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }
}


