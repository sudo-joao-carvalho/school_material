public class Main {
    public static void main(String[] args) {

        int[] ar = {1, 2, 3, 4, 5}; // criaArrayCom(50,10,true);

        for (int i = 0; i < ar.length; i++) {
            if (binIterativa(ar, ar[i])!=-1) System.out.println(ar[i] + " Existe");
            else System.out.println(ar[i] + " nao existe");

        }
    }

    public static int binIterativa(int[] tab, int chave) {

        int linf = 0, lsup = tab.length - 1, meio;

        while (lsup >= linf) {
            meio = (linf + lsup) / 2;
            if (tab[meio] == chave) return meio;
            if (tab[meio] < chave) linf = meio + 1;
            else lsup = meio - 1;


        }
        return -1;

    }
}