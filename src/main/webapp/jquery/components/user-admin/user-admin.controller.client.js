//the client controller class
(function (){
    jQuery(main); //call after content loaded
    var userService = new UserServiceClient(); //a "model" for client

    var body;
    var tempRow;

    function main() {
        tempRow = $('.template');
        body = $('tbody');
        $('#createUser').click(createUser);
        //tempRow.find('.deleteUser').click(deleteUser);
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
        body.empty();//refresh the page
        for(var i = 0; i < newUsers.length; i++) {
            var user = newUsers[i];
            var cloneTemplate = tempRow.clone();
            cloneTemplate.attr('id', user.id);//mark the row with user id
            cloneTemplate.find('.username').html(user.username);
            cloneTemplate.find('.password').html(formatPW(user.password));
            cloneTemplate.find('.firstName').html(user.firstName);
            cloneTemplate.find('.lastName').html(user.lastName);
            body.append(cloneTemplate);

            //add delete button click listener
            cloneTemplate.find('.deleteBtn').click(deleteUser);
            cloneTemplate.find('.editBtn').click(editUser);

        }
    }

    function deleteUser(event) {
        console.log('delete user');
        //get info = grab id of the user as an att in button which is in the row
        //get the button
        var btn = $(event.currentTarget);
        var row = btn.parent().parent();
        var userID = row.attr('id'); //get id
        //delete by ID, update the list
        userService
            .deleteUser(userID)
            .then(findAllUsers);
    }

    function editUser(event) {
        console.log('edit user');
        //get info = grab username att in button

    }

    function formatPW(password) {
        var s = "";
        for (var i = 0; i < password.length; i++) {
            s += '*';
        }
        return s;
    }

})();