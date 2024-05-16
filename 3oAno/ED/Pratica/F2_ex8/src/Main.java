public class Main {

    public static int binIterativo(int[] tab, int chave) {

        int linf = 0, lsup = tab.length - 1, meio = 0;

        while (lsup >= linf) {
            meio = (linf + lsup) / 2;
            if (tab[meio] == chave) return meio;
            if (tab[meio] < chave) linf = meio + 1;
            else lsup = meio - 1;
        }

        if(tab[meio] < chave) return -meio-2;

        return -meio-1;

    }

    public static double devolveMaiorMenorQueZ(int[] tab, int z){

        int pos = binIterativo(tab, z); //Da pergunta 4

        if(pos == -1 || pos == 0) return z;

        if(pos < 0) pos = -pos-1;

        return tab[pos-1];
    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}