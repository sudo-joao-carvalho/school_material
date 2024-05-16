package pt.isec.pa.enforcado.ui;

import pt.isec.pa.enforcado.modelo.JogoEnforcadoModelo;

import java.util.Scanner;

public class JogoEnforcadoUI {

    private JogoEnforcadoModelo jogo;

    private Scanner sc;

    public JogoEnforcadoUI(JogoEnforcadoModelo jogo){
        this.jogo = jogo;
    }

    public void jogar(){ //na parte da interface nao ha nada de logica

        sc = new Scanner(System.in);

        while(!jogo.concluido()){

            System.out.println("\nSituacao atual: " + jogo.getSituacao());

            System.out.println("Nr. tentativas: " + jogo.getNrTentativas());
            System.out.printf("Erros: %d (máx.: %d)\n", jogo.getNrErros(), JogoEnforcadoModelo.getMaxErros());
            System.out.println("Caracteres ja tentados: " + jogo.getLetrasUsadas());

            System.out.printf("\nTentativa: ");

            String opcao = sc.nextLine().trim();

            if(opcao.length() > 0){
                jogo.tentaOpcao(opcao);
            }
        }

        if(jogo.acertou()){
            System.out.printf("parabens! Acertou na palavra %s em %d tentativas\n", jogo.getPalavra(), jogo.getNrTentativas());
        }else
            System.out.println("Perdeste!!! A palavra era: " + jogo.getPalavra());
    }
}
