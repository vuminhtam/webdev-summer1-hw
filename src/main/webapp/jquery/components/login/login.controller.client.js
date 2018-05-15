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

    //get the info of user from database
    function getIDbyUsername(username) {
        return userService.findUserByUsername(username)
            .then(function(user) {
                renderProfile(user.id);
            });
    }

    function handleResponse(reponse) {
        if(reponse.status === 200) {
            var username = $usernameFld.val();
            getIDbyUsername(username);
        }
        else {
            alert("Invalid credentials!");
        }
    }

    function renderProfile(id) {
        window.location.href = "../profile/profile.template.client.html?uid=" + id;
    }
})();
