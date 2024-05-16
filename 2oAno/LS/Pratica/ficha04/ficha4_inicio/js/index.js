'use strict';
const panelControl = document.querySelector('#panel-control');
const panelGame = document.querySelector('#game');
const btLevel = document.querySelector('#btLevel');
const btPlay = document.querySelector('#btPlay');

let cardsLogos = ['angular', 'bootstrap', 'html', 'javascript', 'vue', 'svelte', 'react', 'css', 'backbone', 'ember'];
let cards = document.querySelectorAll('.card');

let flippedCards;
let totalFlippedCards;

// ------------------------
// Funções Genéricas
// ------------------------
// Algoritmo Fisher-Yates -  Algoritmo que baralha um array.
const shuffleArray = array => {
     for (let i = array.length - 1; i > 0; i--) {
          const j = Math.floor(Math.random() * (i + 1));
          const temp = array[i];
          array[i] = array[j];
          array[j] = temp;
     }
}

/*function gameOver(){

     if(totalFlippedCards === cards.length){
          return true;
     }else return false;
}*/

/*const gameOver = () => {
     if(totalFlippedCards === cards.length) return true;
     return false;
}*/

const gameOver = () => {
     return totalFlippedCards === cards.length;
}

function funcClickCard(e){
     flipCard(this);
}

function checkPair(){

     const [card1, card2] = flippedCards;
     let isMatch = card1.dataset.logo === card2.dataset.logo;
     if(isMatch){
          setTimeout(() => {
               console.log("iguais");
               card1.classList.add("inative");
               card2.classList.add("inative");
               card1.querySelector(".card-front").classList.add("grayscale");
               card2.querySelector(".card-front").classList.add("grayscale");
               totalFlippedCards += 2;

               if(gameOver())
                    stopGame();
          }, 500)
     }else{
          setTimeout(() =>{
               console.log("Não são iguais");
               card1.classList.remove("flipped");
               card2.classList.remove("flipped");
               card1.addEventListener('click', function () { 
                    flipCard(this);
               }, {once: true});
               card2.addEventListener('click', function () { 
                    flipCard(this);
               }, {once: true});
          }, 500)
          
     }    

     flippedCards = [];

}

//adicionado por mim
function hideCards(cards){

     for(let i of cards){
         i.classList.remove("flipped");
     }
 }

function reset() {
     btLevel.disabled = true;
     btLevel.value = 1;
     btPlay.disabled = false;

     panelGame.style.display = "none";
     message.textContent = '';
     message.classList.remove('hide');
     panelGame.style.display = 'grid';

     const elementos = panelControl.querySelectorAll('.list-item');
     elementos.forEach(elemento => {
          elemento.classList.remove('gameStarted');
     });
}

function flipCard(selectedCard) {
     selectedCard.classList.add('flipped');

     flippedCards.push(selectedCard);

     if(flippedCards.length === 2){
          checkPair();
     }
     

}

function startGame() {

     flippedCards = [];
     totalFlippedCards = 0;

     btLevel.disabled = true;
     btPlay.textContent = 'Terminar Jogo';
     const elementos = panelControl.querySelectorAll('.list-item');
     elementos.forEach(elemento => {
          elemento.classList.add('gameStarted')
     });
     message.classList.add('hide');

     shuffleArray(cardsLogos);
     let [indice, newCardLogos] = [0, cardsLogos.slice(0, cards.length / 2)];
     newCardLogos = [...newCardLogos, ...newCardLogos];
     shuffleArray(newCardLogos)

     for (let card of cards) {
          card.querySelector('.card-front').src = `images/${newCardLogos[indice]}.png`;
          card.dataset.logo = newCardLogos[indice++];
          card.addEventListener('click', funcClickCard, {once: true});
     }
}

function stopGame() {
     btPlay.textContent = 'Iniciar Jogo'
     reset();
     hideCards(cards); //adicionado por mim
     modalGameOver.style.display = 'block';

     cards.forEach((item) => {
          item.classList.remove("flipped", "inative");
          item.querySelector(".card-front").classList.remove("grayscale");
     })

     removeEventListener("click", funcClickCard); //esta linha serve para quando o jogo acabar nao podermos carregar nas cartas
}

// ------------------------
// Event Listeners
// ------------------------

btPlay.addEventListener('click', function () {
     if (btPlay.textContent === 'Terminar Jogo')
          stopGame()
     else startGame();
});

panelGame.addEventListener('click', () =>
     message.textContent === '' ? message.textContent = 'Clique em Iniciar o Jogo!' : message.textContent = ''
);

reset();
