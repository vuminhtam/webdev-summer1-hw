//a JS class: direct communicate with the server as a client
function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    // this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    // this.updateUser = updateUser;
    this.url =
        'http://localhost:8080/api/user';
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
}