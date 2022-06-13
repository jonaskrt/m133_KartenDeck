package ch.bzz.kartenDeck.model;

import ch.bzz.kartenDeck.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.List;
import java.util.HashMap;
import java.util.UUID;
import javax.ws.rs.FormParam;
import java.math.*;
import java.lang.*;

/**
 * a card in the card collection
 */
public class Card {
    @JsonIgnore
    @FormParam("deck")
    @Size(min = 1, max = 10, message = "Your Decks name must be between 1 and 10 characters")
    private Deck deck;

    @FormParam("cardUUID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}", message = "The Cards UUID must be a valid UUID")
    private String cardUUID;

    @FormParam("card")
    @Size(min = 1, max = 50, message = "Your Cards name must be between 1 and 50 characters")
    private String card;

    @FormParam("monstertype")
    @Size(min = 1, max = 50, message = "The Monstertype must be between 1 and 10 characters")
    private String monstertype;

    @FormParam("ATK")
    @Size(min = 1, max = 10, message = "Your Cards ATK-stats must be between 1 and 10 characters")
    private BigDecimal ATK;

    @FormParam("DEF")
    @Size(min = 1, max = 10, message = "Your Cards DEF-stats must be between 1 and 10 characters")
    private BigDecimal DEF;

    @FormParam("ability")
    @Size(min = 1, max = 50, message = "Your Cards ability must be between 1 and 50 characters")
    private String ability;

    @JsonIgnore
    private HashMap<String,Card> cardHashMap;

    /**
     * constructor with all params
     * @param deck
     * @param cardUUID
     * @param card
     * @param monstertype
     * @param ATK
     * @param DEF
     * @param ability
     * @throws IllegalArgumentException
     */
    public Card(Deck deck, String cardUUID, String card, String monstertype, BigDecimal ATK, BigDecimal DEF, String ability) throws IllegalArgumentException{
        setDeck(deck);
        setCardUUID(cardUUID);
        setCard(card);
        setMonstertype(monstertype);
        setATK(ATK);
        setDEF(DEF);
        setAbility(ability);
    }

    /**
     * constructor w/o deck
     * @param cardUUID
     * @param card
     * @param monstertype
     * @param ATK
     * @param DEF
     * @param ability
     * @throws IllegalArgumentException
     */
    public Card(String cardUUID, String card, String monstertype, BigDecimal ATK, BigDecimal DEF, String ability) throws IllegalArgumentException{
        setCardUUID(cardUUID);
        setCard(card);
        setMonstertype(monstertype);
        setATK(ATK);
        setDEF(DEF);
        setAbility(ability);
    }

    /**
     * constructor w/o deck, cardUUID
     * @param card
     * @param monstertype
     * @param ATK
     * @param DEF
     * @param ability
     * @throws IllegalArgumentException
     */
    public Card(String card, String monstertype, BigDecimal ATK, BigDecimal DEF, String ability) throws IllegalArgumentException{
        setCard(card);
        setMonstertype(monstertype);
        setATK(ATK);
        setDEF(DEF);
        setAbility(ability);
    }

    /**
     * constructor w/o deck, cardUUID, card
     * @param monstertype
     * @param ATK
     * @param DEF
     * @param ability
     * @throws IllegalArgumentException
     */
    public Card(String monstertype, BigDecimal ATK, BigDecimal DEF, String ability) throws IllegalArgumentException{
        setMonstertype(monstertype);
        setATK(ATK);
        setDEF(DEF);
        setAbility(ability);
    }

    /**
     * constructor w/o deck, cardUUID, card, monstertype
     * @param ATK
     * @param DEF
     * @param ability
     * @throws IllegalArgumentException
     */
    public Card(BigDecimal ATK, BigDecimal DEF, String ability) throws IllegalArgumentException{
        setATK(ATK);
        setDEF(DEF);
        setAbility(ability);
    }

    /**
     * constructor w/o deck, cardUUID, card, monstertype, ATK
     * @param DEF
     * @param ability
     * @throws IllegalArgumentException
     */
    public Card(BigDecimal DEF, String ability) throws IllegalArgumentException{
        setDEF(DEF);
        setAbility(ability);
    }

    /**
     * constructor w/o deck, cardUUID, card, monstertype, ATK, DEF
     * @param ability
     * @throws IllegalArgumentException
     */
    public Card(String ability) throws IllegalArgumentException{
        setAbility(ability);
    }

    /**
     * empty constructor, needed for JSON i suppose
     */
    public Card(){
        setCardUUID(UUID.randomUUID().toString());
    }


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
     * sets random cardUUID with help of the UUID creator
     *
     * @param cardUUID the value to set
     */
    public void setCardUUID(String cardUUID) throws IllegalArgumentException {
        this.cardUUID = UUID.randomUUID().toString();
        UUID.fromString(cardUUID);
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

    /**
     * gets HashMap
     * @return
     */
    public HashMap<String, Card> getCardHashMap() {
        return cardHashMap;
    }

    /**
     * method to add cards to HashMap
     * @param card
     */
    public void addCard(Card card){
        if (cardHashMap == null){
            cardHashMap = new HashMap<>();
        }

        cardHashMap.put(card.getCardUUID(), card);
    }
}