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
                window.location.href = "Home?&username=" + obj.username + "&password=" + obj.password;
            } else {
                alert("no")
            }
        }
    };

    xhttp.open("GET", "rest/login?username=" + obj.username + "&password=" + obj.password, true);
    xhttp.send();

}

function registartion() {
    var msg = JSON.stringify({
        firstName: obj.fname,
        lastName: obj.lname,
        username: obj.regusername,
        password: obj.password1
    });


    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var response = JSON.parse(this.responseText);
            alert(response);

        }
    };

    xhttp.open("POST", "rest/registration", true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(msg);

}