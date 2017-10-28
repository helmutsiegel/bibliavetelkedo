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
                alert("Helytelen felhasználónév vagy jelszó!")
            }
        }
    };

    xhttp.open("GET", "rest/login?username=" + obj.username + "&password=" + obj.password, true);
    xhttp.send();

}

function registartion() {
    var fails = false;
    var failMessage = "";
    if (obj.fname.length <= 1) {
        fails = true;
        failMessage += "a keresztnév tul rövid, ";
    }
    if (obj.lname.length <= 1) {
        fails = true;
        failMessage += "a vezetéknév tul rövid, ";
    }
    if (obj.regusername.length <= 3) {
        fails = true;
        failMessage += "a felhasználónév tul rövid, ";
    }
    if (obj.password1 != obj.password2) {
        fails = true;
        failMessage += "a két jelszó nem egyezik meg, ";
    }else{
        if (obj.password1.length <= 3) {
            fails = true;
            failMessage += "a jelszó tul rövid, ";
        }
    }
    
    if (fails == false) {

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
                if (response.successfull) {
                    window.location.href = 'RegSuccessfull';
                } else {
                    if (response.messages[0] == "username_exists") {
                        document.getElementById('myModal').style.display = "none";
                        alert("A felhasználónév már használatban van!");
                    }
                }
            }
        };

        xhttp.open("POST", "rest/registration", true);
        xhttp.setRequestHeader("Content-Type", "application/json");
        xhttp.send(msg);
        document.getElementById('myModal').style.display = "block";
    } else {
        alert(failMessage);
    }
}