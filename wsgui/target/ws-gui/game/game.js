function getGame() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var response = JSON.parse(this.responseText);
            if (response.answer == "true") {
                alert("van")
            } else {
                alert("no")
            }
        }
    };

    xhttp.open("GET", "rest/game/hasgame?username=" + getCookie("username"), true);
    xhttp.send();

}