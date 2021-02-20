//Odeia Busheri 206059156 and Eddie Rudoy 313533341
package id206059156_id313533341.main;

import id206059156_id313533341.controller.Controller;
import id206059156_id313533341.model.Participant;
import id206059156_id313533341.model.Tournament;
import id206059156_id313533341.view.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Tournament tournament = new Tournament();

		MainView tournamentView = new MainView(primaryStage);

		Controller controller = new Controller(tournament, tournamentView);

	}

}
