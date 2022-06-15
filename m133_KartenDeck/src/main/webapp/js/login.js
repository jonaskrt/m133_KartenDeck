/**
 * View-Controller for login.html
 *
 */

$(document).ready(function () {

    /**
     * listener for submitting the form sends the login data to the web service
     */
    $("#loginForm").submit(sendLogin);

    /**
     * listener for button [Abmelden]
     */
    $("#logoff").click(sendLogoff);

});

/**
 * Sendet den Login-Request
 * @param form
 */
function sendLogin(form) {
    form.preventDefault();
    $
        .ajax({
            url: "./kartenDeck/user/login",
            dataType: "text",
            type: "POST",
            data: $("#loginForm").serialize()
        })
        .done(function () {
            window.location.href = "./index.html";
        })
        .fail(function (xhr, status, errorThrown) {
            if (xhr.status == 404) {
                $("#message").text("Benutzername/Passwort unbekannt");
            } else {
                $("#message").text("Es ist ein Fehler aufgetreten");
            }
        })

}

/**
 * Sendet den Logoff-Request
 */
function sendLogoff() {
    $
        .ajax({
            url: "./kartenDeck/user/logoff",
            dataType: "text",
            type: "DELETE"
        })
        .done(function () {
            window.location.href = "./login.html";
        })
        .fail(function (xhr, status, errorThrown) {

            $("#message").text("Es ist ein Fehler aufgetreten");

        })
}