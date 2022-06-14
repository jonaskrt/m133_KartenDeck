package ch.bzz.kartenDeck.data;

import ch.bzz.kartenDeck.model.Card;
import ch.bzz.kartenDeck.model.Deck;
import ch.bzz.kartenDeck.service.Config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = new DataHandler();
    private static HashMap<String, Card> cardList;
    private static HashMap<String, Deck> deckList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        deckList = new HashMap<>();
        readDeckJSON("KartenDeckJSON");
        cardList = new HashMap<>();
        readCardJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all cards
     * @return list of cards
     */
    public  HashMap<String, Card> readAllCards() {
        return getCardList();
    }

    /**
     * reads all Decks
     * @return list of decks
     */
    public HashMap<String, Deck> readAllDecks() {

        return getDeckList();
    }

    /**
     * reads a publisher by its uuid
     * @param deckUUID
     * @return the Deck (null=not found)
     */
    public Deck readDeckByUUID(String deckUUID) {
        Deck deck = null;
        for (Deck entry : getDeckList().values()) {
            if (entry.getDeckUUID().equals(deckUUID)) {
                deck = entry;
            }
        }
        return deck;
    }


    /**
     * Diese Methode setzt die gespeicherten Daten zurück
     * Sie wird für das Testing gebraucht
     */
    public static void restoreData(){
        cardList = new HashMap<>();
        deckList = new HashMap<>();
        readDeckJSON("KartenDeckJSON");
    }




    /**
     * gets cardList
     *
     * @return value of cardList
     */
    public static HashMap<String, Card> getCardList() {
        return cardList;
    }

    /**
     * sets cardList
     *
     * @param cardList the value to set
     */
    private void setCardList(HashMap<String, Card> cardList) {
        this.cardList = cardList;
    }

    /**
     * gets publisherList
     *
     * @return value of publisherList
     */
    public static HashMap<String, Deck> getDeckList() {
        return deckList;
    }

    /**
     * sets deckList
     *
     * @param deckList the value to set
     */
    private void setDeckList(HashMap<String, Deck> deckList) {
        this.deckList = deckList;
    }


    /**
     * reads a card by its uuid
     * @param cardUUID
     * @return the card (null=not found)
     */
    public Card readCardByUUID(String cardUUID) {
        Card karte = null;
        for (Card entry : getCardList().values()) {
            if (entry.getCardUUID().equals(cardUUID)) {
                karte = entry;
            }
        }
        return karte;
    }

    /**
     * reads the publishers from the JSON-file
     */
    private static void readDeckJSON(String filename) {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(Config.getProperty(filename)));
            ObjectMapper objectMapper = new ObjectMapper();
            Deck[] decks = objectMapper.readValue(jsonData, Deck[].class);
            for (Deck deck : decks) {
                getDeckList().put(getDeckList().toString(),deck); // temporary, no idea how this should work
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the cards from the JSON-file
     */
    private void readCardJSON() {
        try {
            String path = Config.getProperty("cardJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Card[] card = objectMapper.readValue(jsonData, Card[].class);
            for (Card cards : card) {
                getCardList().put(getCardList().toString(), cards);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}