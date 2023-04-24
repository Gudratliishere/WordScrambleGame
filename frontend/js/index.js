const startButton = document.querySelector(".start")
const loginButton = document.querySelector(".login")
const createButton = document.querySelector(".create")
const backButton = document.querySelector(".back")
const usernameInput = document.querySelector(".username")
const passwordInput = document.querySelector(".password")
const messageText = document.querySelector(".message")

startButton.addEventListener("click", function () {
    if (usernameInput.value !== '') {
        messageText.innerText = ""
        loginPanelEnable()
    } else
        messageText.innerText = "Please fill username!"
});

loginButton.addEventListener("click", function () {
    if (passwordInput.value === '')
        messageText.innerText = "Please fill password"
    else {
        messageText.innerText = ""
        $.ajax({
            type: "GET",
            url: 'http://127.0.0.1:8080/auth/user/' + usernameInput.value.toLowerCase(),
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response === true) {
                    login()
                } else {
                    messageText.innerText = "This username doesn't exist, create new one!";
                }
            }
        })
    }
});

createButton.addEventListener("click", function () {
    if (passwordInput.value === '')
        messageText.innerText = "Please fill password"
    else {
        messageText.innerText = ""
        $.ajax({
            type: "GET",
            url: 'http://127.0.0.1:8080/auth/user/' + usernameInput.value.toLowerCase(),
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response === false) {
                    $.ajax({
                        type: "POST",
                        url: 'http://127.0.0.1:8080/auth/register',
                        data: JSON.stringify({
                            "username": usernameInput.value.toLowerCase(),
                            "password": passwordInput.value.toLowerCase()
                        }),
                        contentType: "application/json; charset=utf-8",
                        success: function () {
                            login()
                        },
                        error: function () {
                            messageText.innerText = "Something went wrong, try again!";
                        }
                    })
                } else {
                    messageText.innerText = "This username already exists, choose another one!";
                }
            }
        })
    }
});

backButton.addEventListener("click", function () {
    loginPanelDisable()
});


usernameInput.addEventListener("keyup", (e) => {
    if (e.code === "Enter") {
        startButton.click()
        passwordInput.focus()
    }
});

passwordInput.addEventListener("keyup", (e) => {
    if (e.code === "Enter") loginButton.click()
});

function login() {
    $.ajax({
        type: "POST",
        url: 'http://127.0.0.1:8080/auth/login',
        data: JSON.stringify({
            "username": usernameInput.value.toLowerCase(),
            "password": passwordInput.value.toLowerCase()
        }),
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            window.localStorage.token = response.token;
            window.localStorage.username = usernameInput.value.toLowerCase()
            window.location.href = "game.html";
        },
        error: function () {
            messageText.innerText = "Password is incorrect!";
        }
    })
}

function loginPanelEnable() {
    startButton.style.display = "none";
    loginButton.style.display = "block";
    createButton.style.display = "block";
    backButton.style.display = "block";
    usernameInput.style.display = "none";
    passwordInput.style.display = "block";
}

function loginPanelDisable() {
    startButton.style.display = "block";
    loginButton.style.display = "none";
    createButton.style.display = "none";
    backButton.style.display = "none";
    usernameInput.style.display = "block";
    passwordInput.style.display = "none";
}