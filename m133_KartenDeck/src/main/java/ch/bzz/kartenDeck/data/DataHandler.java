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
    private static DataHandler instance = null;
    private List<Card> cardList;
    private List<Deck> deckList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setDeckList(new ArrayList<>());
        readDeckJSON();
        setCardList(new ArrayList<>());
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
    public List<Card> readAllCards() {
        return getCardList();
    }

    /**
     * reads a card by its uuid
     * @param cardUUID
     * @return the card (null=not found)
     */
    public Card readCardByUUID(String cardUUID) {
        Card karte = null;
        for (Card entry : getCardList()) {
            if (entry.getCardUUID().equals(cardUUID)) {
                karte = entry;
            }
        }
        return karte;
    }

    /**
     * reads all Decks
     * @return list of decks
     */
    public List<Deck> readAllDecks() {

        return getDeckList();
    }

    /**
     * reads a publisher by its uuid
     * @param deckUUID
     * @return the Deck (null=not found)
     */
    public Deck readDeckByUUID(String deckUUID) {
        Deck deck = null;
        for (Deck entry : getDeckList()) {
            if (entry.getDeckUUID().equals(deckUUID)) {
                deck = entry;
            }
        }
        return deck;
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
                getCardList().add(cards);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the publishers from the JSON-file
     */
    private void readDeckJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("deckJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Deck[] decks = objectMapper.readValue(jsonData, Deck[].class);
            for (Deck deck : decks) {
                getDeckList().add(deck);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * gets cardList
     *
     * @return value of cardList
     */
    private List<Card> getCardList() {
        return cardList;
    }

    /**
     * sets cardList
     *
     * @param cardList the value to set
     */
    private void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    /**
     * gets publisherList
     *
     * @return value of publisherList
     */
    private List<Deck> getDeckList() {
        return deckList;
    }

    /**
     * sets deckList
     *
     * @param deckList the value to set
     */
    private void setDeckList(List<Deck> deckList) {
        this.deckList = deckList;
    }


}