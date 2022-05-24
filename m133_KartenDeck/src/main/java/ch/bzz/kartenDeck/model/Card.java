package ch.bzz.kartenDeck.model;

import ch.bzz.kartenDeck.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.*;
import java.lang.*;

/**
 * a card in the card collection
 */
public class Card {
    @JsonIgnore
    private Deck deck;

    private String cardUUID;
    private String card;
    private String monstertype;
    private BigDecimal ATK;
    private BigDecimal DEF;
    private String ability;

    /**
     * gets the deckUUID from the Deck-object
     * @return
     */
    public String getDeckUUID() {
        return getDeck().getDeckUUID();
    }

    /**
     * creates a Deck-object without the cardList
     * @param deckUUID
     */
    public void setDeckUUID(String deckUUID) {
        setDeck( new Deck());
        Deck deck = DataHandler.getInstance().readDeckByUUID(deckUUID);
        getDeck().setDeckUUID(deckUUID);
        getDeck().setDeck(deck.getDeck());

    }

    /**
     * gets deck
     *
     * @return value of deck
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * sets deck
     *
     * @param deck the value to set
     */
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    /**
     * gets cardUUID
     *
     * @return value of cardUUID
     */
    public String getCardUUID() {
        return cardUUID;
    }

    /**
     * sets cardUUID
     *
     * @param cardUUID the value to set
     */
    public void setCardUUID(String cardUUID) {
        this.cardUUID = cardUUID;
    }

    /**
     * gets card
     *
     * @return value of card
     */
    public String getCard() {
        return card;
    }

    /**
     * sets card
     *
     * @param card the value to set
     */
    public void setCard(String card) {
        this.card = card;
    }

    /**
     * gets monstertype
     *
     * @return value of monstertype
     */
    public String getMonstertype() {
        return monstertype;
    }

    /**
     * sets monstertype
     *
     * @param monstertype the value to set
     */
    public void setMonstertype(String monstertype) {
        this.monstertype = monstertype;
    }


    /**
     * gets ATK
     *
     * @return value of ATK
     */
    public BigDecimal getATK() {
        return ATK;
    }

    /**
     * sets ATK
     *
     * @param ATK the value to set
     */
    public void setATK(BigDecimal ATK) {
        this.ATK = ATK;
    }

    /**
     * gets DEF
     *
     * @return value of DEF
     */
    public BigDecimal getDEF() {
        return DEF;
    }

    /**
     * sets DEF
     *
     * @param DEF the value to set
     */
    public void setDEF(BigDecimal DEF) {
        this.DEF = DEF;
    }


    /**
     * gets ability
     *
     * @return value of ability
     */
    public String getAbility() {
        return ability;
    }

    /**
     * sets ability
     *
     * @param ability the value to set
     */
    public void setAbility(String ability) {
        this.ability = ability;
    }
}