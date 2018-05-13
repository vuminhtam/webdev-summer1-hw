//a JS class: direct communicate with the server as a client
function UserServiceClient() {
    this.findAllUsers = findAllUsers;
    this.createUser = createUser;
    this.deleteUser = deleteUser;
    this.findUserByID = findUserByID;
    this.updateUser = updateUser;
    this.register = registerUser;

    this.registerURL = 'http://localhost:8080/api/register';
    this.url = 'http://localhost:8080/api/user';
    var self = this;

    //return all users info in the database
    function findAllUsers() {
        return fetch(self.url)
            .then(function(response) {
            return response.json();
        });
    }

    //add a user into the database
    function createUser(user) {
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
        return fetch(self.url
            + '/' + userID, {//SEND THE USER ID TO DELETE
            method: 'delete'})
    }

    //return a user info by their id
    function findUserByID(userID) {
        return fetch(self.url + '/' + userID)//default = get
            .then(function(response) {
                return response.json();
            });
    }

    //update the info of the user
    function updateUser(userID, user) {
        return fetch(self.url + '/' + userID, { //updateByUserID
            method: 'put',
            body: JSON.stringify(user), //convert json to string
            headers: {
                'content-type': 'application/json' //notify the server to know the post file is json
            }})
            .then(function(response) {
                if(response.bodyUsed) {
                    return response.json();
                }
                else {
                    return null;
                }
        });
    }


    //register a user
    function registerUser(username, password) {
        return fetch(self.registerURL, {
            method: 'post',
            body: JSON.stringify(
                {username: username, password: password}
            ),
            headers: {
                'content-type': 'application/json'
            }});
    }
}