var obj = {
    question: ko.observable(''),
    answer_a: ko.observable(''),
    answer_b: ko.observable(''),
    answer_c: ko.observable(''),
    answer_d: ko.observable(''),
    qid: null

};

ko.applyBindings(obj);

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
            obj.qid = response.id;
        }
    };

    xhttp.open("GET", "rest/questions/getnext?username=" + getCookie("username"), true);
    xhttp.send();

}


function answer(ans) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {

        }
    };

    xhttp.open("GET", "rest/questions/answer?username=" + getCookie("username")
        + "&qid=" + obj.qid + "&answer=" + ans, true);
    xhttp.send();
}