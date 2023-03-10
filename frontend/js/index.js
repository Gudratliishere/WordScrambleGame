const wordText = document.querySelector(".word")
const hint = document.querySelector(".hint span")
const hintField = document.querySelector(".hint")
const timeText = document.querySelector(".time b")
const refreshButton = document.querySelector(".refresh-word");
const checkButton = document.querySelector(".check-word");
const startButton = document.querySelector(".start");
const inputField = document.querySelector("input")
const messageText = document.querySelector(".message")

let correctWord, timer;

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
    let randomObj = words[Math.floor(Math.random() * words.length)];
    let wordArray = randomObj.word.split("");
    for (let i = wordArray.length - 1; i > 0; i--) {
        let j = Math.floor(Math.random() * (i + 1));
        [wordArray[i], wordArray[j]] = [wordArray[j], wordArray[i]];
    }
    wordText.innerText = wordArray.join("");
    hint.innerText = randomObj.hint;
    correctWord = randomObj.word.toLowerCase();
    inputField.value = "";
    inputField.setAttribute("maxlength", correctWord.length.toString());
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
    } else if (userWord !== correctWord) {
        messageText.innerText = `Oops! ${userWord} is not a correct word.`;
        messageText.style.color = "red";
    } else {
        messageText.innerText = `Congrats! ${userWord} is a correct word.`;
        messageText.style.color = "green";
        clearInterval(timer)
    }
}

refreshButton.addEventListener("click", startGame)
checkButton.addEventListener("click", checkWord)
inputField.addEventListener("keyup", (e) => {
    if (e.code === "Enter") checkWord()
})
startButton.addEventListener("click", startGame);