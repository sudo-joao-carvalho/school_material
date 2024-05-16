public class Main {

    public static int binCmp(int[] tab, int chave) {

        int linf = 0, lsup = tab.length - 1, meio = 0;

        while (lsup >= linf) {
            meio = (linf + lsup) / 2;
            if (tab[meio] == chave) return meio;
            if (tab[meio] < chave) linf = meio + 1;
            else lsup = meio - 1;
        }

        if (tab[meio] < chave) return -meio - 2;

        return -meio - 1;
    }

    public static double percentagem(int m[], int valor){

        int pos = binCmp(m, valor);

        //se o valor existe
        if(pos >= 0) return (double)pos/m.length;

        //se o valor nao existe
        int posInsert = -pos-1;

        //o valor na pos de inserçao é menor e deve contar
        return posInsert/(double)m.length;
    }

    public static void main(String[] args) {
        int[] arr = {3,7,12,15};

        System.out.println("4: " + percentagem(arr, 4) * 100 + "%");
        System.out.println("8: " + percentagem(arr, 8) * 100 + "%");
        System.out.println("13: " + percentagem(arr, 13) * 100 + "%");
        System.out.println("16: " + percentagem(arr, 16) * 100 + "%");
    }
}