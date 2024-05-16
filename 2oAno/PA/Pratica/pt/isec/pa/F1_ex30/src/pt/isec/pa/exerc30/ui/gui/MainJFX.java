package pt.isec.pa.exerc30.ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.pa.exerc30.model.Drawing;
import pt.isec.pa.exerc30.model.Figure;

public class MainJFX extends Application {
    Drawing drawing; //drawing data

    @Override
    public void init() throws Exception { //normalmente n se usa o init mas aqui usa se para povoar a app
        super.init();
        drawing = new Drawing(); //criamos o modelo

        //TEST
        drawing.setCurrentType(Figure.FigureType.LINE);
        drawing.setRGB(1,0,0);
        drawing.createFigure(0,0);
        drawing.finishCurrentFigure(100,100);
        drawing.setCurrentType(Figure.FigureType.OVAL);
        drawing.setRGB(0,1,0);
        drawing.createFigure(100,0);
        drawing.finishCurrentFigure(200,100);
        drawing.setCurrentType(Figure.FigureType.RECTANGLE);
        drawing.setRGB(0,0,1);
        drawing.createFigure(200,0);
        drawing.finishCurrentFigure(300,100);

    }

    @Override
    public void start(Stage stage) throws Exception {
        RootPane root = new RootPane(drawing);
        Scene scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.setTitle("Drawing@PA");
        stage.show();
    }
}
