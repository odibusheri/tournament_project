package id206059156_id313533341.view;

import id206059156_id313533341.model.Participant;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AllParticipantsView extends VBox {

	public AllParticipantsView() {
		setSpacing(10);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(20));

	}

	public void addParticipantToView(Participant participant) {
		TextField tfName = new TextField(participant.getName());
		HBox hbName = new HBox(10);
		tfName.setEditable(false);
		hbName.getChildren().add(tfName);
		getChildren().add(hbName);
	}
}
