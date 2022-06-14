package ch.bzz.kartenDeck.service;

import ch.bzz.kartenDeck.data.DataHandler;
import ch.bzz.kartenDeck.model.Deck;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.UUID;

/**
 * services "deck.java"
 * more or less
 */

@Path("Deck")
public class deckService {

    /**
     * returns us our saved data
     * @return Text
     */
    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response test(
            @CookieParam("token") String token
    ){
        int status;
        String all = "";

        String[] role = {"admin", "maintenance", "user"};
        status = CheckCookie.checkCookie(token, role);

        if (status == 200){
            all = "\nJSON should be parsed now\n";
            for (HashMap.Entry entry : DataHandler.getDeckList().entrySet()){
                Deck x = (Deck) entry.getValue();
                if (x.getDeck() != null){
                    all += "\n\nDeck: \n" + "\tUUID: " + x.getDeckUUID() +  "\n"
                            + "--------------------------------";
                }
                else {
                    all += "\n\nDeck: \n" + "\tUUID: " + x.getDeckUUID()
                            + "--------------------------------";
                }

            }
        }

        Response response = Response
                .status(status)
                .entity(all)
                .build();

        return response;

    }


    /**
     * lists all Deck-JSONs
     * @return JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllDeck(
            @CookieParam("token") String token
    ){
        int status;
        String returnValue = "";

        String[] roles = {"admin", "maintenance", "user"};
        status = CheckCookie.checkCookie(token, roles);

        if (status == 200){
            returnValue = DataHandler.getDeckList().toString();
        }

        Response response = Response
                .status(status)
                .entity(returnValue)
                .build();

        return response;
    }


    /**
     * Deck to JSON
     * @param DeckUUID
     * @return JSON
     */
    @GET
    @Path("check")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkDeck(
            @QueryParam("DeckUUID") String DeckUUID,
            @CookieParam("token") String token
    ){
        int status;
        String returnValue = "";

        String[] roles = {"admin", "maintenance"};
        status = CheckCookie.checkCookie(token, roles);

        if (status == 200){
            Deck Deck = null;

            try {
                UUID.fromString(DeckUUID);
                if (DataHandler.getDeckList().get(DeckUUID) == null){
                    status = 404;       //Not found
                } else {
                    Deck = DataHandler.getDeckList().get(DeckUUID);
                }
            } catch (IllegalArgumentException e){
                status = 400;       //Bad Request
            }

            returnValue = DataHandler.getDeckList().toString();
        }

        Response response = Response
                .status(status)
                .entity(returnValue)
                .build();

        return response;

    }


    /**
     * Fügt ein neues Deck hinzu
     * @param herstellerUUID
     * @param DeckUUID
     * @param Deck
     * @return Text
     */
    @POST
    @Path("insert")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertDeck(
            @FormParam("herstellerUUID") String herstellerUUID,
            @FormParam("DeckUUID") String DeckUUID,
            @Valid @BeanParam Deck Deck,
            @CookieParam("token") String token
    ){
        int status;

        status = CheckCookie.checkCookie(token, "admin");

        if (status == 200){
            do {
                try {
                    //schaut, ob die UUIDs Formal korrekt sind & existieren
                    UUID.fromString(herstellerUUID);
                    if (DeckUUID.equals("-")){
                        DeckUUID = null;
                    }
                    if (DeckUUID != null) {
                        UUID.fromString(DeckUUID);
                    }
                    if (Deck.getDeckUUID().equals("")){
                        Deck.setDeckUUID(UUID.randomUUID().toString());
                    }

                    else {
                        status = 404;   //Not found
                        break;
                    }

                    if (DeckUUID != null && DataHandler.getDeckList().get(DeckUUID) != null){
                        Deck.setDeck(DataHandler.getDeckList().get(DeckUUID));
                        DataHandler.getDeckList().get(DeckUUID);
                    }
                    else {
                        status = 404;   //Not found
                        break;
                    }

                } catch (IllegalArgumentException e){
                    status = 400;       //Bad Request
                }
            }
            while (false);
        }

        Response response = Response
                .status(status)
                .entity("")
                .build();

        return response;
    }


    /**
     * Aktualisiert ein Deck
     * Es müssen nur die veränderten Daten als Parameter übergeben werden
     * @param DeckUUID
     * @param DeckUUID
     * @return Text
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateDeck(
            @FormParam("oldDeckUUID") String deckUUID,
            @FormParam("newDeckUUID") String DeckUUID,
            @Valid @BeanParam Deck Deck,
            @CookieParam("token") String token
    ){
        int status;

        String[] rollen = {"admin", "maintenance"};
        status = CheckCookie.checkCookie(token, rollen);

        if (status == 200){
            do {
                try {
                    UUID.fromString(DeckUUID);
                    //Dies ist das Deckobjekt, das abgeändert werden soll
                    Deck DeckImSpeicher = DataHandler.getDeckList().get(DeckUUID);

                    if (DeckImSpeicher != null){

                        if (DeckImSpeicher.getDeck() == null){
                        }
                        if (DeckUUID == null && DeckImSpeicher.getDeck() != null){
                            DataHandler.getDeckList().get(DeckImSpeicher.getDeck());
                        }
                    }


                    if (DeckUUID != null){
                        if (DeckUUID.equals("-")){
                            DeckUUID = null;
                        }
                    }

                    if (DeckUUID != null){
                        if (DataHandler.getDeckList().get(DeckUUID) == null){
                            status = 404;   //Not found
                            break;
                        }
                        else {
                            UUID.fromString(DeckUUID);
                            if (DeckImSpeicher.getDeck() != null){
                                DataHandler.getDeckList().get(DeckImSpeicher.getDeck());
                            }
                            Deck.setDeck(DataHandler.getDeckList().get(DeckUUID));
                            DataHandler.getDeckList().get(DeckUUID);
                        }
                    }

                    if (Deck.getDeckUUID() == null){
                        Deck.setDeckUUID(DeckImSpeicher.getDeckUUID());
                    } else {
                        UUID.fromString(Deck.getDeckUUID());
                    }


                }
                catch (IllegalArgumentException e){
                    status = 400;   //Bad Request
                }
            }
            while (false);
        }

        Response response = Response
                .status(status)
                .entity("")
                .build();

        return response;
    }


    /**
     * delete Deck
     * @param DeckUUID
     * @return Text
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteDeck(
            @QueryParam("DeckUUID") String DeckUUID,
            @CookieParam("token") String token
    ){
        int status;

        status = CheckCookie.checkCookie(token, "admin");

        if (status == 200){
            try {
                UUID.fromString(DeckUUID);

                if (DataHandler.getDeckList() != null){
                    HashMap<String, Deck> Deck = DataHandler.getDeckList();


                    if (DataHandler.getDeckList().containsKey(DeckUUID)){
                    }

                }
                else {
                    status = 404;   //Not found
                }
            }
            catch (IllegalArgumentException e){
                status = 400;   //Bad Request
            }
        }

        Response response = Response
                .status(status)
                .entity("")
                .build();

        return response;
    }
}