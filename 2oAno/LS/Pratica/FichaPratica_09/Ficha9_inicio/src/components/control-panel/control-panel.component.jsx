import React from "react";
import "./control-panel.css";

function ControlPanel(props) {

  const { gameStarted, selectedLevel, onGameStart, onLevelChange } = props;

  console.log('selectedLevel:', props.selectedLevel);
  
  
  const changeText = () =>{
    return gameStarted ? "Parar Jogo" : "Iniciar Jogo";
  }

  return (
    <section id="panel-control">
      <h3 className="sr-only">Escolha do Nível</h3>
      <form className="form">
        <fieldset className="form-group">
          <label htmlFor="btLevel">Nível:</label>
          <select id="btLevel" disabled={gameStarted} onChange={onLevelChange}>
            <option defaultValue value="0">
              Seleccione...
            </option>
            <option value="1">Básico (2x3)</option>
            <option value="2">Intermédio (3x4)</option>
            <option value="3">Avançado (4x5)</option>
          </select>
        </fieldset>
        <button type="button" id="btPlay" disabled={selectedLevel === "0"} onClick={onGameStart}>
          {changeText()}
        </button>
      </form>
      <div className="form-metadata">
        <p id="message" role="alert" className="hide">
          Clique em Iniciar o Jogo!
        </p>
        <dl className={gameStarted ? 'list-item left gameStarted' : 'list-item left'}>
          <dt>Tempo de Jogo:</dt>
          <dd id="gameTime">0</dd>
        </dl>
        <dl className={gameStarted ? 'list-item left gameStarted' : 'list-item left'}>
          <dt>Pontuação TOP:</dt>
          <dd id="pointsTop">0</dd>
        </dl>
        <dl className={gameStarted ? 'list-item left gameStarted' : 'list-item left'}>
          <dt>Pontuação:</dt>
          <dd id="points">0</dd>
        </dl>
        <div id="top10" className="right">
          <button id="btTop">Ver TOP 10</button>
        </div>
      </div>
    </section>
  );
}

export default ControlPanel;
