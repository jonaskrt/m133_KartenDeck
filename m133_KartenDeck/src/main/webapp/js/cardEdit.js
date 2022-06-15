/**
 * View-Controller für cardedit.html
 *
 */

$(document).ready(function () {
    loadDecks();
    loadCards();

    /**
     * listener for submitting the form
     */
    $("#cardditForm").submit(saveCard);

    /**
     * listener for button [abbrechen], redirects to bookshelf
     */
    $("#cancel").click(function () {
        window.location.href = "./card.html";
    });


});

function loadCard() {
    let oldCardUUID = $.urlParam("cardUUID");
    $("#oldCardUUID").val(oldCardUUID);
    if (oldCardUUID) {
        $
            .ajax({
                url: "./kartenDeck/card/check?cardUUID=" + oldCardUUID,
                dataType: "json",
                type: "GET"
            })
            .done(showcard)
            .fail(function (xhr, status, errorThrown) {
                if (xhr.status == 403) {
                    window.location.href = "./login.html";
                } else if (xhr.status == 404) {
                    $("#message").text("Kein card gefunden");
                } else {
                    window.location.href = "./card.html";
                }
            })
    }

}


function loadDecks() {
    $
        .ajax({
            url: "./kartenDeck/deck/list",
            dataType: "json",
            type: "GET"
        })
        .done(showHersteller)
        .fail(function (xhr, status, errorThrown) {
            if (xhr.status == 404) {
                $("#message").text("Kein Deck gefunden");
            } else {
                window.location.href = "./card.html";
            }
        })
}

function loadCards() {
    $
        .ajax({
            url: "./kartenDeck/card/list",
            dataType: "json",
            type: "GET"
        })
        .done(showAirlines)
        .fail(function (xhr, status, errorThrown) {
            if (xhr.status == 404) {
                $("#message").text("Keine Card gefunden");
            } else {
                window.location.href = "./card.html";
            }
        })
}


function showcard(card) {
    $("#card").val(card.cardUUID);
    if (card.cardUUID == null){
        $("#card").val("-");
    }
    else {
        $("#card").val(card.cardUUID);
    }
    $("#cardTyp").val(card.cardTyp);
    $("#cardUUID").val(card.cardUUID);
}


function savecard(form) {
    form.preventDefault();

    $("#newDeckUUID").val($("#hersteller").val());
    $("#newCardUUID").val($("#airline").val());

    let url = "./kartenDeck/card/";
    let type;

    let oldCardUUID = $("#oldCardUUID").val();
    if (oldCardUUID) {
        type= "PUT";
        url += "update"
    } else {
        type = "POST";
        url += "insert";
    }

    $
        .ajax({
            url: url,
            dataType: "text",
            type: type,
            data: $("#cardEditForm").serialize()
        })
        .done(function (jsonData) {
            window.location.href = "./card.html"
        })
        .fail(function (xhr, status, errorThrown) {
            if (xhr.status == 404) {
                $("#message").text("Dieses card existiert nicht");
            } else {
                $("#message").text("Fehler beim Speichern des cards");
            }
        })
}