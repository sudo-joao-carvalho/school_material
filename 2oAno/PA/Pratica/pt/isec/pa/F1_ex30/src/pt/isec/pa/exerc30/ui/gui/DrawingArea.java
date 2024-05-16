package pt.isec.pa.exerc30.ui.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pt.isec.pa.exerc30.model.Drawing;
import pt.isec.pa.exerc30.model.Figure;

public class DrawingArea extends Canvas {

    Drawing drawing; // -> isto é o data //DrawingData drawingData;
    public DrawingArea(Drawing drawing) {
        //super(600, 400);  //se o canvas nao tiver tamanho definido ele fica 0 por 0 -> isto funciona com um tamanho fixo
        //mas se houver resize da window toda o tamanho fica estatico
        this.drawing = drawing;

        //createViews();
        registerHandlers();
        update();
    }

    private void registerHandlers(){
        this.setOnMousePressed(mouseEvent -> {
            //alinea a
            drawing.setRGB(Math.random(), Math.random(), Math.random()); // -> mete uma cor aleatoria
            if(mouseEvent.isControlDown()){
                drawing.setCurrentType(Figure.FigureType.RECTANGLE);
            } else if (mouseEvent.isAltDown()) {
                drawing.setCurrentType(Figure.FigureType.OVAL);
            }else {
                drawing.setCurrentType(Figure.FigureType.LINE);
            }

            drawing.createFigure(mouseEvent.getX(), mouseEvent.getY());
            update();
        });

        this.setOnMouseDragged(mouseEvent -> {
            drawing.updateCurrentFigure(mouseEvent.getX(), mouseEvent.getY());
            update();
        });

        this.setOnMouseReleased(mouseEvent -> {
            drawing.finishCurrentFigure(mouseEvent.getX(), mouseEvent.getY());
            update();
        });
    }

    private void update(){
        GraphicsContext gc = this.getGraphicsContext2D(); //precisamos disto para desenhar no canvas

        clearScreen(gc);

        for(Figure figure: drawing.getList()){
            drawFigure(gc, figure);
        }

        drawFigure(gc, drawing.getCurrentFigure());
    }

    private void clearScreen(GraphicsContext gc){
        gc.setFill(Color.LIGHTGOLDENRODYELLOW);
        gc.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawFigure(GraphicsContext gc, Figure figure){
        if(figure == null)
            return;

        Color color = Color.color(figure.getR(), figure.getG(), figure.getB());
        gc.setFill(color);
        gc.setLineWidth(3);

        switch (figure.getType()){
            case LINE -> {
                gc.setStroke(color);
                gc.strokeLine(figure.getX1(), figure.getY1(), figure.getX2(), figure.getY2());
            }

            case RECTANGLE -> {
                gc.fillRect(figure.getX1(), figure.getY1(), figure.getWidth(), figure.getHeight());
                gc.setStroke(color.darker());
                gc.strokeRect(figure.getX1(), figure.getY1(), figure.getWidth(), figure.getHeight());
            }

            case OVAL -> {
                gc.fillOval(figure.getX1(), figure.getY1(), figure.getWidth(), figure.getHeight());
                gc.setStroke(color.darker());
                gc.strokeOval(figure.getX1(), figure.getY1(), figure.getWidth(), figure.getHeight());
            }
        }

    }

    public void updateSize(double w, double h){
        setWidth(w);
        setHeight(h);
        update(); //é preciso o update pq no fundo ele mudava a width e a height mas nao ia aparecer no ecra
    }
}
