package pt.isec.pa.exemplo1;

public class Exemplo1 {
    public static void main (String[] args) {
        System.out.println("Hello world!" ) ;
        if(args.length > 0){
            System.out.println("Ola " + args[0] + "!");
        }
    }


}
//ao complicar um ficheiro .java cria ficheiros .classe (java bytecode) que vai ser lidos pela jvm
// se tiver 3 classes, A, B e C vao ser criados 3 ficheiros .classe para cada ficheiro .java
//cada vez que e declarado um objeto num pedaço de codigo a jvm vai procurar no diretorio um ficheiro .classe daquela classe
//em java o "nome do programa", verdadeiramente o nome da classe onde tem a main que queremos que o programa comece, nao conta como argumento da linha de comandos

//STATIC --> se uma classe tiver um atributo static todos os objetos criados dessa classe conseguem aceder a esse atributo'85 por exemplo se cada vez que criar um objeto e incrementar 1 a esse atributo, esse atributo vai ser incrementado em todos os objetos dessa class
//para criar um ficheiro jar(zip) --> para facilitar deployment de programas java
//   jar cf exemplo1.jar pt/* (meter no jar tudo o que esta na diretoria pt)
//executar o jar
//   java -cp exemplo1.jar pt.isec.pa.exemplo1.Exemplo1
//para especifir o ponto de entrada(onde esta o main):
//jar cfe exemplo1.jar pt.isec.pa.exemplo1.Exemplo1 pt/*
//depois para executar ja n e preciso indicar a class onde esta o main, é so fazer:
//java .jar <ficheiro.jar>
//pode se meter um .bat dentro do .jar para a pessoa quando quiser executar o programa apenas clicar 2 vezes no ficheiro .bat