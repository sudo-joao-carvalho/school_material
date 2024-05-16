import java.util.Arrays;
import java.util.Random;

public class Main {

    public static boolean binRecursivo(int []tab, int chave){

        //MELHORAR
        /*int []newt;

        if(tab.length == 0) return false;

        if(tab[(tab.length - 1)/2] == chave){
            return true;
        }

        if(tab[(tab.length - 1)/2] > chave){
            newt = Arrays.copyOfRange(tab, 0, (tab.length - 1)/2 - 1);
        }else{
            newt = Arrays.copyOfRange(tab, (tab.length - 1)/2 - 1, tab.length - 1);
        }*/

        int start = 0, end = tab.length, meio = (start + end) / 2;

        if(end == 0){
            System.out.println("Tabela vazia");
            return false;
        }

        if(tab[meio] == chave) return true;
        if(tab[meio] > chave) end = meio - 1; else start = meio + 1;

        if(end < tab.length) end++; // copyOfRange vai ate end - 1
        int[] newt = Arrays.copyOfRange(tab, start, end);

        return binRecursivo(newt, chave);
    }

    static int[] criaArrayCom( int valor, int dimensao, boolean diferentes){

        int m[]=new int[dimensao];
        if(diferentes){
            for(int i=0;i<dimensao;i++)
                m[i]=i*10;
            if((valor%10!=0)||(0>valor)||(valor>(dimensao-1)*10))
                m[0]=valor;
        } else{
            Random r=new Random();
            int gama=(Math.abs(valor)<10)?10:Math.abs(valor); for(int i=0;i<dimensao;i++)
                m[i]=r.nextInt(gama*4)-gama*2;
            m[0]=valor;
        }
        Arrays.sort(m);
        return m;
    }
    public static void main(String[] args) {

        //int[] arr = criaArrayCom(50, 10, true); //{1, 3, 4, 6, 8, 9}
        int[] arr = {1, 3, 4, 6, 8};
        boolean res = binRecursivo(arr, 5);

        //System.out.println(Arrays.toString(arr));

        if(res == true) System.out.println("Existe");
        else System.out.println("Nao existe");
    }
}