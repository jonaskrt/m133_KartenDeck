package ch.bzz.kartenDeck.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.HashMap;
import java.util.UUID;



/**
 * a card publisher
 */
public class Deck {
    @FormParam("deckUUID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}", message = "The Cards UUID must be a valid UUID")
    private String deckUUID;

    @FormParam("deck")
    @Size(min = 1, max = 10, message = "Your Decks name must be between 1 and 10 characters")
    private String deck;

    @JsonIgnore
    private HashMap<String, Deck> deckHashMap;

    /**
     * constructor
     * @param deckUUID
     * @param deck
     * @throws IllegalArgumentException
     */
    public Deck(String deckUUID, String deck) throws IllegalArgumentException{
        setDeckUUID(deckUUID);
        setDeck(deck);

        deckHashMap = null;
    }

    /**
     * empty constructor
     * sets randomized deckUUID
     */
    public Deck() {
        setDeckUUID(UUID.randomUUID().toString());

        deckHashMap = null;
    }

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
     * @throws IllegalArgumentException
     */
    public void setDeckUUID(String deckUUID) throws IllegalArgumentException{
        UUID.fromString(deckUUID);

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

    public HashMap<String, Deck> getDeckHashMap(){
        return deckHashMap;
    }

    public void addDeckHashMap(Deck deck){
        if(deckHashMap == null){
            deckHashMap = new HashMap<>();
        }
        deckHashMap.put(deck.getDeckUUID(), deck);
    }
}
