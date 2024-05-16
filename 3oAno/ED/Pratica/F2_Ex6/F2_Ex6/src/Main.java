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

    public static double contaIntervalo(int []tab, int min, int max){

        int j, k, count = 0;

        //for(j = 0; j < tab.length && tab[j] <= max; j++){  //SOLUCAO LINEAR
        //    if(tab[j] >= min && tab[j] <=max) count++;
        //}
        //return count;

        j = binIterativo(tab, min); //Solucao logaritmica
        k = binIterativo(tab, max); //O(2.log(n)) continua a ser logaritmica
        if(j < 0) j = -j-1;
        if(k < 0) k = -k-1; else k++;

        return k-j;

    }

    public static void main(String[] args) {

        int[] arr = {3,7,12,15};

        System.out.println(contaIntervalo(arr, 0, 15));
        System.out.println(contaIntervalo(arr, 3, 7));
        System.out.println(contaIntervalo(arr, 14, 14));
    }
}