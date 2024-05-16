import java.util.Arrays;
import java.util.Random;

public class Main {

    public static boolean binIterativo(int []tab, int chave){

        int linf = 0, lsup = tab.length - 1, meio;

        while(lsup >= linf){
            meio = (linf + lsup) / 2;

            if(tab[meio] == chave) return true;
            if(tab[meio] < chave) linf = meio + 1; else lsup = meio - 1;
        }

        return false;

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

        int[] arr = {1, 3, 4, 6, 9, 99};

        for(int j = 0; j < arr.length; j++){
            if(binIterativo(arr, arr[j])) System.out.println(arr[j] + "Existe");
            else System.out.println(arr[j] + "Nao existe");

            if(binIterativo(arr, 100)) System.out.println(arr[j] + "Existe");
            else System.out.println(100 + "Nao existe");
        }
    }
}