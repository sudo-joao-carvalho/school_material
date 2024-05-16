//Uma aplicaçao com JavaFX a main tem que ter extend Application obrigatoriamente
import javafx.application.Application;
import jc.javafx.basic_example.ui.JavaFxMain;
public class Main{
    public static void main(String[] args) {
        Application.launch(JavaFxMain.class, args);
    }

    /*@Override
    public void start(Stage stage) throws Exception{ //o stage é a janela principal
        //é aqui que e feito o ambiente grafico
        //é aqui que se definem os handlers
    }*/
}