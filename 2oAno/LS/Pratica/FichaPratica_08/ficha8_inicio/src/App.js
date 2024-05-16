/*import Header from "./components/header/header.component";
import Footer from "./components/footer/footer.component";
import ControlPanel from "./components/control-panel/control-panel.component";
import GamePanel from "./components/game-panel/game-panel.component";*/
import {
  ControlPanel,
  Footer,
  Header,
  GamePanel,
  Card,
} from "./components";
import "./assets/styles/App.css";

function App() {
  return (
    <div id="container">
      <Header></Header>
      <ControlPanel></ControlPanel>
      <GamePanel></GamePanel>
      <Footer></Footer>
    </div>
  );
}

export default App;
// Esta linha também poderia ser eliminada
// e adefinição da funsão ser substituida 
// export default function App() {
