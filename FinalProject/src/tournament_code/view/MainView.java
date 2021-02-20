package tournament_code.view;

import java.util.ArrayList;

import tournament_code.listeners.ViewListenable;
import tournament_code.model.Participant;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainView implements TournamentViewable {
	private ArrayList<ViewListenable> allListeners;
	private BorderPane bpMain;
	private HBox hbTop;
	private Label lblTopTitle;
	private HBox hbLeft;
	private HBox hbCenter;
	private HBox hbRight;

	private AddParticipantView addParticipantView;
	private AllParticipantsView allParticipantsView;
	private ChooseGameView chooseGameView;

	private ArrayList<StageView> allStages;

	private int activeStage = 0;

	public MainView(Stage primaryStage) {
		allListeners = new ArrayList<ViewListenable>();
		bpMain = new BorderPane();
		bpMain.setPadding(new Insets(10));

		hbTop = new HBox(10);
		hbTop.setAlignment(Pos.CENTER);
		lblTopTitle = new Label("Championship");
		lblTopTitle.setFont(Font.font("Arial", FontWeight.BOLD, 22));
		lblTopTitle.setTextFill(Color.DARKBLUE);
		hbTop.getChildren().add(lblTopTitle);
		bpMain.setTop(hbTop);

		hbCenter = new HBox(10);
		hbCenter.setAlignment(Pos.CENTER);
		hbRight = new HBox(10);
		hbRight.setAlignment(Pos.CENTER);
		hbLeft = new HBox(10);
		hbLeft.setAlignment(Pos.CENTER);

		addParticipantView = new AddParticipantView(allListeners);

		allParticipantsView = new AllParticipantsView();

		chooseGameView = new ChooseGameView();

		allStages = new ArrayList<StageView>();

		bpMain.setLeft(allParticipantsView);
		bpMain.setCenter(addParticipantView);
		bpMain.setRight(chooseGameView);

		Scene scene = new Scene(bpMain, 1200, 600);
		primaryStage.setTitle("Tournament");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void registerListener(ViewListenable l) {
		allListeners.add(l);
	}

	@Override
	public void showAddedParticipant(Participant pariticpant) {
		allParticipantsView.addParticipantToView(pariticpant);
	}

	@Override
	public String getGameType() throws Exception {
		return chooseGameView.getGameType();
	}

	@Override
	public void showMsg(String msg) {
		Alert alertExpetion = new Alert(AlertType.ERROR);
		alertExpetion.setContentText(msg);
		alertExpetion.show();
	}

	@Override
	public void setGameStageIndex(int index) {
		this.activeStage = index;
	}

	@Override
	public void showStartGame(ArrayList<Participant> players, int size) {
		addParticipantView.setVisible(false);
		chooseGameView.setVisible(false);
		allStages.add(new StageView(allListeners, players.size(), this)); // first stage
		allStages.add(new StageView(allListeners, players.size() / 2, this)); // second stage
		allStages.add(new StageView(allListeners, players.size() / 4, this)); // third stage
		allStages.add(new StageView(allListeners, 1, this)); // winner

		allStages.get(0).addStageOnePlayers(players);

		hbCenter.getChildren().addAll(allStages.get(1), allStages.get(2));
		hbRight.getChildren().add(allStages.get(3));
		bpMain.setLeft(allStages.get(0));
		bpMain.setCenter(hbCenter);
		bpMain.setRight(hbRight);

	}

	@Override
	public void showCurrentWinner(Participant winnerNow) throws Exception {
		if (activeStage != allStages.size() - 1) {
			allStages.get(getActiveStage() + 1).getAllPlayers().get(allStages.get(activeStage).getActivePlayButton())
					.setText(winnerNow.getName());
		}
	}

	public int getActiveStage() {
		return activeStage;
	}

}
