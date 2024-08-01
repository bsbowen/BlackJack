package org.example;

import java.util.Scanner;

/**
 * Contains all Game logic.
 */
public class GameRunner {
    private Deck deck, discarded; // The main deck and a discarded pile
    private Dealer dealer; // The dealer in the game
    private Player player; // The player in the game
    private int wins, losses, pushes; // Counters for wins, losses, and pushes (ties)
    private static final int BET_AMOUNT = 5; // Hard coded bet amount

    // Constructor to initialize the game components and start the game
    public GameRunner() {
        deck = new Deck(true); // Initialize the main deck with cards
        discarded = new Deck(); // Initialize the discarded pile
        dealer = new Dealer(); // Initialize the dealer
        player = new Player(); // Initialize the player
        deck.shuffle(); // Shuffle the deck at the start
        System.out.println("Starting balance: " + player.getBalance());
        startRound(); // Start the first round of the game
    }

    // Method to start a new round of Blackjack
    private void startRound() {
        Scanner sc = new Scanner(System.in);

        // Continue playing while the player has a positive balance
        while (player.getBalance() > 0) {
            // If this isn't the first round, display the current score and reset hands
            if (wins > 0 || losses > 0 || pushes > 0) {
                System.out.println();
                System.out.println("Starting Next Round... Wins: " + wins + " Losses: " + losses + " Pushes: " + pushes);
                dealer.getHand().discardHandToDeck(discarded); // Move dealer's cards to the discard pile
                player.getHand().discardHandToDeck(discarded); // Move player's cards to the discard pile
            }

            // Check if the deck needs to be reloaded from the discard pile
            if (deck.cardsLeft() < 4) {
                deck.reloadDeckFromDiscard(discarded);
            }

            // Deal two cards to the dealer and player
            dealer.getHand().takeCardFromDeck(deck);
            dealer.getHand().takeCardFromDeck(deck);
            player.getHand().takeCardFromDeck(deck);
            player.getHand().takeCardFromDeck(deck);

            // Show the dealer's hand with one card face down
            dealer.printFirstHand();

            // Show the player's hand
            player.printHand();

            // Deduct the bet amount from the player's balance
            player.adjustBalance(-BET_AMOUNT);
            System.out.println("Bet placed: " + BET_AMOUNT);
            System.out.println("Your current balance is: " + player.getBalance());

            // Check if the dealer has Blackjack
            if (dealer.hasBlackjack()) {
                dealer.printHand(); // Show the dealer's full hand
                if (player.hasBlackjack()) {
                    System.out.println("You both have 21 - Push."); // If both have Blackjack, it's a push
                    pushes++;
                    player.adjustBalance(BET_AMOUNT); // Return the bet amount
                } else {
                    System.out.println("Dealer has BlackJack. You lose."); // Dealer wins
                    losses++;
                }
                continue;
            }

            // Check if the player has Blackjack
            if (player.hasBlackjack()) {
                System.out.println("You have Blackjack! You win!");
                wins++;
                player.adjustBalance(BET_AMOUNT * 2); // Player wins, balance increased by twice the bet amount
                continue;
            }

            // Let the player decide their next move (hit or stand)
            player.makeDecision(deck, discarded);

            // Check if the player busts (goes over 21)
            if (player.getHand().calculatedValue() > 21) {
                System.out.println("You have gone over 21.");
                losses++;
                continue;
            }

            // Dealer's turn to draw cards
            dealer.printHand();
            while (dealer.getHand().calculatedValue() < 17) {
                dealer.hit(deck, discarded);
            }

            // Determine the winner of the round and adjust balance
            if (dealer.getHand().calculatedValue() > 21) {
                System.out.println("Dealer busts");
                wins++;
                player.adjustBalance(BET_AMOUNT * 2); // Player wins, balance increased by twice the bet amount
            } else if (dealer.getHand().calculatedValue() > player.getHand().calculatedValue()) {
                System.out.println("You lose.");
                losses++;
            } else if (player.getHand().calculatedValue() > dealer.getHand().calculatedValue()) {
                System.out.println("You win.");
                wins++;
                player.adjustBalance(BET_AMOUNT * 2); // Player wins, balance increased by twice the bet amount
            } else {
                System.out.println("Push.");
                pushes++;
                player.adjustBalance(BET_AMOUNT); // Bet amount is returned in case of a push
            }

            System.out.println("Your current balance is: " + player.getBalance());

            // End the game if balance is zero
            if (player.getBalance() <= 0) {
                System.out.println("Game over! Your final balance is: " + player.getBalance());
                System.out.println("Thanks for playing!");
                return;
            }
        }

        System.out.println("Game over! Your final balance is: " + player.getBalance());
        System.out.println("Thanks for playing!");
    }

    // Method to pause the game for a short period (for dramatic effect)
    public static void pause() {
        try {
            Thread.sleep(2000); // Pause for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Britt's BlackJack Table!");
        // Create a new game instance
        new GameRunner();
    }
}