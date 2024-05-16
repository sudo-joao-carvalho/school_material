package ex4.tabela;

public class Tabela {

    public static final int TAM = 20;

    private int[] tabela;

    private int nGerados = 0;
    private int nInseridos = 0;

    public Tabela(){
        tabela = new int[TAM];
    }

    public int getNumeroPosicao(int pos){return tabela[pos];}

    public boolean verifica(int nr){
        for(int i = 0; i < TAM; i++){
            if(nr == tabela[i]) {
                return true;
            }
        }

        return false;
    }

    /*public boolean adiciona(int nr){

        if(nr < 0 || nr > 100)
            return false;


    }*/
    public void preenche(){

        for(int i = 0; i < TAM; i++){

            int numero = (int)(Math.random() * 100);

            if(!verifica(numero)){
                tabela[i] = numero;
                nGerados++;
                nInseridos++;
            }else{
                while(verifica(numero)){
                    numero = (int)(Math.random() * 100);
                    nGerados++;
                }
                tabela[i] = numero;
                nInseridos++;

            }
        }
    }


    public void lista(){

        if(nInseridos == 0){
            System.out.printf("Nenhum numero inserido...\n");
        }else{
            System.out.printf("[");
            for(int i = 0; i < TAM; i++){
                System.out.println(" " + tabela[i] + " ");
            }
            System.out.printf("]");
        }
    }

    public void printaInfo(){
        System.out.printf("\nForam gerados %d valores duplicados", nGerados-nInseridos);
    }

    public void preencheCrescente(){

        for(int i = 0; i < TAM; i++){
            for(int j = i + 1; j < TAM; j++){
                if(tabela[i] > tabela[j]){
                    int aux;
                    aux = tabela[i];
                    tabela[i] = tabela[j];
                    tabela[j] = aux;
                }
            }
        }


    }
}
