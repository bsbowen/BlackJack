package org.example;

import java.util.ArrayList;

/**
 * A hand of cards to play with.
 */
public class Hand {

    private ArrayList<Card> hand; // The list of cards in the hand

    /**
     * Constructor to create an empty hand of cards.
     */
    public Hand() {
        hand = new ArrayList<>();
    }

    /**
     * Take a single card from the top of the deck and add it to the hand, removing it from the previous deck.
     *
     * @param deck The deck of cards we're taking from
     */
    public void takeCardFromDeck(Deck deck) {
        hand.add(deck.dealNextCard());
    }

    /**
     * Add a single card to this hand.
     *
     * @param c The card being added
     */
    public void addCard(Card c) {
        hand.add(c);
    }

    /**
     * Discard the hand to the discard deck.
     *
     * @param discardDeck The deck we're discarding this hand to
     */
    public void discardHandToDeck(Deck discardDeck) {
        // Copy cards from hand to discardDeck
        discardDeck.addCards(hand);
        // Clear the hand
        hand.clear();
    }

    /**
     * @return The hand with all its cards in a single line String
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Card card : hand) {
            output.append(card).append(" - ");
        }
        return output.toString();
    }

    /**
     * Calculate the numerical value of the hand.
     *
     * @return The calculated value of the hand
     */
    public int calculatedValue() {
        int value = 0;
        int aceCount = 0;

        // For each card in this hand
        for (Card card : hand) {
            // Add the card value to the hand
            value += card.getValue();
            // Count how many aces have been added
            if (card.getValue() == 11) {
                aceCount++;
            }
        }

        // Adjust for aces if the value is over 21
        if (value > 21 && aceCount > 0) {
            while (aceCount > 0 && value > 21) {
                aceCount--;
                value -= 10;
            }
        }
        return value;
    }

    /**
     * Get a card from the hand by index.
     *
     * @param idx The index of the card we're getting
     * @return The card at the specified index
     */
    public Card getCard(int idx) {
        return hand.get(idx);
    }
}