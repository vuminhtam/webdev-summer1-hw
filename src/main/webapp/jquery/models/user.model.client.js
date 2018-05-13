function User(username, password, email, firstName, lastName,
              phone, role, DOB) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.role = role;
    this.DOB = DOB;

    //setters and getters
    this.setUsername = setUsername;
    this.getUsername = getUsername;
    this.setPW = setPW;
    this.getPW = getPW;
    this.setFirstName = setFirstName;
    this.getFirstName = getFirstName;
    this.setLastName = setLastName;
    this.getLastName = getLastName;
    this.setEmail = setEmail;
    this.getEmail = getEmail;
    this.setPhone = setPhone;
    this.getPhone = getPhone;
    this.setRole = setRole;
    this.getRole = getRole;
    this.setDOB = setDOB;
    this.getDOB = getDOB;

    function setUsername(username) {
        this.username = username;
    }
    function getUsername() {
        return this.username;
    }

    function setPW(password) {
        this.password = password;
    }

    function getPW() {
        return this.password;
    }

    function setFirstName(name) {
        this.firstName = name;
    }
    function getFirstName() {
        return this.firstName;
    }

    function setLastName(name) {
        this.lastName = name;
    }
    function getLastName() {
        return this.lastName;
    }

    function setEmail(email) {
        this.email = email;
    }
    function getEmail() {
        return this.email;
    }

    function setPhone(phone) {
        this.phone = phone;
    }
    function getPhone() {
        return this.phone;
    }

    function setRole(role) {
        this.role = role;
    }
    function getRole() {
        return this.role;
    }

    function setDOB(DOB) {
        this.DOB = DOB;
    }
    function getDOB() {
        return this.DOB;
    }
}
