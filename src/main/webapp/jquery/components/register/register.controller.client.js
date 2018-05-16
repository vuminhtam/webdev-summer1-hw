(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld,
        $firstNameFld, $lastNameFld, $emailFld, $phoneFld;
    var $dobFld;
    var $roleFld;
    var $registerBtn;
    var userJS;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $verifyPasswordFld= $('#confirmPWFld');
        $firstNameFld = $('#firstNameFld');
        $lastNameFld = $('#lastNameFld');
        $emailFld = $('#emailFld');
        $phoneFld = $('#phoneFld');
        $dobFld = $('#birthDateFld');
        $roleFld = $('#roleFld');

        $registerBtn = $('#createBtn').click(register);
    }

    // function register() {
    //     if($usernameFld.val() != ""
    //         && $passwordFld.val() != ""
    //         && $verifyPasswordFld.val() != "") {
    //         if($passwordFld.val() === $verifyPasswordFld.val()) {
    //             console.log('pw match.');
    //             userService
    //                 .findUserByUsername($usernameFld.val(), $passwordFld.val())
    //                 .then(handleQuery);
    //         }
    //         else {
    //             alert("Password and Confirm Password does not match.");
    //         }
    //     }
    //     else {
    //         alert("Require username, password, and confirm password!");
    //     }
    // }

    function register() {
        userJS = {
            username: $usernameFld.val(),
            password: $passwordFld.val(),
            firstName: $firstNameFld.val(),
            lastName: $lastNameFld.val(),
            email: $emailFld.val(),
            phone: $phoneFld.val(),
            DOB: $dobFld.val(),
            role: $roleFld.val()};

        if($usernameFld.val() != ""
            && $passwordFld.val() != ""
            && $verifyPasswordFld.val() != "") {
            if($passwordFld.val() === $verifyPasswordFld.val()) {
                console.log('pw match!!');
                userService.register(userJS)
                    .then(handleResponse);
            }
            else {
                alert("Password and Confirm Password does not match.");
            }
        }
        else {
            alert("Require username, password, and confirm password!");
        }
    }


    //
    // function handleQuery(usernameQuery) {
    //     if(usernameQuery.length === 0) {
    //         //create user in database
    //         userService.createUser(userJS);
    //         alert("Successfully signed up!");
    //     }
    //     else {
    //         alert("Username already exists!");
    //     }
    // }


    function handleResponse(response) {
        if(response.status === 500) {
            alert(response.message);
        }
        else {
            alert('Created user!');
            renderProfile(response.id);

        }
    }

    function renderProfile(id) {
        window.location.href = "../profile/profile.template.client.html?uid=" + id;
    }
})();
