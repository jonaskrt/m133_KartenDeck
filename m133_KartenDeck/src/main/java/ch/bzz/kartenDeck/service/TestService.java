package ch.bzz.kartenDeck.service;

import ch.bzz.kartenDeck.data.DataHandler;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.CookieManager;

/**
 * test service
 */
@Path("test")
public class TestService {

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response test(
            @CookieParam("token") String token
    ) {
        int status;
        String returnValue = "not authorised";

        String[] roles = {"admin", "maintenance"};
        status = CheckCookie.checkCookie(token, "admin");

        if (status == 200){
            returnValue = "It worked";
        }

        Response response = Response
                .status(status)
                .entity(returnValue)
                .build();
        return response;
    }

    @GET
    @Path("restore")
    @Produces(MediaType.TEXT_PLAIN)
    public Response restore(
            @CookieParam("token") String token
    ){
        int status;
        String returnValue = "not authorised";

        status = CheckCookie.checkCookie(token, "admin");

        if (status == 200){
            DataHandler.restoreData();
            returnValue = "Data reset";
        }

        Response response = Response
                .status(status)
                .entity(returnValue)
                .build();
        return response;

    }
}