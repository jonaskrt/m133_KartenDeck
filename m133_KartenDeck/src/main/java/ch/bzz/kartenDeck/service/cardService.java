package ch.bzz.kartenDeck.service;

import ch.bzz.kartenDeck.data.DataHandler;
import ch.bzz.kartenDeck.model.Card;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.UUID;

/**
 * services "card.java"
 * more or less
 */

@Path("Card")
public class cardService {

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
            for (HashMap.Entry entry : DataHandler.getCardList().entrySet()){
                Card x = (Card) entry.getValue();
                if (x.getDeck() != null){
                    all += "\n\nCard: \n" + "\tUUID: " + x.getCardUUID() +  "\n"
                            + "--------------------------------";
                }
                else {
                    all += "\n\nCard: \n" + "\tUUID: " + x.getCardUUID()
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
     * lists all card-JSONs
     * @return JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllCard(
            @CookieParam("token") String token
    ){
        int status;
        String returnValue = "";

        String[] roles = {"admin", "maintenance", "user"};
        status = CheckCookie.checkCookie(token, roles);

        if (status == 200){
            returnValue = DataHandler.getCardList().toString();
        }

        Response response = Response
                .status(status)
                .entity(returnValue)
                .build();

        return response;
    }


    /**
     * card to JSON
     * @param CardUUID
     * @return JSON
     */
    @GET
    @Path("check")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkCard(
            @QueryParam("CardUUID") String CardUUID,
            @CookieParam("token") String token
    ){
        int status;
        String returnValue = "";

        String[] roles = {"admin", "maintenance"};
        status = CheckCookie.checkCookie(token, roles);

        if (status == 200){
            Card card = null;

            try {
                UUID.fromString(CardUUID);
                if (DataHandler.getCardList().get(CardUUID) == null){
                    status = 404;       //Not found
                } else {
                    card = DataHandler.getCardList().get(CardUUID);
                }
            } catch (IllegalArgumentException e){
                status = 400;       //Bad Request
            }

            returnValue = DataHandler.getCardList().toString();
        }

        Response response = Response
                .status(status)
                .entity(returnValue)
                .build();

        return response;

    }


    /**
     * Fügt ein neues Card hinzu
     * @param herstellerUUID
     * @param DeckUUID
     * @param Card
     * @return Text
     */
    @POST
    @Path("insert")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertCard(
            @FormParam("herstellerUUID") String herstellerUUID,
            @FormParam("DeckUUID") String DeckUUID,
            @Valid @BeanParam Card Card,
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
                    if (Card.getCardUUID().equals("")){
                        Card.setCardUUID(UUID.randomUUID().toString());
                    }

                    else {
                        status = 404;   //Not found
                        break;
                    }

                    if (DeckUUID != null && DataHandler.getDeckList().get(DeckUUID) != null){
                        Card.setDeck(DataHandler.getDeckList().get(DeckUUID));
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
     * Aktualisiert ein Card
     * Es müssen nur die veränderten Daten als Parameter übergeben werden
     * @param CardUUID
     * @param DeckUUID
     * @return Text
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateCard(
            @FormParam("oldCardUUID") String CardUUID,
            @FormParam("newDeckUUID") String DeckUUID,
            @Valid @BeanParam Card card,
            @CookieParam("token") String token
    ){
        int status;

        String[] rollen = {"admin", "maintenance"};
        status = CheckCookie.checkCookie(token, rollen);

        if (status == 200){
            do {
                try {
                    UUID.fromString(CardUUID);
  //Dies ist das Cardobjekt, das abgeändert werden soll
                    Card CardImSpeicher = DataHandler.getCardList().get(CardUUID);

                    if (CardImSpeicher != null){

                                if (CardImSpeicher.getDeck() == null){
                                }
                                if (DeckUUID == null && CardImSpeicher.getDeck() != null){
                                    DataHandler.getDeckList().get(CardImSpeicher.getDeck().getDeckUUID());
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
                                if (CardImSpeicher.getDeck() != null){
                                    DataHandler.getDeckList().get(CardImSpeicher.getDeck().getDeckUUID());
                                }
                                Card.setDeck(DataHandler.getDeckList().get(DeckUUID));
                                DataHandler.getDeckList().get(DeckUUID);
                            }
                        }

                        if (Card.getCardUUID() == null){
                            Card.setCardUUID(CardImSpeicher.getCardUUID());
                        } else {
                            UUID.fromString(Card.getCardUUID());
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
     * delete card
     * @param CardUUID
     * @return Text
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteCard(
            @QueryParam("CardUUID") String CardUUID,
            @CookieParam("token") String token
    ){
        int status;

        status = CheckCookie.checkCookie(token, "admin");

        if (status == 200){
            try {
                UUID.fromString(CardUUID);

                if (DataHandler.getCardList() != null){
                    HashMap<String, Card> card = DataHandler.getCardList();


                    if (DataHandler.getDeckList().containsKey(CardUUID)){
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