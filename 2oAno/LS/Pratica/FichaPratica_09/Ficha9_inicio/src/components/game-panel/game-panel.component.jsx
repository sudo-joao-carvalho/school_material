import React from "react";
import { Card } from "../index";
import { CARDS_LOGOS } from "../../constants";

function GamePanel(){

    const cards = [];

    for(let i = 0; i < 6; i++){
        cards.push(<Card name={CARDS_LOGOS[i]} key={CARDS_LOGOS[i]} />);
    }

    return(
        <section className="game-panel">
            <h3 className="sr-only">Peças do Jogo</h3>
            <div id="game">
                {cards}
                
                {/*<Card name="angular" />
                <Card name="html" />
                <Card name="javascript" />
                <Card name="vue" />
                <Card name="svelte" />
                <Card name="react" />*/}
            </div>
        </section>
    );
}

export default GamePanel;