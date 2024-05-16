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
    public static void main(String[] arrgs) {

        int[] arr = {3, 7, 12, 15};

        for(int i = 0; i < arr.length; i++) {
            if (binIterativo(arr, arr[i])!=-1) System.out.println(arr[i] + " Existe");
            else System.out.println(arr[i] + " Nao existe");
        }
    }
}