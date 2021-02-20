package id206059156_id313533341.view;

import java.util.ArrayList;

import id206059156_id313533341.listeners.ViewListenable;
import id206059156_id313533341.model.Participant;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StageView extends HBox {
	public static int stageCounter = 0;
	public static int cuurentActiveStage;

	private int activePlayButton;
	private int stageIndex;

	private VBox vbNames;
	private VBox vbButtons;
	private ArrayList<TextField> allPlayers;
	private ArrayList<Button> allPlayButtons;
	private ScoreView scoreView;

	public StageView(ArrayList<ViewListenable> allListeners, int stageSize, MainView view) {
		stageIndex = stageCounter++;
		vbNames = new VBox(10);
		vbButtons = new VBox(40);
		allPlayers = new ArrayList<TextField>();
		allPlayButtons = new ArrayList<Button>();
		vbNames.setAlignment(Pos.CENTER);
		vbButtons.setAlignment(Pos.CENTER);

		setPadding(new Insets(20));

		for (int i = 0; i < stageSize; i++) {
			allPlayers.add(new TextField());
			allPlayers.get(i).setEditable(false);
			vbNames.getChildren().add(allPlayers.get(i));
		}

		if (stageSize > 1) { // if not last stage(winner) add "Play Game" buttons
			for (int i = 0; i < stageSize / 2; i++) {
				Button btnPlay = new Button("Play Game");
				allPlayButtons.add(btnPlay);

				btnPlay.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						activePlayButton = getAllPlayButtons().indexOf(event.getTarget());
						try {
							if (allListeners.get(0).getCurrentStageIndex() != cuurentActiveStage) {
								cuurentActiveStage++;
								view.setGameStageIndex(cuurentActiveStage);
							}
							if (activePlayButton == allListeners.get(0).viewAskForGameIndex(cuurentActiveStage)
									&& stageIndex == allListeners.get(0).getCurrentStageIndex()) {
								scoreView = new ScoreView(new Stage(), allListeners);
								scoreView.showScoreWindow(activePlayButton, cuurentActiveStage);
							} else {
								throw new Exception("Can't play this game");
							}
						} catch (Exception e) {
							Alert altException = new Alert(AlertType.ERROR, e.toString(), ButtonType.OK);
							altException.show();
							System.out.println(e.toString());
						}

					}
				});
			}
		}

		vbButtons.getChildren().addAll(allPlayButtons);
		vbButtons.setPadding(new Insets(10));
		getChildren().addAll(vbNames, vbButtons);

	}

	public void addStageOnePlayers(ArrayList<Participant> players) {
		for (int i = 0; i < players.size(); i++) {
			allPlayers.get(i).setText(players.get(i).getName());
			allPlayers.get(i).setEditable(false);
		}
	}

	public ArrayList<Button> getAllPlayButtons() {
		return allPlayButtons;
	}

	public ArrayList<TextField> getAllPlayers() {
		return allPlayers;
	}

	public int getActivePlayButton() {
		return activePlayButton;
	}

}
