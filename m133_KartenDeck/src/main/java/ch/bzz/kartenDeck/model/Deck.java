package ch.bzz.kartenDeck.model;

/**
 * a card publisher
 */
public class Deck {
    private String deckUUID;
    private String deck;

    /**
     * gets deckUUID
     *
     * @return value of deckUUID
     */
    public String getDeckUUID() {
        return deckUUID;
    }

    /**
     * sets deckUUID
     *
     * @param deckUUID the value to set
     */
    public void setDeckUUID(String deckUUID) {
        this.deckUUID = deckUUID;
    }

    /**
     * gets deck
     *
     * @return value of deck
     */
    public String getDeck() {
        return deck;
    }

    /**
     * sets deck
     *
     * @param deck the value to set
     */
    public void setDeck(String deck) {
        this.deck = deck;
    }
}
