import "./assets/styles/App.css";
import React, { useState } from "react";
import {
  ControlPanel,
  Footer,
  Header,
  GamePanel,
} from "./components";

function App() {

  const [gameStarted, setGameStarted] = useState(false);
  const [selectedLevel, setSelectedLevel] = useState("0");

  const handleGameStart = () =>{
    setGameStarted(!gameStarted);
  }

  const handleLevelChange = (e) =>{
    setSelectedLevel(e.currentTarget.value);
  }

  return (
    <div id="container">
      <Header />
      <main className="main-content">
        <ControlPanel gameStarted={gameStarted} onGameStart={handleGameStart} selectedLevel={selectedLevel} onLevelChange={handleLevelChange}/>
        <GamePanel />
      </main>
      <Footer />
    </div>
  );
}
export default App;
