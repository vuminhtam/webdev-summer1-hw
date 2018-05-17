//the client controller class
(function (){

    var userService = new UserServiceClient(); //a "model" for client

    var body;
    var tempRow;

    var $removeBtn, $editBtn, $createBtn;
    jQuery(main); //call after content loaded

    function main() {
        tempRow = $('.template');
        body = $('tbody');
        $createBtn = $('#createUser').click(createUser);
        //tempRow.find('.deleteUser').click(deleteUser);
        findAllUsers();
    }


    function createUser() {
        //grab info
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var email = $('#emailFld').val();
        var phone = $('#phoneFld').val();
        var $DOB = $("#birthDate");
        console.log(birthDate);
        var role = $('#roleFld').val();

        if(username === ""
            || password === "") {
            alert("username and password required!");
        } else {
            //build a user json object
            var userJS = {
                username: username,
                password: password,
                firstName: firstName,
                lastName: lastName,
                email: email,
                phone: phone,
                dob: $DOB.val(),
                role: role
            };

            userService
                .register(userJS)//send to the service the new user info
                .then(handleResponse)
                .then(findAllUsers)//reload with new user info
        }
    }


    function handleResponse(response) {
        if(response.status === 500) {
            alert(response.message);
        }
        else {
            alert('Created user!');
            findAllUsers();
        }
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
            cloneTemplate.find('.email').html(user.email);
            cloneTemplate.find('.phone').html(user.phone);
            cloneTemplate.find('.birthdate').html(user.dob);
            cloneTemplate.find('.role').html(user.role);
            body.append(cloneTemplate);

            //add delete button click listener
            $removeBtn = cloneTemplate.find('.deleteBtn').click(deleteUser);
            $editBtn = cloneTemplate.find('.editBtn').click(updateUser);

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

    function updateUser(event) {
        console.log('edit user by admin');
        //get info = grab id att in button
        var btn = $(event.currentTarget);
        var row = btn.parent().parent();
        var userID = row.attr('id'); //get id
        window.location.href = "../profile/profile.template.client.html?uid=" + userID;
    }

    function formatPW(password) {
        if(password != null) {
            var s = "";
            for (var i = 0; i < password.length; i++) {
                s += '*';
            }
            return s;
        }
    }

})();