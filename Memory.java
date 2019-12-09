

import java.util.Collections;
import java.util.LinkedList;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Memory extends Application {

	private static final int BOARD_SIZE = 2;
	private static final int WINDOW_WIDTH = 400;
	private static final int WINDOW_HEIGHT = 400;


	private Scene introScene, gameScene, endScene;
	private Stage primaryStage;

	private Label scoreLabel;
	private Label totalScore;

	private CharButton clickedButton;

	private int score;
	private int status;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;

		makeIntroScene();
		makeGameScene();
		makeEndScene();

		primaryStage.setScene(introScene);
		primaryStage.setTitle("Memory");
		primaryStage.show();
	}


	private void makeIntroScene() {
		VBox rootPane = new VBox(20);

		rootPane.setAlignment(Pos.CENTER);
		// rootPane.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
		rootPane.setStyle("-fx-background-color: lightblue;");

		Label gameTitle = new Label("Memory!");
		gameTitle.setStyle("-fx-font-size: 20pt;");

		Button startButton = new Button("Start game");
		startButton.setOnAction(e -> primaryStage.setScene(gameScene));

		rootPane.getChildren().addAll(gameTitle, startButton);

		introScene = new Scene(rootPane, WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	private void makeGameScene() {
		BorderPane rootPane = new BorderPane();
		GridPane gamePane = new GridPane();

		LinkedList<Character> chars = new LinkedList<Character>();

		for (char c = 'a'; c < 'a' + BOARD_SIZE*BOARD_SIZE/2; c++) {
			chars.add(c);
			chars.add(c);
		}

		Collections.shuffle(chars);

		for (int x=0; x<BOARD_SIZE; x++) {
			for (int y=0; y<BOARD_SIZE; y++) {
				CharButton b = new CharButton(chars.remove());
				b.setPrefWidth(WINDOW_WIDTH/BOARD_SIZE);
				b.setPrefHeight(WINDOW_HEIGHT/BOARD_SIZE);

				b.setOnAction(e -> handleClick(b));

				gamePane.add(b, x, y);
			}
		}

		scoreLabel = new Label("Score: 0");
		scoreLabel.setStyle("-fx-font-size: 14pt;");

		rootPane.setCenter(gamePane);
		rootPane.setTop(scoreLabel);
		rootPane.setAlignment(scoreLabel, Pos.CENTER);

		gameScene = new Scene(rootPane);
	}

	private void handleClick(CharButton newClicked) {
		newClicked.toggleClick();

		if (clickedButton == null) {
			clickedButton = newClicked;

		} else {
			if (newClicked.equals(clickedButton)) {
				status++;
				newClicked.setCorrect();
				clickedButton.setCorrect();

				clickedButton = null;

			} else {
				PauseTransition pt = new PauseTransition(Duration.millis(500));

				pt.setOnFinished(e -> {
					newClicked.toggleClick();
					clickedButton.toggleClick();
					clickedButton = null;
				});
				pt.play();

			}

			score++;
			scoreLabel.setText("Score: " + score);
		}

		if (status == BOARD_SIZE*BOARD_SIZE/2) {
			totalScore.setText("Horray, you did it in " + score + " tries!");
			primaryStage.setScene(endScene);
		}
	}

	private void makeEndScene() {
		VBox rootPane = new VBox(20);

		rootPane.setAlignment(Pos.CENTER);
		// rootPane.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
		rootPane.setStyle("-fx-background-color: lightblue;");

		totalScore = new Label();
		totalScore.setStyle("-fx-font-size: 20pt;");

		Button startButton = new Button("Restart game");
		startButton.setOnAction(e -> {
			makeGameScene();
			status = 0;
			score = 0;
			primaryStage.setScene(gameScene);
		});

		rootPane.getChildren().addAll(totalScore, startButton);

		endScene = new Scene(rootPane, WINDOW_WIDTH, WINDOW_HEIGHT);
	}

}
