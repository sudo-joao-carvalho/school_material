package jc.javafx.basic_example.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javafx.scene.control.Button;

public class JavaFxMain extends Application {

    //aqui é o data(dados do jogo)
    private String text;
    private Button b1, b2;
    private Label l;

    @Override
    public void start(Stage stage) throws Exception{

        text = "Hi!";

        //Cria os componentes
        b1 = new Button("Start");
        b2 = new Button("Start");
        l = new Label();

        l.setAlignment(Pos.CENTER);
        l.setMinWidth(150);
        l.setTextFill(Color.STEELBLUE);
        l.setFont(Font.font("courier new", FontWeight.BOLD, 40));

        //Organiza/ dispoe vistas    Isto é o root pane da scene
        HBox hb = new HBox();
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(b1, b2, l);

        //Regista os handlers (resposta a eventos)
        b1.setOnAction((ActionEvent ev) -> { //nao pode ter codigo nos botoes(ou seja na interface)
            text = "START"; //Aqui vamos ter context.qualquercoisa()
            update();
        });

        b2.setOnAction((ev) -> { //nao pode ter codigo nos botoes(ou seja na interface)
            text = "STOP";
            update();
        });

        //Cria a cena com o componente de base (root)
        Scene scene = new Scene(hb, 400, 300);

        stage.setTitle("My Basic JavaFX Application");
        stage.setScene(scene);
        stage.show();

        update();
    }

    public void update(){
        l.setText(text);
    }


}

//3 Steps
//  -> criar os butoes e a label
//  -> Organizar/ dispor as vistas
//  -> tratar dos handlers