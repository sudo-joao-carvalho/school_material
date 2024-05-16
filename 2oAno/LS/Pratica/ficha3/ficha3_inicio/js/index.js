'use strict';

//ex1.
//a)
const panelControl = document.getElementById("panel-control");
const panelGame = document.getElementById("game");
const btLevel = document.getElementById("btLevel");
const msg = document.getElementById("message");
const btPlay = document.getElementById("btPlay");

const allListItems = document.querySelectorAll('.list-item');

const cards = panelGame.querySelectorAll(".card");

//FICHA3
let cardLogos = ["angular" , "bootstrap", "html", "javascript", "vue", "svelte", "react", "css", "backbone", "ember"];
let newCardLogos = Array(6);

//b)
function reset(){

    /*
    const msg = document.getElementById("message");
    const btPlay = document.getElementById("btPlay");

    //1)
    //panelControl.style.display = 'none';
    //3)
    msg.textContent = '';
    //4)
    msg.classList.remove('hide');
    //5)
    btPlay.disabled = true;
    */

    //ex2.
    btPlay.disabled = true;
    
    if(btLevel.value !== '0'){
        btPlay.disabled = false;
        panelGame.style.display = 'grid';
    }

    allListItems.forEach((item) => {
        item.classList.remove("gameStarted");
    })
}

// ex2.
btLevel.addEventListener("change", reset);
//2)
reset();

//3)

function startGame(){
    btLevel.disabled = true;
    btPlay.textContent = "Terminar Jogo";
    msg.classList.add("hide");

    allListItems.forEach((item) => {
        item.classList.add("gameStarted");
    })

    //FICHA 3 1)
        //console.table(cardLogos);    

        shuffleArray(cardLogos);

        //console.table(cardLogos);
        /*cards.forEach((item) => {
            console.log(item);
        })*/

        //EX1 g)
            //FOR...OF VERSION
                let counter = 0;
                newCardLogos = fillNewArray(newCardLogos, cardLogos);
                shuffleArray(newCardLogos);
                /*for(let i of cards){

                    console.table(newCardLogos[cardLogos.indexOf(i)]);

                    const img = i.querySelector(".card-front");
                    i.dataset.logo = newCardLogos[cardLogos.indexOf(i)];
                    
                    img.src = "images/" + newCardLogos[cardLogos.indexOf(i)] + ".png";

                    i.addEventListener("click", function (){
                        flipCard(this);
                    })
                }*/

            //FOREACH VERSION
                //ex2
                cards.forEach((i) => {
                    
                    const img = i.querySelector(".card-front");
                    i.dataset.logo = newCardLogos[counter];
                    img.src = "images/" + newCardLogos[counter++] + ".png";

                    //V1
                        /*i.addEventListener("click", function (){
                            flipCard(this);
                        })*/
                    //V2
                        i.addEventListener("click", flipCard);

                    //sem delegacao de eventos --> aplica se o eventListener a cada filho
                    i.addEventListener("mouseover", () =>{
                        i.classList.add("cardHover");
                    })
    
                    i.addEventListener("mouseout", () =>{
                        i.classList.remove("cardHover");
                    })

                })


                //delegacao de eventos --> aplica se o eventListener ao pai e este transmite ao filho
                /*panelGame.addEventListener("click", (e) => {
                    const elemento = e.target;
                    if(elemento.src !== undefined){
                        flipCard(elemento);
                    }
                })*/

                
                
        //EX1 g)

        //showCards(cards);
        
    //FICHA 3 1)

}

function stopGame(){
    btPlay.textContent = "Iniciar Jogo";
    btLevel.disabled = false;
    reset();
    hideCards(cards);
}

btPlay.addEventListener("click", () =>{
    if(btPlay.textContent === "Terminar Jogo"){
        stopGame();
    }else{
        startGame();
    }
});

panelGame.addEventListener("click", () => {

    msg.textContent === '' ? msg.textContent = "Clique em Iniciar Jogo!" : msg.textContent = '';

})

const shuffleArray = array => {
    for(let i = array.length - 1; i > 0; i--){
        const j = Math.floor(Math.random() * (i + 1));
        const temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}

function showCards(cardLogos){

    for(let i of cardLogos){
        i.classList.add("flipped");
    }
}

function hideCards(cards){

    for(let i of cards){
        i.classList.remove("flipped");
    }
}

//F3 ex2
function fillNewArray(newCardLogos, cardLogos){
    const firstHalf = cardLogos.slice(0, 3);
    newCardLogos = [...firstHalf, ...firstHalf];
    return newCardLogos;
}

//F3 ex3
//V1
    /*function flipCard(selectedCard){
        selectedCard.classList.add("flipped");
    }*/
//V2
    function flipCard(){
        this.classList.add("flipped");
    }


