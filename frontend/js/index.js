const startButton = document.querySelector(".start")
const loginButton = document.querySelector(".login")
const createButton = document.querySelector(".create")
const usernameInput = document.querySelector(".username")
const passwordInput = document.querySelector(".password")
const messageText = document.querySelector(".message")

startButton.addEventListener("click", function () {
    startButton.style.display = "none";
    loginButton.style.display = "block";
    createButton.style.display = "block";
    usernameInput.style.display = "none";
    passwordInput.style.display = "block";
});

loginButton.addEventListener("click", function ()
{
    $.ajax({
        type: "GET",
        url: 'http://127.0.0.1:8080/auth/user/' + usernameInput.textContent,
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            if (response === true) {
                $.ajax({
                    type: "POST",
                    url: 'http://127.0.0.1:8080/auth/login',
                    contentType: "application/json; charset=utf-8",
                    success: function (response)
                    {
                        $window.localStorage.token = response;
                    }
                })
            } else {
                messageText.innerText = "This username doesn't exist, create new one!";
            }
        }
    })
});

usernameInput.addEventListener("keyup", (e) => {
    if (e.code === "Enter") startButton.click()
})