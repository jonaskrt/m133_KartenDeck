package ch.bzz.kartenDeck.service;

import ch.bzz.kartenDeck.data.*;
import ch.bzz.kartenDeck.model.*;

/**
 * Cookie validator
 *
 */

public class CheckCookie {

    /**
     * meets token with needed cookie validation
     * @param token
     * @param role
     * @return status
     */
    public static int checkCookie(String token, String role){
        int status = 403;
        String decrypted = AESEncrypt.decrypt(token);
        String values[] = decrypted.split(";");

        return status;
    }

    /**
     * meets token with needed cookie validation
     * @param token
     * @param role
     * @return status
     */
    public static int checkCookie(String token, String[] role){
        int status = 403;

        if (token != null){
            String decrypted = AESEncrypt.decrypt(token);
            String values[] = decrypted.split(";");
        }
        else {
            status = 401;
        }
        return status;
    }
}
