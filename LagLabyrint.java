import javafx.application.Application;
import javafx.scene.input.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Optional;

public class LagLabyrint extends Application {
    GUIRute[][] ruter;
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        root.setTop(lagToppBoks());

        GridPane rutenett = new GridPane();
        int storrelse = 20;
        ruter = new GUIRute[10][10];
        for(int rad = 0; rad < 10; rad++) {
            for(int kol = 0; kol < 10; kol++) {
                ruter[rad][kol] = new GUIRute(storrelse);
                rutenett.add(ruter[rad][kol], kol, rad);
            }
        }
        root.setCenter(rutenett);

        Button lagre = new Button("Lagre");
        lagre.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                for(GUIRute[] rad : ruter) {
                    for(GUIRute rute : rad) {
                        System.out.print(rute);
                    }
                    System.out.println();
                }
            }
        });
        root.setBottom(lagre);

        stage.setScene(new Scene(root));
        stage.setTitle("Lag labyrint");
        stage.show();
    }

    private class GUIRute extends Pane {
        boolean erHvit = true;

        public GUIRute(int storrelse) {
            oppdaterFarge();
            setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                null, new BorderWidths(1))));
            setMinWidth(storrelse);
            setMinHeight(storrelse);

            addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    erHvit = !erHvit;
                    oppdaterFarge();
                }
            });
        }

        public void oppdaterFarge() {
            if(erHvit) {
                setBackground(new Background(
                    new BackgroundFill(Color.WHITE, null, null)));
            }
            else {
                setBackground(new Background(
                    new BackgroundFill(Color.BLACK, null, null)));
            }
        }

        @Override
        public String toString() {
            /*if(erHvit) {
                return ".";
            }
            else {
                return "#";
            }*/
            return erHvit ? "." : "#";
        }
    }

    private HBox lagToppBoks() {
        Label breddetekst = new Label("Bredde: ");
        TextField bredde = new TextField("10");
        Label hoydetekst = new Label("Hoyde: ");
        TextField hoyde = new TextField("5");
        Button lagRutenett = new Button("Lag rutenett");

        return new HBox(breddetekst, bredde, hoydetekst, hoyde, lagRutenett);
    }
}
