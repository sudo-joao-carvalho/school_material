'use strict';
const panelControl = document.querySelector('#panel-control');
const panelGame = document.querySelector('#game');
const btLevel = document.querySelector('#btLevel');
const btPlay = document.querySelector('#btPlay');
const labelGameTime = document.querySelector('#gameTime');
const labelPoints = document.querySelector('#points');

let cardsLogos = ['angular', 'bootstrap', 'html', 'javascript', 'vue', 'svelte', 'react', 'css', 'backbone', 'ember'];
let cards = document.querySelectorAll('.card');
let flippedCards = [];
let totalFlippedCards = 0;

const TIMEOUTGAME = 10;
let timer;
let timerId;
let totalPoints = 0;

let topGamers = [
     {nickname: 'Ze', points: 1},
     {nickname: 'Maria', points: 2},
];

(function (){
     console.log(localStorage.getItem('Nick Name'));

     let topLocalStorage = JSON.parse('Top 10');
     console.log(localStorage.getItem('Top 10'));
     console.log(topLocalStorage);
})();

/*const getLocalStorage = () => {
     console.log(localStorage.getItem('Nick Name'));

     let topLocalStorage = JSON.parse('Top 10');
     console.log(localStorage.getItem('Top 10'));
     console.log(topLocalStorage);
}*/

const saveTop10 = () => {

     let singlePlayer = {nickname : document.querySelector('#inputNick').value, points : totalPoints};

     //console.log(document.querySelector('#inputNick').value);
     //console.log(totalPoints);
     //console.log(singlePlayer.nickname + " " + singlePlayer.points);

     topGamers.map((player, index) => {
          
          if(singlePlayer.nickname === player.nickname){
               if(singlePlayer.points > player.points){
                    player.nickname = singlePlayer.nickname;
                    player.points = singlePlayer.points;
               }
          }else{
               if(index === topGamers.length - 1){
                    topGamers.push(singlePlayer);
               }
          }

     })

     if(topGamers.length > 10){
          topGamers.pop();
     }

     topGamers.sort((a, b) => b.points - a.points);

     //******************
     //  LOCAL STORAGE
     //******************
     localStorage.setItem('Nick Name', singlePlayer.nickname);
     localStorage.setItem('Top 10', () => {
          const topGamersNew = topGamers.map((player) => ({ nickname: player.nickname, points: player.points }));
          return JSON.stringify(topGamersNew);
     });

}

const checkTop10 = (player) => {

     if(topGamers.length < 10 || player.points > getLastPoints()){
          let nick = document.querySelector('#nickname');
          nick.display = 'block';

          document.querySelector('#messageGameOver').innerHTML += "<br>Parabéns! Entrou no TOP 10!"
     }
          
}

const getLastPoints = () => {return topGamers[topGamers.length-1].points};

const getTopPoints = () => {

     let pointsTop = document.querySelector('#pointsTop');

     pointsTop.textContent = topGamers[0].points;

}

const getTop10 = () => {

     const infoTop = document.querySelector("#infoTop");
     /*let string = '';
 
     topGamers.forEach(player => {
         string += player.nickname + " - " + player.points + '<br>'; //normalmente fariamos <br>ola</br>(exemplo, pq como br é break de linha nunca la ia ter nada escrito dentro), mas se nao existir conteudo entre as tags posso fazer logo <br/>
     })
 
     infoTop.innerHTML = string;*/

     let firstDiv = document.createElement('div');
     firstDiv.setAttribute('class', 'info');
     firstDiv.setAttribute('id', 'infoTop');

     let firstChild = document.createElement('div');
     firstDiv.appendChild(firstChild);

     let pOne = document.createElement('p');
     pOne.textContent = "Nick Name";

     let pTwo = document.createElement('p');
     pTwo.textContent = "Pontuação";

     firstChild.appendChild(pOne);
     firstChild.appendChild(pTwo);

     topGamers.forEach((player) => {
          let playerDiv = firstChild.cloneNode(true);
          playerDiv.firstChild.textContent = player.nickname;
          playerDiv.lastChild.textContent = player.points;
          firstDiv.appendChild(playerDiv);
     })

     /*for(let i = 0; i < topGamers.length; i++){
          let playerDiv = firstChild.cloneNode(true);
          playerDiv.firstChild.textContent = topGamers[i].nickname;
          playerDiv.lastChild.textContent = topGamers[i].points;
          firstDiv.appendChild(playerDiv);

          let playerDiv = document.createElement('div');
          let playerPOne = document.createElement('p');
          let playerPTwo = document.createElement('p');
          firstDiv.appendChild(playerDiv);
          playerDiv.appendChild(playerPOne);
          playerDiv.appendChild(playerPTwo);

          
          playerPOne.textContent = topGamers[i].nickname;
          playerPTwo.textContent = topGamers[i].points;
          console.log(playerPOne);
     }*/

     infoTop.appendChild(firstDiv);
     getLocalStorage();
}

