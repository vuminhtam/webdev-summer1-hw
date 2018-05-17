//a JS class: direct communicate with the server as a client
function UserServiceClient() {
    this.findAllUsers = findAllUsers;
    this.createUser = createUser;
    this.deleteUser = deleteUser;
    this.findUserByID = findUserByID;
    this.updateUser = updateUser;
    this.findUserByUsername = findUserByUsername;
    this.register = register;
    this.login = login;
    this.logout = logout;

    this.registerURL = "/api/register";
    this.profileURL = '/api/profile';
    this.findUsernameURL = '/api/findByUsername';
    this.loginURL = '/api/login';
    this.logoutURL = '/api/logout';
    this.url = '/api/user';
    var self = this;

    //return all users info in the database
    function findAllUsers() {
        return fetch(self.url)
            .then(function(response) {
            return response.json();
        });
    }

    //return a user info by their id
    function findUserByID(userID) {
        return fetch(self.url + '/' + userID)//default = get
            .then(function(response) {
                return response.json();
            });
    }

    //look up a user by username
    function findUserByUsername(username) {
        var url = self.findUsernameURL + '/' + username;
        return fetch(url)
            .then(function(response) {
                return response.json();
            });
    }

    //register a user
    function register(user) {
        console.log("current register");
        return fetch(self.registerURL, {
            method: 'post',
            body: JSON.stringify(user), //convert json to string
            headers: {
                'content-type': 'application/json' //notify the server to know the post file is json
            }
        }).then(function(response) {
           return response.json();
        });
    }

    //add a user into the database
    function createUser(user) {
        console.log("create user");
        console.log(JSON.stringify(user));
        //send to server json input
        //return a promise
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user), //convert json to string
            headers: {
                'content-type': 'application/json' //notify the server to know the post file is json
            }
        });
    }

    //delete a user in database by ID
    function deleteUser(userID) {
        console.log("delete user");
        return fetch(self.url
            + '/' + userID, {//SEND THE USER ID TO DELETE
            method: 'delete'})
    }



    //update the info of the user
    function updateUser(user) {
        console.log("update user");
        return fetch(self.profileURL, { //updateByUserID
            method: 'put',
            body: JSON.stringify(user), //convert json to string
            headers: {
                'content-type': 'application/json' //notify the server to know the post file is json
            }})
            // .then(function(response) {
            //     if(response.bodyUsed) {
            //         return response.json();
            //     }
            //     else {
            //         return null;
            //     }})
            ;
    }

    //verify log in credentials and route to user profile page
    function login(username, password) {
        return fetch(self.loginURL, {
            method: 'post',
            body: JSON.stringify(
                {username: username, password: password}
            ),
            headers: {
                'content-type': 'application/json'
            }});
    }

    function logout() {
        return fetch(self.logoutURL, {
            method: 'post',
            headers: {
                'content-type': 'application/json'
            }});
    }
}