package org.example;

/**
 * The Card class represents a single playing card with a suit and rank.
 * It implements Comparable to allow comparison of card values.
 */
public class Card implements Comparable<Card> {
    private Suit suit; // The suit of the card (e.g., Hearts, Diamonds)
    public Rank rank; // The rank of the card (e.g., Two, King)  //bb: changed rank to public

    /**
     * Constructor to create a card with a specified suit and rank.
     *
     * @param suit The suit of the card
     * @param rank The rank of the card
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Copy constructor to create a card based on another card.
     *
     * @param card The card to copy
     */
    public Card(Card card) {
        this.suit = card.getSuit();
        this.rank = card.getRank();
    }

    /**
     * Get the value of the card based on its rank.
     *
     * @return The numerical value of the card
     */
    public int getValue() {
        return rank.rankValue;
    }

    /**
     * Get the suit of the card.
     *
     * @return The suit of the card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Get the rank of the card.
     *
     * @return The rank of the card
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Return a string representation of the card.
     *
     * @return A string representing the card
     */
    @Override
    public String toString() {
        return ("[" + rank + " of " + suit + "] (" + this.getValue() + ")");
    }

    /**
     * Compare this card to another card based on their values.
     *
     * @param c The card to compare to
     * @return 1 if this card is greater, -1 if less, 0 if equal
     */
    @Override
    public int compareTo(Card c) {
        if (this.getValue() > c.getValue()) {
            return 1;
        } else if (this.getValue() < c.getValue()) {
            return -1;
        } else {
            return 0;
        }
    }
}