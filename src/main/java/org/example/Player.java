package org.example;

import java.util.Scanner;

/**
 * Handles all Player specific operations.
 */
public class Player extends Person {

    private Scanner input = new Scanner(System.in); // Scanner to get user input
    private int balance; // Player's balance

    /**
     * Create a new Player.
     */
    public Player() {
        super.setName("Player");
        this.balance = 20; // Starting balance
    }

    public int getBalance() {
        return balance;
    }

    public void adjustBalance(int amount) {
        this.balance += amount;
    }

    /**
     * Allow the player to make decisions during their turn.
     *
     * @param deck The deck to draw cards from
     * @param discard The discard pile to reload from if the deck is empty
     */
    public void makeDecision(Deck deck, Deck discard) {
        int decision = 0;
        boolean getNum = true;

        while (getNum) {
            try {
                System.out.println("Would you like to: 1) Hit or 2) Stand");
                decision = input.nextInt();
                getNum = false;
            } catch (Exception e) {
                System.out.println("Invalid");
                input.next(); // Clear the invalid input
            }
            // We don't close the scanner, because we will need it later.
        }

        if (decision == 1) {
            this.hit(deck, discard);
            // Return if they have blackjack or busted
            if (this.getHand().calculatedValue() > 20) {
                return;
            } else {
                this.makeDecision(deck, discard);
            }
        } else {
            System.out.println("You stand.");
        }
    }
}