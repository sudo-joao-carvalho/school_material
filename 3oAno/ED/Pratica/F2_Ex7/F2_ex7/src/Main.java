import java.util.Arrays;

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

    public static boolean testaRepetidos(int tab[], int chave) {
        int j;

        j = binIterativo(tab, chave);

        if (j < 0)
            return false;

        if (j == 0) {
            if (tab[1] != chave)
                return false;
            else
                return true;
        }

        if (j == tab.length-1) {
            if (tab[tab.length-2] != chave)
                return false;
            else
                return true;
        }

        if ((tab[j] == tab[j-1]) || tab[j] == tab[j+1])
            return true;

        return false;
    }

    public static void main(String[] args) {
        int[] ar = {3, 3, 7, 12, 12, 15};

        System.out.println();
        System.out.println(Arrays.toString(ar));
        System.out.println();

        System.out.println("Valor = 3   resultado =  " + testaRepetidos(ar, 3));
        System.out.println("Valor = 7   resultado =  " + testaRepetidos(ar, 7));
        System.out.println("Valor = 12   resultado =  " + testaRepetidos(ar, 12));
    }
}