package pt.isec.pa.enforcado;

import pt.isec.pa.enforcado.modelo.JogoEnforcadoModelo;
import pt.isec.pa.enforcado.ui.JogoEnforcadoUI;

public class JogoEnforcado {

    public static void main(String[] args){
        JogoEnforcadoModelo jogo = new JogoEnforcadoModelo();
        JogoEnforcadoUI jogoUI = new JogoEnforcadoUI(jogo);

        jogoUI.jogar();

    }
}
