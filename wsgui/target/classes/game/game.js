var obj = {
    question: ko.observable(''),
    answer_a: ko.observable(''),
    answer_b: ko.observable(''),
    answer_c: ko.observable(''),
    answer_d: ko.observable('')

};

ko.applyBindings(obj);

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

function getNextQuestion() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var response = JSON.parse(this.responseText);
            obj.question(response.question);
            obj.answer_a(response.answerA);
            obj.answer_b(response.answerB);
            obj.answer_c(response.answerC);
            obj.answer_d(response.answerD);
        }
    };

    xhttp.open("GET", "rest/questions/getnext?username=" + getCookie("username"), true);
    xhttp.send();

}



function answer(ans){
    alert("You answerted "+ans.innerHTML);
}