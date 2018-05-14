(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $loginBtn = $('#loginBtn').click(login);
    }

    function login() {
        console.log("log in");
        if($usernameFld.val() === ""
            && $passwordFld.val() === "") {
            alert("username and password required!");
        }
        else {
            userService.login($usernameFld.val(), $passwordFld.val())
                .then(handleResponse);
        }
    }

    function handleResponse(reponse) {
        if(reponse.status === 200) {
            alert("Route to login!");
        }
        else {
            alert("Invalid credentials!");
        }
    }
})();