import React from "react";
import GamePanel from "../game-panel/game-panel.component";
import "./control-panel.css";

function ControlPanel(){

    return (
        <main className="main-content"> 
            <section id="panel-control">
                <h3 className="sr-only">Escolha do Nível</h3>
                <form className="form">
                    <fieldset className="form-group">
                    <label htmlFor="btLevel">Nível:</label>
                    <select id="btLevel">
                        <option defaultValue="0">Seleccione...</option>
                        <option value="1">Básico (2x3)</option>
                        <option value="2">Intermédio (3x4)</option>
                        <option value="3">Avançado (4x5)</option>
                    </select>
                    </fieldset>
                    <button type="button" id="btPlay">Iniciar Jogo</button>
                </form>
                <div className="form-metadata">
                    <p id="message" role="alert" className="left hide">
                        Clique em Iniciar o Jogo!
                    </p>
                    <dl className="list-item left">
                        <dt>Tempo de Jogo:</dt>
                        <dd id="gameTime">0</dd>
                    </dl>
                    <dl className="list-item right">
                        <dt>Pontuação TOP:</dt>
                        <dd id="pointsTop">0</dd>
                    </dl>
                    <dl className="list-item left">
                        <dt>Pontuação:</dt>
                        <dd id="points">0</dd>
                    </dl>
                    <div id="top10" className="right">
                        <button id="btTop">Ver TOP 10</button>
                    </div>
                </div>
            </section>
        </main>
    );
}

export default ControlPanel;