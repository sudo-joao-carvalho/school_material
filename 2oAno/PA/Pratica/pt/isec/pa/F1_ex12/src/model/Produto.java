package model;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class Produto {

    private int nSerie;
    private Date dataFabrico;

    private String estado;

    public Produto(int nSerie){
        this.nSerie         = nSerie;
        this.dataFabrico    = new Date();
        this.estado         = "não testado";
    }

    //GETS
    public int getnSerie(){return this.nSerie;}
    public Date getDataFabrico(){return this.dataFabrico;}
    public String getEstado(){return this.estado;}

    //SETS
    public void setnSerie(int nSerie) {
        this.nSerie = nSerie;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public void setDataFabrico(Date dataFabrico){
        this.dataFabrico = dataFabrico;
    }

    public boolean testaUnidade(){

        Random rand = new Random();
        double probAprovado = 0.9;

        if(estado.equalsIgnoreCase("não testado")){
            if(rand.nextDouble() < probAprovado){
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder("Produto\r\n");
        sb.append(String.format("Numero de Serie: %d\r\n", nSerie));
        sb.append(String.format("Data de Fabrico: %tc\r\n", dataFabrico));
        sb.append(String.format("Estado: %s\r\n", estado));

        return sb.toString();

    }


    //FUNCAO EQUALS()
    //Explicaçao da funcao
    /*Vamos ver passo a passo o que essa função faz:

O primeiro if verifica se o objeto atual é o mesmo objeto que está sendo comparado. Se for, isso significa que os dois objetos são iguais, então o método retorna true.
Se o primeiro if falhar, o segundo if é executado. Ele verifica se o objeto sendo comparado é uma instância de Produto. Se não for, significa que os objetos não são iguais e o método retorna false.
Se os dois primeiros passos não retornarem true ou false, então o objeto sendo comparado é uma instância de Produto. Nesse caso, o objeto o é convertido em um objeto Produto usando a sintaxe de casting (Produto) o.
A função então compara os valores do atributo numeroDeSerie do objeto atual (this) e do objeto produto que foi passado como parâmetro. Se os valores forem iguais, a função retorna true, indicando que os objetos são iguais. Caso contrário, a função retorna false, indicando que os objetos não são iguais.*/
    @Override
    public boolean equals(Object p){ //Objeto generico para podermos comparar se é o mm objeto, se é ou nao uma instancia dos objetos Produto
        if(this == p) return true;
        if(!(p instanceof Produto)) return false; //O operador instanceof é usado para verificar se um objeto é uma instância de uma determinada classe. No caso do método equals(), essa verificação é usada para garantir que o objeto o recebido como parâmetro seja realmente uma instância da classe Produto.
        Produto produto = (Produto) p;
        return nSerie == produto.nSerie;
    }
    //FUNCAO HASCODE()
    @Override
    public int hashCode() {
        return Objects.hash(nSerie);
    }
}
