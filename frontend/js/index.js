const url = 'http://word-scramble-game-19c397df7ec8.herokuapp.com'

const startButton = document.querySelector(".start")
const loginButton = document.querySelector(".login")
const createButton = document.querySelector(".create")
const backButton = document.querySelector(".back")
const usernameInput = document.querySelector(".username")
const passwordInput = document.querySelector(".password")
const messageText = document.querySelector(".message")
const loading = document.querySelector(".loading")

startButton.addEventListener("click", function () {
    if (usernameInput.value !== '') {
        messageText.innerText = ""
        loginPanelEnable()
    } else
        messageText.innerText = "Zəhmət olmasa istifadəçi adını daxil edin!"
});

loginButton.addEventListener("click", function () {
    if (passwordInput.value === '')
        messageText.innerText = "Zəhmət olmasa parolu yazın."
    else {
        messageText.innerText = ""
        $.ajax({
            type: "GET",
            url: url + '/auth/user/' + usernameInput.value.toLowerCase(),
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response === true) {
                    login()
                } else {
                    messageText.innerText = "Bu istifadəçi mövcud deyil, yenisini yaradın!";
                }
            }
        })
    }
});

createButton.addEventListener("click", function () {
    if (passwordInput.value === '')
        messageText.innerText = "Zəhmət olmasa parolu yazın."
    else {
        messageText.innerText = ""
        showLoading()
        $.ajax({
            type: "GET",
            url: url + '/auth/user/' + usernameInput.value.toLowerCase(),
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response === false) {
                    $.ajax({
                        type: "POST",
                        url: url + '/auth/register',
                        data: JSON.stringify({
                            "username": usernameInput.value.toLowerCase(),
                            "password": passwordInput.value.toLowerCase()
                        }),
                        contentType: "application/json; charset=utf-8",
                        success: function () {
                            login()
                        },
                        error: function () {
                            hideLoading()
                            messageText.innerText = "Xəta baş verdi, yenidən cəhd edin!";
                        }
                    })
                } else {
                    hideLoading()
                    messageText.innerText = "Bu istifadəçi adına uyğun başqa bir hesab mövcuddur, zəhmət olmasa başqa bir ad seçin!";
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
    showLoading()
    $.ajax({
        type: "POST",
        url: url + '/auth/login',
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
            hideLoading()
            messageText.innerText = "Şifrə səhvdir!";
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

function showLoading ()
{
    passwordInput.style.display = "none";
    loading.style.display = "block";
}

function hideLoading ()
{
    passwordInput.style.display = "block";
    loading.style.display = "none";
}