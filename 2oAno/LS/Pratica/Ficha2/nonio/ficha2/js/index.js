'use strict';

//ex1.
//a)
const panelControl = document.getElementById("panel-control");
const panelGame = document.getElementById("game");
const btLevel = document.getElementById("btLevel");
const msg = document.getElementById("message");
const btPlay = document.getElementById("btPlay");

const allListItems = document.querySelectorAll('.list-item');

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

}

function stopGame(){
    btPlay.textContent = "Iniciar Jogo";
    btLevel.disabled = false;
    reset();
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
