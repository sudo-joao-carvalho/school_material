import ex4.tabela.Tabela;

public class Main {
    public static void main(String[] args) {

        Tabela tab= new Tabela();

        tab.preenche();
        tab.preencheCrescente();

        tab.lista();

        tab.printaInfo();
    }
}