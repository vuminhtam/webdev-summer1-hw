(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld,
        $firstNameFld, $lastNameFld, $emailFld, $phoneFld;
    var $dobFld;
    var $roleFld;
    var $registerBtn;
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

    function register() {
        console.log('register');
        if($passwordFld === $verifyPasswordFld) {
            userService.register($usernameFld, $passwordFld);
        }
        else {
            alert("Password and Confirm Password does not match.");
        }
    }
})();
