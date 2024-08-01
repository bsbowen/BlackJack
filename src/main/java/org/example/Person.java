package org.example;

/**
 * Abstract class used for shared logic between the dealer and player.
 */
public abstract class Person {

    private Hand hand; // The hand of cards the person holds
    private String name; // The name of the person

    /**
     * Constructor to create a new Person with an empty hand and no name.
     */
    public Person() {
        this.hand = new Hand();
        this.name = "";
    }

    // Getters and setters for hand and name
    public Hand getHand() {
        return this.hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Prints a formatted version of the Person's hand.
     */
    public void printHand() {
        System.out.println(this.name + "'s hand looks like this:");
        System.out.println(this.hand + " Valued at: " + this.hand.calculatedValue());
    }

    /**
     * Player takes a card from the deck.
     *
     * @param deck The deck to take the card from
     * @param discard The discard pile to reload from if the deck is empty
     */
    public void hit(Deck deck, Deck discard) {
        // If there's no cards left in the deck
        if (!deck.hasCards()) {
            deck.reloadDeckFromDiscard(discard);
        }
        this.hand.takeCardFromDeck(deck);
        System.out.println(this.name + " gets a card");
        this.printHand();
        GameRunner.pause();
    }

    /**
     * Check if the person has Blackjack (hand value equals 21).
     *
     * @return True if the person has Blackjack, false otherwise
     */
    public boolean hasBlackjack() {
        return this.getHand().calculatedValue() == 21;
    }
}