package ex4.tabela;

public class Tabela {
    public static final int MAX = 100;

    public static final int TAM = 20;

    private int[] tab;

    private int nInseridos = 0;

    private int nGerados = 0;

    public Tabela(){
        tab = new int[TAM];
    }

    public boolean existe(int n){
        for (int i = 0; i < nInseridos; i++){
            if (tab[i] == n){
                return true;
            }
        }

        return false;
    }

    public int getnGerados(){
        return nGerados;
    }

    public int getnInseridos(){
        return nInseridos;
    }

    public boolean adiciona(int n){

        if(n < 0 || n > MAX)
            return false;

        if(existe(n))
            return false;

        tab[nInseridos++] = n;
        return true;
    }

    public void geraNums(){

        while(nInseridos < TAM){
            int nr = (int)(Math.random() * (MAX + 1));
            nGerados++;
            adiciona(nr);
        }
    }

    public void mostraDuplicados(){
        if(nInseridos == 0){
            System.out.println("Ainda nao foram gerados valores...\n");
        }else {
            System.out.printf("\nPara alem dos %d valores inseridos, foram gerados %d valores duplicados.", nInseridos, nGerados - nInseridos);
        }
    }

    public void listar() {
        System.out.print("[");

        for (int i = 0; i < nInseridos; i++){
            if (i > 0){
                System.out.println(", ");
            }

            System.out.println(tab[i]);
        }

        System.out.println("]");
    }
}

