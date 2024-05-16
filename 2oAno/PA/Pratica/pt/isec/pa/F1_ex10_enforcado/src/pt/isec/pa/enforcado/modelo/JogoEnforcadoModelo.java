package pt.isec.pa.enforcado.modelo;

public class JogoEnforcadoModelo {

    private static final int MAX_ERROS = 7;

    private static final String ESCONDIDO = ".";

    private String palavra; //palavra a sortear

    private StringBuffer situacao; //situacao atual na descoberta

    private StringBuilder usadas; //letras usadas ate ao momento

    private int nrTentativas, nrAcertos;

    public JogoEnforcadoModelo(){
        iniciar();
    }

    public static int getMaxErros(){return MAX_ERROS;}
    public int getNrTentativas(){return nrTentativas;}
    public int getNrAcertos(){return nrAcertos;}
    public String getPalavra(){return palavra;}
    public int getNrErros(){return nrTentativas - nrAcertos;}

    public String getLetrasUsadas() {return usadas.toString();}
    public String getSituacao(){return situacao.toString();}
    public void iniciar(){

        int i = (int)(Math.random() * JogoEnforcadoDicionario.getNrPalavras());

        palavra = JogoEnforcadoDicionario.getPalavra(i).toUpperCase();

        situacao = new StringBuffer(ESCONDIDO.repeat(palavra.length()));

        usadas = new StringBuilder();

        nrTentativas = nrAcertos = 0;

    }

    public boolean acertou(){

        /*if(situacao.toString() == palavra){
            return true;
        }*/
        return palavra.equalsIgnoreCase(situacao.toString());
    }

    public boolean concluido(){
        //termina quando acertamos ou termina quando perdemos

        if(acertou())
            return true;

        if(nrTentativas - nrAcertos >= MAX_ERROS)
            return true;


        return false;
    }

    public boolean tentaOpcao(String opcao) {

        if(concluido() || opcao.isBlank())
            return false;

        if(opcao.length() > 1){
            return false;
        }

        if(getLetrasUsadas().contains(opcao)){
            return false;
        }

        nrTentativas++;
        //MINHA RESOLUCAO
        /*usadas.append(opcao);
        nrTentativas++;

        if(palavra.contains(opcao)){
            nrAcertos++;

            for(int i = 0; i < palavra.length(); i++){
                if(opcao.charAt(0) == palavra.charAt(i)){
                    situacao.setCharAt(i, opcao.charAt(0));
                }
            }
        }*/

        //RESOLUCAO STOR
        char op = Character.toUpperCase(opcao.charAt(0));
        usadas.append(op);

        for(int i = 0; i < palavra.length(); i++){
            char c = palavra.charAt(i);
            if(c == op){
                situacao.setCharAt(i, c);
                nrAcertos++;
            }
        }

        return true;
    }
}
