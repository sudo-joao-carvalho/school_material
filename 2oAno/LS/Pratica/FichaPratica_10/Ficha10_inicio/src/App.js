import React from 'react';
import { useState } from "react";
import { useEffect } from 'react';
import "./assets/styles/App.css";
import shuffleArray from './helpers/shuffle';
import { CARDS_LOGOS } from './constants';
import { TIMEOUTGAME } from './constants';

/*
import Header from "./components/header/header.component"
import Footer from "./components/footer/footer.component"
import GamePanel from "./components/game-panel/game-panel.component";
import ControlPanel from "./components/control-panel/control-panel.component";
*/

import {
  ControlPanel,
  Footer,
  Header,
  GamePanel,
} from "./components";

let timerId = undefined;

function App() {
  const [gameStarted, setGameStarted] = useState(false);
  const [selectedLevel, setSelectedLevel] = useState("0");
  const [Cards, setCards] = useState([]);
  const [timer, setTimer] = useState(TIMEOUTGAME);
  /**
  * When the game starts
  */
  const handleGameStart = () => {
    if (gameStarted) {
      console.log("Termina Jogo");
      setGameStarted(false);
    } else {
      console.log("Inicia Jogo");
      setGameStarted(true);
    }
  };
  /**
   * When the user selects a new level,
   * this callback function is executed
   */
  const handleLevelChange = (event) => {
    const { value } = event.currentTarget;
    let numOfCards = 0;
    const initialCards = shuffleArray(CARDS_LOGOS);

    switch(value){
      case "1" :  numOfCards = 3;
                break;
      case "2" :  numOfCards = 6;
                break;
      case "3" :  numOfCards = 10;
                break;
      default:  numOfCards = 0;
                break;
    }

    const slicedInitialCards = initialCards.slice(0, numOfCards);


    const doubledCardsObjects = [];
    slicedInitialCards.forEach((card, index) => {
      doubledCardsObjects.push({
         key: `${card}-${index}`, 
         id: card, 
         name: card 
      });
      doubledCardsObjects.push({
        key: `${card}-${index}-clone`,
        id: `${card}-clone`,
        name: card
      });
    });
    
    shuffleArray(doubledCardsObjects);
    setCards(doubledCardsObjects);

    setSelectedLevel(value);
  }

  useEffect(() => {
    if (gameStarted) {
      let nextTimer;
      timerId = setInterval(() => {
        setTimer((previousState) => {
          nextTimer = previousState - 1;
          return nextTimer;
        });
        if (nextTimer === 0) {
          setGameStarted(false);
        }
      }, 1000);
    } else if (timer !== TIMEOUTGAME) {
      setTimer(TIMEOUTGAME);
    }
    return () => {
      if (timerId) {
        clearInterval(timerId);
      }
    };
  }, [gameStarted]);



  return (
    <div id="container">
      <Header />
      <main className="main-content">
        <ControlPanel
          gameStarted={gameStarted}
          onGameStart={handleGameStart}
          selectedLevel={selectedLevel}
          onLevelChange={handleLevelChange}
          timer={timer}
        />
        <GamePanel 
         cards={Cards}
         selectedLevel={selectedLevel}
        />
      </main>
      <Footer />
    </div>
  );
}

export default App;
// Esta linha também poderia ser eliminada
// e adefinição da funsão ser substituida 
// export default function App() {
