(function () {
    $(init);
    var userService = new UserServiceClient();
    var $firstName;
    var $lastName;
    var $userName;
    var $password;
    var $updateBtn;

    function init() {
        findUserByID(272);
        $firstName = $("#firstName");//fetch $ the element whose id # is firstName
        $lastName = $("#lastName");
        $userName = $("#userName");
        $password = $("#inputPassword");
        $updateBtn = $("#updateBtn");
        $updateBtn.click(updateUser);
    }

    //get the info of user from database
    function findUserByID(userID) {
        userService.findUserByID(userID)
            .then(renderUser);
    }

    //render the user profile
    function renderUser(user) {
        $userName.val(user.username);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
        $password.val(user.password);
    }

    //update the user with the current input when update button clicked
    function updateUser() {
        var newUser = {
            firstName: $firstName.val(),
            lastName: $lastName.val()
        };
        userService
            .updateUser(272, newUser)
            .then(confirmUserInfo(272));
            //.then(success);
    }

    function confirmUserInfo(userID) {
        userService.findUserByID(userID).then(outputUserInfo);
    }

    function outputUserInfo(user) {
        alert('first name ' + user.firstName +
            ' \nlast name ' + user.lastName);
    }

    //inform successfully update user info
    function success(response) {
        if(response === null) {
            alert('cannot update.');
        }
        else {
            alert('successfully updated user!');
        }
    }

})();