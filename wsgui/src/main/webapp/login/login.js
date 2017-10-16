var obj = {
    username: '',
    password: '',
    fname: '',
    lname: '',
    password1: '',
    password2: '',
    regusername: ''
};

ko.applyBindings(obj);

function login() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var response = JSON.parse(this.responseText);
            if (response.login == "true") {
                alert("yep")
            } else {
                alert("no")
            }
        }
    };

    xhttp.open("GET", "rest/login?username=" + obj.username + "&password=" + obj.password, true);
    xhttp.send();

}

function registartion() {

}