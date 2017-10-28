var obj = {
    question: ko.observable(''),
    answer_a: ko.observable(''),
    answer_b: ko.observable(''),
    answer_c: ko.observable(''),
    answer_d: ko.observable(''),
    qid: null,
    kov: ko.observable(false),
    felez: ko.observable(false),
    hibaz: ko.observable(false),
    level: ko.observable(0),
    canContinue: ko.observable(false)

};

ko.applyBindings(obj);

function getNextQuestion() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var response = JSON.parse(this.responseText);

            obj.canContinue(response.canContinue);
            obj.question(response.question);
            obj.answer_a(response.answerA);
            obj.answer_b(response.answerB);
            obj.answer_c(response.answerC);
            obj.answer_d(response.answerD);
            obj.qid = response.id;
            obj.felez(response.canHalf);
            obj.kov(response.canNext);
            obj.hibaz(response.canFault);
            obj.level(response.level);

            document.getElementById("a").disabled = false;
            document.getElementById("b").disabled = false;
            document.getElementById("c").disabled = false;
            document.getElementById("d").disabled = false;

        }
    };

    xhttp.open("GET", "rest/questions/getnext?username=" + getCookie("username"), true);
    xhttp.send();

}

function answer(ans) {
    var xhttp = new XMLHttpRequest();
    var me = this;
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var resp = JSON.parse(xhttp.responseText)
            me.getNextQuestion();
        }
    };

    xhttp.open("GET", "rest/questions/answer?username=" + getCookie("username")
        + "&qid=" + obj.qid + "&answer=" + ans, true);
    xhttp.send();
}

function felez() {
    if (obj.felez() === true) {
        var xhttp = new XMLHttpRequest();
        var me = this;
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                var resp = JSON.parse(xhttp.responseText);
                var buttons = document.getElementsByTagName('button');
                for (var i = 0; i < buttons.length; i++) {
                    var button = buttons[i];
                    if (button.innerHTML == resp[0] || button.innerHTML == resp[1]) {
                        button.disabled = true;
                    }
                    obj.felez(false);
                }
            }
        };

        xhttp.open("GET", "rest/questions/halfing?username=" + getCookie("username")
            + "&qid=" + obj.qid, true);
        xhttp.send();
    }
}

function kovetkezo() {
    if (obj.kov() === true) {
        var xhttp = new XMLHttpRequest();
        var me = this;
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                var resp = JSON.parse(xhttp.responseText)
                if (resp == true || resp == "true") {
                    obj.kov(false);
                    me.getNextQuestion();
                }
            }
        };
        xhttp.open("GET", "rest/questions/nexting?username=" + getCookie("username")
            + "&qid=" + obj.qid, true);
        xhttp.send();
    }
}