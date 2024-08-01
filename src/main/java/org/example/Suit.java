package org.example;

/**
 * Enum representing the suits in a deck of cards.
 */
public enum Suit {
    CLUB("Clubs"),
    DIAMOND("Diamonds"),
    HEART("Hearts"),
    SPADE("Spades");

    private String suitName;

    Suit(String suitName) {
        this.suitName = suitName;
    }

    @Override
    public String toString() {
        return suitName;
    }
}