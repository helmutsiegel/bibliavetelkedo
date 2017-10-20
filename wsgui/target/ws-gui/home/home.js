document.getElementById("homeh2").innerHTML = "Üdv " + getCookie("fname") + " " + getCookie("lname") + "!";

function startGame() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var response = JSON.parse(this.responseText);
            if (response.answer === "true") {
                window.location.href='Game';
            } else {
                alert("Nem sikerult a jatekot letrehozni!");
            }
        }
    };

    xhttp.open("GET", "rest/game/startgame?username=" + getCookie("username"), true);
    xhttp.send();
}