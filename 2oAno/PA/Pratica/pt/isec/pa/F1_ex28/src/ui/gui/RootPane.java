package ui.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class RootPane extends BorderPane {

    Color background = Color.WHITE;

    int nrGreen, nrBlue, nrOther;

    Button btnGreen;
    Button btnBlue;
    TextField tfColor;
    Button btnCustomColor;
    Label lbStatus;

    public RootPane(){
        nrGreen = nrBlue = nrOther = 0;
        createViews();
        registerHandlers();
        update();
    }

    public void createViews(){
        btnBlue = new Button("Blue");
        btnBlue.setMinWidth(75);
        btnGreen = new Button("Green");
        btnGreen.setMinWidth(75);
        tfColor = new TextField();
        tfColor.setPrefWidth(Integer.MAX_VALUE);
        tfColor.setMinWidth(200);
        tfColor.setPromptText("Color name or RGB");
        btnCustomColor = new Button("Set Color");
        btnCustomColor.setMinWidth(75);

        lbStatus = new Label();
        lbStatus.setPrefWidth(Integer.MAX_VALUE);
        lbStatus.setStyle("-fx-background-color: #c0c0c0; -fx-font-size: 16; -fx-font-family: 'Courier New'");
        lbStatus.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        lbStatus.setPadding(new Insets(10));

        HBox hb = new HBox();
        hb.getChildren().addAll(btnBlue, btnGreen, tfColor, btnCustomColor);
        hb.setSpacing(20);
        this.setTop(hb);
        this.setBottom(lbStatus);
    }

    public void registerHandlers(){
        btnGreen.setOnAction(actionEvent -> {
            nrGreen++;
            background = Color.GREEN; // no tp isto do update não existe. Nós temos o modelo de dados e o manager e assim, aqui invocamos um metodo da máquina de estados. A máquina depois avisa as vistas que tiveram uma atualização e depois elas atualizam
            update();
        });

        btnBlue.setOnAction(actionEvent -> {
            nrBlue++;
            background = Color.BLUE;
            update();
        });

        btnCustomColor.setOnAction(actionEvent -> {
            nrOther++;
            try{
                background = Color.valueOf(tfColor.getText());
            }catch (Exception e){
                background = Color.BLACK;
                tfColor.setStyle("-fx-control-inner-background: #ff0000;");
                tfColor.requestFocus();
            }
            update();

            tfColor.setOnKeyPressed(keyEvent -> {
                tfColor.setStyle("-fx-control-inner-background: #FFFFFF");
                if (keyEvent.getCode() == KeyCode.ENTER){
                    btnCustomColor.fire();
                }
            });
        });
    }

    public void update(){
        this.setBackground(new Background(new BackgroundFill(background, CornerRadii.EMPTY, Insets.EMPTY)));
        tfColor.setText(String.format("#%02X%02X%02X", (int) (255 * background.getRed()), (int) (255 * background.getGreen()), (int) (255 * background.getBlue())));
        lbStatus.setText(String.format("Green: %d \t Blue: %d \t Other: %d", nrGreen, nrBlue, nrOther));
    }
}
