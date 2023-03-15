const wordText = document.querySelector(".word")
const hint = document.querySelector(".hint span")
const hintField = document.querySelector(".hint")
const timeText = document.querySelector(".time b")
const refreshButton = document.querySelector(".login");
const checkButton = document.querySelector(".create");
const startButton = document.querySelector(".start");
const inputField = document.querySelector("input")
const messageText = document.querySelector(".message")

let wordId, timer;

const initTimer = (maxTime) => {
    clearInterval(timer);
    timer = setInterval(() => {
        if (maxTime > 0) {
            maxTime--;
            return timeText.innerText = maxTime;
        }
        clearInterval(timer);
        messageText.innerText = `Time out!!`;
        messageText.style.color = "red";
    }, 1000);
}

const initGame = () => {
    initTimer(30);
    $.getJSON('http://127.0.0.1:8080/word/random', function (data) {
        wordText.innerText = data.name;
        wordId = data.wordId;
        hint.innerText = data.hint;
        inputField.value = "";
        inputField.setAttribute("maxlength", data.name.length.toString());
    });
}


const startGame = () => {
    timeText.innerText = "30";
    startButton.style.display = "none";
    refreshButton.style.display = "block";
    checkButton.style.display = "block";
    hintField.style.display = "block";
    messageText.innerText = "";
    initGame()
}

const checkWord = () => {
    let userWord = inputField.value.toLocaleLowerCase();
    if (!userWord) {
        messageText.innerText = "Please write a word!";
        messageText.style.color = "red";
    } else {
        $.ajax({
            type: "POST",
            url: 'http://127.0.0.1:8080/word/checkWord',
            data: JSON.stringify({"wordId": wordId, "name": userWord}),
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                    if (response === true) {
                        messageText.innerText = `Congrats! ${userWord} is a correct word.`;
                        messageText.style.color = "green";
                        clearInterval(timer)
                    } else {
                        messageText.innerText = `Oops! ${userWord} is not a correct word.`;
                        messageText.style.color = "red";
                    }
                }
        })
    }
}
refreshButton.addEventListener("click", startGame)
checkButton.addEventListener("click", checkWord)
inputField.addEventListener("keyup", (e) => {
    if (e.code === "Enter") checkWord()
})
startButton.addEventListener("click", startGame);
