package id206059156_id313533341.view;

import java.util.ArrayList;

import id206059156_id313533341.listeners.ViewListenable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ScoreView {
	private ArrayList<ViewListenable> allListeners;
	private VBox vbMain;
	private HBox hbGameType;
	private Label lblGameType;

	private HBox hbAllScores;

	private VBox vbNames;
	private Stage seconderyStage;
	private VBox vbScores;

	private Label lblNamePlayerOne;
	private HBox hbScorePlayerOne;
	private ArrayList<TextField> scoresPlayerOne;
	private HBox hbScorePlayerTwo;
	private Label lblNamePlayerTwo;
	private ArrayList<TextField> scoresPlayerTwo;
	private Button btnDone;

	private int scoreWindowIndex;
	private int activeStage;
	private int scoresCounter = 0;
	private int numOfScores;
	private boolean isTie;

	public ScoreView(Stage seconderyStage, ArrayList<ViewListenable> allListeners) {
		this.allListeners = allListeners;
		this.seconderyStage = seconderyStage;

		vbMain = new VBox(30);
		hbAllScores = new HBox(10);
		hbGameType = new HBox(10);
		hbGameType.setPadding(new Insets(20));
		lblGameType = new Label(getGameTypeName() + " Game");
		lblGameType.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		lblGameType.setTextFill(Color.BLUEVIOLET);
		hbGameType.getChildren().add(lblGameType);
		hbGameType.setAlignment(Pos.TOP_CENTER);
		lblGameType.setAlignment(Pos.TOP_CENTER);

		vbScores = new VBox(10);
		vbScores.setPadding(new Insets(10));
		vbScores.setAlignment(Pos.CENTER);
		vbNames = new VBox(30);

		hbScorePlayerOne = new HBox(10);
		scoresPlayerOne = new ArrayList<TextField>();
		lblNamePlayerOne = new Label();
		vbNames.getChildren().add(lblNamePlayerOne);

		hbScorePlayerTwo = new HBox(10);
		lblNamePlayerTwo = new Label();
		scoresPlayerTwo = new ArrayList<TextField>();
		vbNames.getChildren().add(lblNamePlayerTwo);

		Alert altEmpty = new Alert(AlertType.ERROR, "Empty fileds", ButtonType.OK);
		btnDone = new Button("Done");
		btnDone.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (emptyFileds()) {
					try {
						if (!isTie) {
							for (int j = 0; j < numOfScores; j++) {
								allListeners.get(0).viewManageGame(getIndex(), getScorePlayerOne(j),
										getScorePlayerTwo(j));
							}
						} else {
							for (int i = 0; i < allListeners.get(0).getTieRules(); i++) {
								allListeners.get(0).viewMangeTie(getActiveStage(), getIndex(),
										getScorePlayerOne((i + scoresCounter)), getScorePlayerTwo(i + scoresCounter));
							}

						}
						if (!(allListeners.get(0).viewAskIsTie(getActiveStage(), getIndex()))) {
							getWinner();
							seconderyStage.close();
						} else {
							Alert alt = new Alert(AlertType.ERROR, "Game is not over", ButtonType.OK);
							alt.show();
							manageTie();
							isTie = true;
							scoresCounter = numOfScores++;
						}

					} catch (Exception e) {

						System.out.println(e.toString());

					}

				} else {
					altEmpty.show();
				}
			}

		});

		VBox vbPlayersScores = new VBox(10);
		vbPlayersScores.getChildren().addAll(hbScorePlayerOne, hbScorePlayerTwo);
		hbAllScores.getChildren().addAll(vbNames, vbPlayersScores);
		vbScores.getChildren().addAll(hbAllScores, btnDone);
		vbMain.getChildren().addAll(hbGameType, vbScores);

		Scene scoreScene = new Scene(vbMain, 400, 250);
		seconderyStage.initStyle(StageStyle.UNDECORATED);
		seconderyStage.setScene(scoreScene);

	}

	private String getGameTypeName() {
		return allListeners.get(0).viewAskForGameType();
	}

	public void showScoreWindow(int gameIndex, int activeStageIndex) throws Exception {
		setScoreIndex(gameIndex);
		this.activeStage = activeStageIndex;
		setPlayersNames(allListeners.get(0).viewAskForCurrentGame().getPlayerOne().getName(),
				allListeners.get(0).viewAskForCurrentGame().getPlayerTwo().getName());
		setNumOfScores(allListeners.get(0).viewAskForCurrentGame().numOfScores());
		seconderyStage.show();
	}

	public void setPlayersNames(String playerOne, String playerTwo) {
		this.lblNamePlayerOne.setText(playerOne);
		this.lblNamePlayerTwo.setText(playerTwo);
	}

	public void setNumOfScores(int numOfScores) { // input scores for the games (tennis=3/5, basketball=4, soccer=2)
		for (int i = 0; i < numOfScores; i++) {
			scoresPlayerOne.add(new TextField());
			scoresPlayerTwo.add(new TextField());
			scoresPlayerOne.get(i).setPrefWidth(50);
			scoresPlayerTwo.get(i).setPrefWidth(50);
			this.numOfScores = numOfScores;
		}
		hbScorePlayerOne.getChildren().addAll(scoresPlayerOne);
		hbScorePlayerTwo.getChildren().addAll(scoresPlayerTwo);
	}

	public void getWinner() throws Exception {
		allListeners.get(0).viewAskCurrentWinner(getIndex(), activeStage);
	}

	public int getScorePlayerOne(int index) {
		return Integer.parseInt(scoresPlayerOne.get(index).getText());
	}

	public int getScorePlayerTwo(int index) {
		return Integer.parseInt(scoresPlayerTwo.get(index).getText());
	}

	public void setScoreIndex(int index) {
		seconderyStage.setTitle(String.valueOf(index));
		this.scoreWindowIndex = index;
	}

	public int getIndex() {
		return scoreWindowIndex;
	}

	public int getActiveStage() {
		return activeStage;
	}

	public boolean emptyFileds() {
		for (int i = 0; i < scoresPlayerOne.size(); i++) {
			if (scoresPlayerOne.get(i).getText().trim().isEmpty() || scoresPlayerTwo.get(i).getText().trim().isEmpty())
				return false;
		}
		return true;
	}

	public void manageTie() { // add more textfields in case of tie (depends on game type)
		int tieRule = allListeners.get(0).getTieRules();
		for (int i = 0; i < tieRule; i++) {
			scoresPlayerOne.add(new TextField());
			scoresPlayerTwo.add(new TextField());
			scoresPlayerOne.get(numOfScores + i).setPrefWidth(50);
			scoresPlayerTwo.get(numOfScores + i).setPrefWidth(50);
		}
		hbScorePlayerOne.getChildren().setAll(scoresPlayerOne);
		hbScorePlayerTwo.getChildren().setAll(scoresPlayerTwo);

	}

}
