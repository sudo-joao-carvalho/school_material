package pt.isec.pa.exerc30.ui.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import pt.isec.pa.exerc30.model.Drawing;
import pt.isec.pa.exerc30.model.Figure;

public class RootPane extends BorderPane {
    Drawing drawing;

    DrawingArea drawingArea;
    //Canvas area;
    //ToolBar toolBar;
    //Pane areaPane;
    Pane drawingAreaPane;

    public RootPane(Drawing drawing) {
        this.drawing = drawing;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        //this.setTop(new DrawingToolBar(drawing));

        drawingArea = new DrawingArea(drawing);
        drawingAreaPane = new Pane(drawingArea);
        this.setCenter(drawingAreaPane);

        /*toolBar = new ToolBar();
        ToggleButton tgb = new ToggleButton(null,new Line(10,10,30,30));
        toolBar.getItems().add(tgb);
        this.setTop(toolBar);

        area = new Canvas();
        areaPane = new Pane(area);
        this.setCenter(areaPane);*/
    }

    private void registerHandlers() {
        //TODO: event processing
        drawingAreaPane.widthProperty().addListener(observable -> {
            drawingArea.updateSize(getWidth(), getHeight());
            //drawingArea.setWidth(getWidth());
            //drawingArea.setHeight(getHeight());
            //update();
        });
        drawingAreaPane.heightProperty().addListener(observable -> {
            drawingArea.updateSize(getWidth(), getHeight());
            //drawingArea.setWidth(getWidth());
            //drawingArea.setHeight(getHeight());
            //update();
        });

    }

    private void update() {
        /*GraphicsContext gc = area.getGraphicsContext2D();

        // Canvas: clearScreen
        gc.setFill(Color.LIGHTGOLDENRODYELLOW);
        gc.fillRect(0,0,getWidth(),getHeight());

        for(Figure figure:drawing.getList())
            switch(figure.getType()) {
                case LINE -> gc.strokeLine(figure.getX1(),figure.getY1(),figure.getX2(),figure.getY2());
                case RECTANGLE -> gc.strokeRect(figure.getX1(),figure.getY1(),figure.getWidth(),figure.getHeight());
                case OVAL -> gc.strokeOval(figure.getX1(),figure.getY1(),figure.getWidth(),figure.getHeight());
            }

        //gc.setStroke(...)
        //gc.fill*(...)
        //gc.setFill(...)
        //gc.setLineWidth(....);


        gc.strokeText("Advanced Programming",300,300);*/

    }
}
