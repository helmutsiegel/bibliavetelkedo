function getCookie(name) {
    var cookies = document.cookie.split('; ');

    for (var i = 0; i < cookies.length; i++) {
        var pair = cookies[i].split('=');

        if (pair[0] == name) {
            return pair[1];
        }
    }
    return null;
}