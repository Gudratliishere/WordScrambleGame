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
    messageText.innerText = "This username doesn't exist, create new one!";
});

usernameInput.addEventListener("keyup", (e) => {
    if (e.code === "Enter") startButton.click()
})