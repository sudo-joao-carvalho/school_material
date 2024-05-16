'use strict';
// ------------------------
// Variáveis Globais
// ------------------------
const TIMEOUTGAME = 20;

const panelControl = document.querySelector('#panel-control');
const panelGame = document.querySelector('#panel-game');
const btLevel = document.querySelector('#btLevel');
let cards = panelGame.querySelectorAll('.card');
const labelGameTime = document.getElementById("gameTime");
const labelPoints = document.getElementById("points");

let timer;
let timerId;

let totalPoints;

let cardsLogos = ['angular', 'bootstrap', 'html', 'javascript', 'vue', 'svelte', 'react', 'css', 'backbone', 'ember'];
let flippedCards = [];
let totalFlippedCards = 0;
let cardsListeners;

// Algoritmo Fisher-Yates -  Algoritmo que baralha um array.
const shuffleArray = array => {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        const temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

// ------------------------
// Funções Jogo memória
// ------------------------

function flipCard(selectedCard) {
    selectedCard.classList.add('flipped');
    flippedCards.push(selectedCard);
    if (flippedCards.length === 2)
        checkPair();
}

const gameOver = () => { return totalFlippedCards === cards.length; }

const createPanelGame = () =>{

    panelGame.className = "";
    panelGame.innerHTML = '';
    let div = document.createElement('div');
    div.setAttribute('class', 'card');

    let cardBack = document.createElement('img');
    cardBack.setAttribute('src', 'images/ls.png');
    cardBack.setAttribute('class', 'card-back');

    let cardFront = document.createElement('img');
    cardFront.setAttribute('class', 'card-front');

    div.appendChild(cardBack);
    div.appendChild(cardFront);

    //panelGame.appendChild(div);

    if(btLevel.value === "1"){
        panelGame.classList.add("basico");
        for(let i = 0; i < 6; i++){
            panelGame.appendChild(div.cloneNode(true));
        }
    }else if(btLevel.value === "2"){
        panelGame.classList.add("intermedio");
        for(let i = 0; i < 12; i++){
            panelGame.appendChild(div.cloneNode(true));
        }
    }else if(btLevel.value === "3"){
        panelGame.classList.add("avancado");
        for(let i = 0; i < 20; i++){
            panelGame.appendChild(div.cloneNode(true));
        }
    }
    
    cards = panelGame.childNodes;

}

const updatePoints = (operator) => {

    if(operator === "+"){
        totalPoints += timer * (cards.length / 2);
        labelPoints.textContent = totalPoints;
    }

    if(operator === "-"){
        totalPoints -= 5;
        labelPoints.textContent = totalPoints;
    }
}

const updateGameTime = () => {
    timer--;
    labelGameTime.textContent = timer + "s";

    if(timer <= 10){
        labelGameTime.style.backgroundColor = "red";
    }

    if(timer === 0)
        stopGame();
}

function checkPair() {
    let [card1, card2] = flippedCards;
    const isMatch = (card1.dataset.logo === card2.dataset.logo);
    if (isMatch) {
        setTimeout(() => { //serve para n vermos as mudanças imediatamente, as vezes de tao rapido nem se consegue verificar entao isto provoca um delay
            card1.classList.add('inative');
            card2.classList.add('inative');
            card1.querySelector('.card-front').classList.add('grayscale');
            card2.querySelector('.card-front').classList.add('grayscale');
            totalFlippedCards += 2;

            updatePoints("+");
            if (gameOver()) stopGame();
        }, 500);
    }
    else {
        setTimeout(() => {
            card1.classList.remove('flipped');
            card2.classList.remove('flipped');
            createEventListenerFlipCard(card1);
            createEventListenerFlipCard(card2);

            updatePoints("-");
        }, 500);
    }
    flippedCards = [];
}

const createAndShuffleCards = array => {
    shuffleArray(array);
    array.splice(cards.length / 2, Number.MAX_VALUE);
    array.push(...array);
    shuffleArray(array)
}

function startGame() {
    btPlay.textContent = 'Terminar Jogo';
    let [indice, newCardLogos] = [0, [...cardsLogos]];
    createAndShuffleCards(newCardLogos);
    cards.forEach(card => {
        card.querySelector('.card-front').src = `images/${newCardLogos[indice]}.png`;
        card.dataset.logo = newCardLogos[indice++];
        createEventListenerFlipCard(card);
    });
    flippedCards = [];
    totalFlippedCards = 0;

    timer = TIMEOUTGAME;
    timerId = setInterval(updateGameTime, 1000);

    totalPoints = 0;
    reset();
}

function stopGame() {
    modalGameOver.style.display = 'block';
    btPlay.textContent = 'Iniciar Jogo';

    document.getElementById("messageGameOver").textContent = `Pontuação: ${totalPoints}`;

    clearInterval(timerId);
}
function reset() {
    //btLevel.value = 1;
    //btLevel.disabled = true;
    for (let card of cards) card.classList.remove('flipped');
    createPanelGame();

    labelGameTime.removeAttribute('style');
    labelGameTime.textContent = TIMEOUTGAME + "s";

    //createPanelGame();
}
// --------------------------------------------------------
// Event Listeners
// --------------------------------------------------------
btPlay.addEventListener('click', function () {
    if (btPlay.textContent === 'Terminar Jogo')
        stopGame()
    else startGame();
});

const createEventListenerFlipCard = (c) => {
    c.addEventListener('click', function fc() {
        flipCard(this);
    }, { once: true });
}

const backupCards = [...cards];
//function createPanelGame() {
//    panelGame.innerHTML = "";
//    backupCards.forEach(c => panelGame.appendChild(c.cloneNode(true)));
//    cards = panelGame.childNodes;
//}