(function () {
    $(init);
    var userService = new UserServiceClient();
    var $firstName, $lastName, $userName, $role, $email;
    var $phone;
    var $DOB;
    var $password;
    var $updateBtn;
    //var $success;
    var testID = 432;

    function init() {
        findUserByID(testID);
        //$success = $("#alert").attr('hidden', true);
        $firstName = $("#firstName");//fetch $ the element whose id # is firstName
        $email = $("#email");
        $phone = $("#phone");
        $lastName = $("#lastName");
        $userName = $("#userName");
        $password = $("#inputPassword");
        $role = $("#role");
        $DOB = $("#DOB");
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
        $phone.val(user.phone);
        $email.val(user.email);
        $role.val(user.role);
        $DOB.val(user.dob);
    }

    //update the user with the current input when update button clicked
    function updateUser() {
        var newUser = {
            id: testID,
            firstName: $firstName.val(),
            lastName: $lastName.val(),
            phone: $phone.val(),
            email: $email.val(),
            role: $role.val(),
            dob: $DOB.val()
        };
        userService
            .updateUser(newUser)
            .then(handleResponse);
    }
    //
    // function confirmUserInfo(userID) {
    //     userService.findUserByID(userID)
    //         .then(outputUserInfo);
    // }
    //
    // function outputUserInfo(user) {
    //     alert('first name ' + user.firstName +
    //         ' \nlast name ' + user.lastName);
    // }
    //
    // //inform successfully update user info
    // function success(response) {
    //     if(response === null) {
    //         alert('cannot update.');
    //     }
    //     else {
    //         $('.alert').show();
    //         alert('successfully updated user!');
    //     }
    // }

    function handleResponse(reponse) {
        if(reponse.status === 200) {
            alert("Successfully update changes!");
        }
        else {
            alert("Fail to update.");
        }
    }

})();