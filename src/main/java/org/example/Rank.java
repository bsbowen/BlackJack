package org.example;

/**
 * Enum representing the ranks in a deck of cards along with their values.
 */
public enum Rank {
    ACE("Ace", 11),
    TWO("Two", 2),
    THREE("Three", 3),
    FOUR("Four", 4),
    FIVE("Five", 5),
    SIX("Six", 6),
    SEVEN("Seven", 7),
    EIGHT("Eight", 8),
    NINE("Nine", 9),
    TEN("Ten", 10),
    JACK("Jack", 10),
    QUEEN("Queen", 10),
    KING("King", 10);

    private String rankName;
    public int rankValue;//bb:changed to public for access in card class

    Rank(String rankName, int rankValue) {
        this.rankName = rankName;
        this.rankValue = rankValue;
    }

    @Override
    public String toString() {
        return rankName;
    }

    public int getRankValue() {
        return rankValue;
    }
}