//the client controller class
(function (){
    jQuery(main); //call after content loaded
    var body;
    var tempRow;
    var userService = new UserServiceClient(); //a "model" for client

    function main() {
        tempRow = $('.template');
        body = $('tbody');
        $('#createUser').click(createUser);
        findAllUsers();
    }

    function createUser() {
        //grab info
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();

        //build a user json object
        var userJS = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName
        };

        userService
            .createUser(userJS)//send to the service the new user info
            .then(findAllUsers)//reload with new user info
    }

    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }

    function renderUsers(newUsers) {
        for(var i = 0; i < newUsers.length; i++) {
            var user = newUsers[i];
            var cloneTemplate = tempRow.clone();
            cloneTemplate.find('.username').html(user.username);
            body.append(cloneTemplate);
        }
    }

})();