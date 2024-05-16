import model.fsm.ElevatorContext;
import ui.ElevatorUI;

public class Main {
    public static void main(String[] args) {
        ElevatorContext elevatorContext = new ElevatorContext();
        ElevatorUI elevatorUI = new ElevatorUI(elevatorContext);

        elevatorUI.start();
    }
}

//EM MAQUINAS DE ESTADO A SEQUENCIA
//  1 -> adicionar o novo estado na enumeracao
//  2 -> depois ir à interface do State adicionar o metodo
//  3 -> ir a classe abstrata adicionar o metodo por omissao
//  4 -> ir ao context e implementar a funcao (ou seja state.funcao()) ---> aplicar a funcao no estado
//  5 -> adicionar funçoes no data (neste caso elevador -> getters e setters)
//  6 -> criar o estado
//  7 -> implementar a mudança de estado (changeState no estado)