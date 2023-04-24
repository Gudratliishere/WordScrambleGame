const wordText = document.querySelector(".word")
const hint = document.querySelector(".hint span")
const hintField = document.querySelector(".hint")
const timeText = document.querySelector(".time b")
const refreshButton = document.querySelector(".refresh-word");
const checkButton = document.querySelector(".check-word");
const startButton = document.querySelector(".start");
const inputField = document.querySelector("input")
const messageText = document.querySelector(".message")
const pointSpan = document.querySelector(".point")
const myRankSpan = document.querySelector(".my-rank")
const myUsernameSpan = document.querySelector(".my-username")
const myPointSpan = document.querySelector(".my-point")
const playersRankDiv = document.querySelector(".players-rank")
const playersUsernameDiv = document.querySelector(".players-username")
const playersPointDiv = document.querySelector(".players-point")
const playersContainer = document.querySelector('.players-container')

let wordId, timer, win, timeout;

const initTimer = (maxTime) => {
    clearInterval(timer);
    timer = setInterval(() => {
        if (maxTime > 0) {
            maxTime--;
            return timeText.innerText = maxTime;
        }
        clearInterval(timer);
        decreasePoint(10)
        timeout = true
        messageText.innerText = `Time out!!`;
        messageText.style.color = "red";
    }, 1000);
}

const initGame = () => {
    initTimer(30);
    $.ajax({
        type: "GET",
        url: 'http://127.0.0.1:8080/word/random',
        contentType: "application/json; charset=utf-8",
        headers: {'Authorization': 'Bearer ' + window.localStorage.token},
        success: function (data) {
            wordText.innerText = data.name;
            wordId = data.wordId;
            hint.innerText = data.hint;
            inputField.value = "";
            inputField.setAttribute("maxlength", data.name.length.toString());
        }
    })

    refreshRank()
}


const startGame = () => {
    timeText.innerText = "30";
    startButton.style.display = "none";
    refreshButton.style.display = "block";
    checkButton.style.display = "block";
    hintField.style.display = "block";
    inputField.style.display = "block"
    messageText.innerText = "";
    playersContainer.style.display = "block"
    win = false
    timeout = false
    initGame()
}

const checkWord = () => {
    if (win !== true && timeout !== true) {
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
                headers: {'Authorization': 'Bearer ' + window.localStorage.token},
                success: function (response) {
                    if (response === true) {
                        increasePoint(30)
                        messageText.innerText = `Congrats! ${userWord} is a correct word.`;
                        messageText.style.color = "green";
                        clearInterval(timer)
                        win = true
                    } else {
                        decreasePoint(5)
                        messageText.innerText = `Oops! ${userWord} is not a correct word.`;
                        messageText.style.color = "red";
                    }
                }
            })
        }
    }
}
refreshButton.addEventListener("click", startGame)
checkButton.addEventListener("click", checkWord)
inputField.addEventListener("keyup", (e) => {
    if (e.code === "Enter") checkWord()
})
startButton.addEventListener("click", startGame);

function decreasePoint(point)
{
    showPointChange("Point: -" + point)
    $.ajax({
        type: "POST",
        url: 'http://127.0.0.1:8080/user/decreasePoint/' + point,
        contentType: "application/json; charset=utf-8",
        headers: {'Authorization': 'Bearer ' + window.localStorage.token}
    })
    refreshRank()
}

function increasePoint(point)
{
    showPointChange("Point: +" + point)
    $.ajax({
        type: "POST",
        url: 'http://127.0.0.1:8080/user/increasePoint/' + point,
        contentType: "application/json; charset=utf-8",
        headers: {'Authorization': 'Bearer ' + window.localStorage.token}
    })
    refreshRank()
}

function showPointChange (value)
{
    $("#point").fadeIn('slow').animate({opacity: 1.0}, 1500).delay(300).hide('slow');
    pointSpan.innerText = value
}

function refreshRank()
{
    fetchMyRank()
    fetchUsersRank()
}

function fetchMyRank ()
{
    $.ajax({
        type: "GET",
        url: 'http://127.0.0.1:8080/user/rank',
        contentType: "application/json; charset=utf-8",
        headers: {'Authorization': 'Bearer ' + window.localStorage.token},
        success: function (response) {
            myRankSpan.innerText = response.rank
            myUsernameSpan.innerText = response.username
            myPointSpan.innerText = response.point
        }
    })
}

function fetchUsersRank ()
{
    $.ajax({
        type: "GET",
        url: 'http://127.0.0.1:8080/user/playersRank',
        contentType: "application/json; charset=utf-8",
        headers: {'Authorization': 'Bearer ' + window.localStorage.token},
        success: function (response) {
            playersRankDiv.innerHTML = ''
            playersUsernameDiv.innerHTML = ''
            playersPointDiv.innerHTML = ''
            for (data of response) {
                const playerRankSpan = document.createElement('span');
                const playerUsernameSpan = document.createElement('span');
                const playerPointSpan = document.createElement('span');

                playerRankSpan.innerText = data.rank
                playerUsernameSpan.innerText = data.username
                playerPointSpan.innerText = data.point

                playersRankDiv.appendChild(playerRankSpan)
                playersRankDiv.appendChild(document.createElement('br'))
                playersUsernameDiv.appendChild(playerUsernameSpan)
                playersUsernameDiv.appendChild(document.createElement('br'))
                playersPointDiv.appendChild(playerPointSpan)
                playersPointDiv.appendChild(document.createElement('br'))
            }
        }
    })
}