import React from "react";

import "./game-panel.css";
import { Card } from "../index";

function GamePanel(props) {
  const {cards, selectedLevel} = props;

  let gameClasse;

  if(selectedLevel === "2")
    gameClasse = "intermedio";
  else if(selectedLevel === "3")
    gameClasse = "avançado";
  else gameClasse = "";

  return (
    <section className="game-panel">
      <h3 className="sr-only">Peças do Jogo</h3>
      <div className={gameClasse} id="game">
        {cards.map((card) => (
          <Card key={card.key} name={card.name} />
        ))}
      </div>
    </section>
  );
}
export default GamePanel;
