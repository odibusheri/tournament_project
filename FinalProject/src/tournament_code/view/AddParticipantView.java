package tournament_code.view;

import java.util.ArrayList;

import tournament_code.listeners.ViewListenable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddParticipantView extends VBox {
	private ArrayList<ViewListenable> allListeners;
	private HBox hbName;
	private Label lblName;
	private TextField tfName;

	private HBox hbButtons;
	private Button btnAddParticipant;
	private Button btnStart;

	public AddParticipantView(ArrayList<ViewListenable> allListeners) {
		this.allListeners = allListeners;
		setSpacing(30);
		hbName = new HBox(10);
		lblName = new Label("Particiapant name:");
		tfName = new TextField();

		hbName.getChildren().addAll(lblName, tfName);
		hbName.setAlignment(Pos.CENTER);

		btnAddParticipant = new Button("Add participant");
		btnAddParticipant.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				addParticipant();
				tfName.clear();
			}
		});
		btnStart = new Button("Start Championship");
		btnStart.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				startCahmpionship();

			}

		});

		hbButtons = new HBox(20);
		hbButtons.getChildren().addAll(btnAddParticipant, btnStart);
		hbButtons.setAlignment(Pos.CENTER);

		setPadding(new Insets(20));
		setAlignment(Pos.CENTER);
		getChildren().addAll(hbName, hbButtons);

	}

	public void addParticipant() {
		if (!tfName.getText().isEmpty()) {
			String name = tfName.getText();
			allListeners.get(0).viewAddedParticipant(name);
		} else {
			Alert altEmpty = new Alert(AlertType.ERROR, "Empty fields", ButtonType.OK);
			altEmpty.show();
		}
	}

	public void startCahmpionship() {
		try {
			if (allListeners.get(0).viewStratGame(allListeners.get(0).viewAskForchosenGameType())) {
				setVisible(false);
			}

		} catch (Exception e) {
			Alert alertError = new Alert(AlertType.ERROR);
			alertError.setContentText(e.toString());
			alertError.show();
		}
	}
}