const gameOver = () => { return totalFlippedCards === cards.length; }

const shuffleArray = array => {
     for (let i = array.length - 1; i > 0; i--) {
          const j = Math.floor(Math.random() * (i + 1));
          const temp = array[i];
          array[i] = array[j];
          array[j] = temp;
     }
}
function updatePoints(operation = '+') {
     operation === '+'
          ? totalPoints += (timer * (cards.length / 2))
          : totalPoints < 5 ? totalPoints = 0 : totalPoints -= 5;
     labelPoints.textContent = totalPoints;
}
function updateGameTime() {
     timer--;
     if (timer < 10) labelGameTime.style.backgroundColor = 'red';
     labelGameTime.textContent = `${timer}s`;
     if (timer == 0) stopGame();
}
function flipCard(selectedCard) {
     selectedCard.classList.add('flipped');
     flippedCards.push(selectedCard);
     if (flippedCards.length === 2)
          checkPair();
}
function checkPair() {
     let [card1, card2] = flippedCards;
     const isMatch = (card1.dataset.logo === card2.dataset.logo);
     if (isMatch) {
          setTimeout(() => {
               card1.classList.add('inative'); card2.classList.add('inative');
               card1.querySelector('.card-front').classList.add('grayscale');
               card2.querySelector('.card-front').classList.add('grayscale');
               totalFlippedCards += 2;
               updatePoints('+');
               if (gameOver()) stopGame();
          }, 500);
     }
     else {
          setTimeout(() => {
               card1.classList.remove('flipped'); card2.classList.remove('flipped');
               card1.addEventListener('click', funClickCard, { once: true });
               card2.addEventListener('click', funClickCard, { once: true });
               updatePoints('-');
          }, 500);
     }
     flippedCards = [];
}
function createPanelGame() {
     const backupCards = [...cards];
     panelGame.innerHTML = '';
     backupCards.forEach(c => panelGame.appendChild(c.cloneNode(true)));
     cards = panelGame.childNodes;
}
function reset() {
     [btLevel.disabled, btLevel.value, btPlay.disabled, panelGame.style.display, message.textContent] = [true, 1, false, 'grid', ''];
     message.classList.remove('hide');
     panelControl.querySelectorAll('.list-item').forEach(elemento => elemento.classList.remove('gameStarted'));
     createPanelGame();
     [labelGameTime.textContent, labelPoints.textContent] = [`${TIMEOUTGAME}s`, 0];
}
const createAndShuffleCards = array => {
     shuffleArray(array);
     array.splice(cards.length / 2, Number.MAX_VALUE);
     array.push(...array);
     shuffleArray(array)
}

function funClickCard() { flipCard(this); }

function startGame() {
     btPlay.textContent = 'Terminar Jogo';
     panelControl.querySelectorAll('.list-item').forEach(elemento => elemento.classList.add('gameStarted'));
     message.classList.add('hide');

     let [indice, newCardLogos] = [0, [...cardsLogos]];
     createAndShuffleCards(newCardLogos);
     for (let card of cards) {
          card.querySelector('.card-front').src = `images/${newCardLogos[indice]}.png`;
          card.dataset.logo = newCardLogos[indice++];
          card.addEventListener('click', funClickCard, { once: true });
     }
     [flippedCards, totalFlippedCards, totalPoints] = [[], 0, 0];
     [timer, timerId] = [TIMEOUTGAME, setInterval(updateGameTime, 1000)];
     [labelGameTime.textContent, labelPoints.textContent] = [`${timer}s`, 0];
     labelGameTime.removeAttribute('style');

     getTopPoints();
}
function stopGame() {
     [btPlay.textContent, modalGameOver.style.display, document.querySelector('#messageGameOver').textContent] = ['Iniciar Jogo', 'block', `Pontuação:${totalPoints}`];
     clearInterval(timerId);
     checkTop10();
     reset();
}
btLevel.addEventListener('change', reset);
btPlay.addEventListener('click', () => btPlay.textContent === 'Terminar Jogo' ? stopGame() : startGame());
panelGame.addEventListener('click', () => message.textContent === '' ? message.textContent = 'Clique em Iniciar o Jogo!' : message.textContent = '');
btTop.addEventListener('click', getTop10);
document.getElementById('okTop').addEventListener('click', () => {
     saveTop10();
     modalGameOver.style.display = 'none';
     reset();
})
reset();

