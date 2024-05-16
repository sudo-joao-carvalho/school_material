package ui;

import model.Fabrica;
import utils.PAInput;

public class FabricaUI {
    //Fabrica fabrica;
    Fabrica fabrica = new Fabrica("Mata Borrao");

    public void menu() {
        int option;
        do {
            option = PAInput.chooseOption("Fabrica Mata Borrao", "Acrescentar Produto","Pesquisar Produto","Eliminar Produto","Eliminar Reprovados", "Testa Unidades", "Lista Produtos", "Sair");
            switch (option) {
                case 1:
                    addProduto();
                    break;
                case 2:
                    pesquisarProd();
                    break;
                case 3:
                    removeProduto();
                    break;

                case 4:
                    fabrica.eliminaReprovados();
                    System.out.printf("Produtos Reprovados eliminados com sucesso. \r\n");
                    break;

                case 5:
                    fabrica.testaUnidades();
                    break;

                case 6:
                    System.out.println(fabrica.toString());
                    break;
            }
        } while (option != 7);

    }

    private void addProduto() {
        int numS = PAInput.readInt("Numero de serie: ");
        if(fabrica.acrescentaProduto(numS)){
            System.out.printf("Produto %d Adicionado com Sucesso\r\n", numS);
        }else System.out.printf("Operacao Não Concluida\r\n");
    }

    private void pesquisarProd(){
        int numS = PAInput.readInt("Numero de serie: ");
        System.out.println(fabrica.pesquisaProduto(numS).toString());
    }

    private void removeProduto() {
        int numS = PAInput.readInt("Numero de serie: ");
        if(fabrica.eliminaProduto(numS)){
            System.out.printf("Produto %d Removido com Sucesso\r\n", numS);
        }else System.out.printf("Operacao Não Concluida\r\n");
    }
}
