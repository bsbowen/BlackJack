package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * The Deck class represents a deck of playing cards.
 * It includes methods to shuffle, deal, and manage the deck.
 */
public class Deck implements DeckActions {
    private ArrayList<Card> deck; // The list of cards in the deck

    /**
     * Create an empty deck of cards.
     */
    public Deck() {
        deck = new ArrayList<>();
    }

    /**
     * Copy constructor to create a deck based on another deck.
     *
     * @param c The deck to copy
     */
    public Deck(Deck c) {
        this.deck = new ArrayList<>(c.getCards());
    }

    /**
     * Create a standard deck of 52 cards.
     *
     * @param makeDeck If true, initializes the deck with 52 cards
     */
    public Deck(boolean makeDeck) {
        deck = new ArrayList<>();
        if (makeDeck) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    deck.add(new Card(suit, rank));
                }
            }
        }
    }

    /**
     * Add a card to the deck.
     *
     * @param card The card to add
     */
    public void addCard(Card card) {
        deck.add(card);
    }

    /**
     * Add multiple cards to the deck.
     *
     * @param cards The list of cards to add
     */
    public void addCards(ArrayList<Card> cards) {
        deck.addAll(cards);
    }

    /**
     * Shuffle the deck of cards randomly.
     */
    @Override
    public void shuffle() {
        Collections.shuffle(deck, new Random());
    }

    /**
     * Deal the next card from the deck.
     *
     * @return The dealt card, or null if the deck is empty
     */
    @Override
    public Card dealNextCard() {
        return deck.isEmpty() ? null : deck.remove(0);
    }

    /**
     * Print a specified number of cards from the deck.
     *
     * @param numToPrint The number of cards to print
     */
    @Override
    public void printDeck(int numToPrint) {
        for (int i = 0; i < numToPrint && i < deck.size(); i++) {
            System.out.println(deck.get(i));
        }
    }

    /**
     * Check if the deck has any cards left.
     *
     * @return True if the deck has cards, false otherwise
     */
    public boolean hasCards() {
        return !deck.isEmpty();
    }

    /**
     * Get the number of cards left in the deck.
     *
     * @return The number of cards left
     */
    public int cardsLeft() {
        return deck.size();
    }

    /**
     * Get the list of cards in the deck.
     *
     * @return The list of cards
     */
    public ArrayList<Card> getCards() {
        return deck;
    }

    /**
     * Empty the deck of all cards.
     */
    public void emptyDeck() {
        deck.clear();
    }

    /**
     * Reload the deck from a discard pile and shuffle the deck.
     *
     * @param discard The discard pile to reload from
     */
    public void reloadDeckFromDiscard(Deck discard) {
        this.addCards(discard.getCards());
        this.shuffle();
        discard.emptyDeck();
        System.out.println("Ran out of cards, creating new deck from discard pile & shuffling deck.");
    }
}